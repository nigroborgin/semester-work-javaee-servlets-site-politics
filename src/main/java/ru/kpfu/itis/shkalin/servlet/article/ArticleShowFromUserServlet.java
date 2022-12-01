package ru.kpfu.itis.shkalin.servlet.article;

import ru.kpfu.itis.shkalin.dto.AccountFullDto;
import ru.kpfu.itis.shkalin.dto.PostFullDto;
import ru.kpfu.itis.shkalin.dto.PostViewDto;
import ru.kpfu.itis.shkalin.service.converter.ConverterService;
import ru.kpfu.itis.shkalin.service.converter.ConverterServiceImpl;
import ru.kpfu.itis.shkalin.service.crud.AccountCrudService;
import ru.kpfu.itis.shkalin.service.crud.PostCrudService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "articleShowFromUserServlet", urlPatterns = {"/posts/fromuser/", "/posts/my/"})
public class ArticleShowFromUserServlet extends HttpServlet {

    private PostCrudService postCrudService;
    private AccountCrudService accountCrudService;

    @Override
    public void init() throws ServletException {
        postCrudService = (PostCrudService) getServletContext().getAttribute("postCrudService");
        accountCrudService = (AccountCrudService) getServletContext().getAttribute("accountCrudService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String type = null;

        if (uri.contains("posts")) {
            type = "post";
        } else if (uri.contains("articles")) {
            type = "article";
        }
        req.setAttribute("type", type + "s");

        Integer idUser = null;
        if (uri.contains("/posts/my/")) {
            idUser = (Integer) req.getSession().getAttribute("id");
        } else if (req.getParameter("iduser") != null) {
            idUser = Integer.parseInt(req.getParameter("iduser"));
        }

        List<PostFullDto> postFullDtoList;
        List<PostViewDto> postViewDtoList = new ArrayList<>();
        ConverterService converter = (ConverterServiceImpl) getServletContext().getAttribute("converter");

        if (idUser != null) {
            AccountFullDto user = accountCrudService.get(idUser);
            if (user == null) {
                req.setAttribute("message", "There is no such user");
                req.getRequestDispatcher("/WEB-INF/view/articles-fromuser.ftl").forward(req, resp);
            } else {
                if (uri.contains("my")) {
                    req.setAttribute("title1", "My Posts");
                    req.setAttribute("showEditButtons", true);
                } else {
                    req.setAttribute("title1", "Posts from " + user.getUsername());
                    req.setAttribute("showEditButtons", false);
                }
                postFullDtoList = postCrudService.getByUserId(idUser);
                String finalType = type;
                postFullDtoList.stream()
                        .filter(p -> p.getType().equals(finalType))
                        .forEach(p -> {
                            PostViewDto postViewDto = new PostViewDto();
                            converter.update(p, postViewDto);
                            postViewDto.setDate(postCrudService.dateForView(p.getDate()));
                            postViewDtoList.add(postViewDto);
                        });
                if (!postViewDtoList.isEmpty()) {
                    req.removeAttribute("postList");
                    req.setAttribute("postList", postViewDtoList);
                }

                if (req.getSession() != null && req.getSession().getAttribute("role") != null) {
                    if (req.getSession().getAttribute("role").toString().equals("admin")) {
                        req.setAttribute("showEditButtons", true);
                    }
                }
                req.getRequestDispatcher("/WEB-INF/view/articles-fromuser.ftl").forward(req, resp);
            }
        } else {
            req.setAttribute("message",
                    "You are trying to view the user's posts, but you have not given his ID. \n" +
                            "You can select the user whose posts you want to view from the table.");
            req.getRequestDispatcher("/WEB-INF/view/users.ftl").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


}
