package ru.kpfu.itis.shkalin.servlet.account;

import ru.kpfu.itis.shkalin.dto.AccountAdminEditDto;
import ru.kpfu.itis.shkalin.dto.AccountFullDto;
import ru.kpfu.itis.shkalin.entity.Role;
import ru.kpfu.itis.shkalin.service.crud.AccountCrudService;
import ru.kpfu.itis.shkalin.util.FileUpDownUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@MultipartConfig
@WebServlet(name = "accountSaveServlet", urlPatterns = {"/users/edit/"})
public class AccountSaveServlet extends HttpServlet {

    AccountCrudService accountCrudService;

    @Override
    public void init() throws ServletException {
        accountCrudService = (AccountCrudService) getServletContext().getAttribute("accountCrudService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if (id != null) {
            AccountFullDto user = accountCrudService.get(Integer.parseInt(id));
            if (user != null) {
                req.setAttribute("user", user);
                req.getRequestDispatcher("/WEB-INF/view/user-form.ftl").forward(req, resp);
            } else {
                req.setAttribute("message", "There is no such user");
                req.getRequestDispatcher("/WEB-INF/view/users.ftl").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        AccountFullDto user = new AccountFullDto();

        if (id != null) {
            user.setId(Integer.parseInt(req.getParameter("id")));
        }

        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setEmail(req.getParameter("email"));
        Role role = new Role();
        role.setName(req.getParameter("role"));
        user.setRole(role);

        Part filePart = req.getPart("picture");
        String uploadPath = getServletContext().getRealPath("") + "picture" + File.separator + "users";
        int lengthFilePathSplitArray = filePart.getSubmittedFileName().split("\\.").length;
        String fileExtension = filePart.getSubmittedFileName().split("\\.")[lengthFilePathSplitArray - 1];
        String newFileName = req.getParameter("username") + "-" + user.hashCode() + "." + fileExtension;

        FileUpDownUtil fileUpDownUtil = (FileUpDownUtil) getServletContext().getAttribute("fileUpDownUtil");
        fileUpDownUtil.uploadFile(filePart, uploadPath, newFileName);

        user.setPictureURL("/politics/picture/users/" + newFileName);
        if (req.getRequestURI().contains("edit")) {
            accountCrudService.update(user);
            req.setAttribute("message", "This user was edited!");
        }
        resp.sendRedirect("/politics/users/");
//        req.getRequestDispatcher("/WEB-INF/view/users.ftl").forward(req, resp);
    }
}
