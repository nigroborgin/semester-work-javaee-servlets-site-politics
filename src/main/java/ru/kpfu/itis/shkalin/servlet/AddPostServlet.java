package ru.kpfu.itis.shkalin.servlet;

import ru.kpfu.itis.shkalin.dto.PostFullDto;
import ru.kpfu.itis.shkalin.service.сrud.PostCrudService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "addPostServlet", urlPatterns = "/addPost")
public class AddPostServlet extends HttpServlet {

    private PostCrudService postCrudService;

    @Override
    public void init() throws ServletException {
        postCrudService = (PostCrudService) getServletContext().getAttribute("postCrudService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/post-form.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PostFullDto post = new PostFullDto();
        HttpSession session = req.getSession();

        post.setUserId((Integer) session.getAttribute("id"));
        post.setViewAuthor(session.getAttribute("username").toString());
        post.setTitle(req.getParameter("title"));
        post.setText(req.getParameter("text"));
        post.setDate(new Date().toString());
        post.setType("post");

        postCrudService.create(post);

        session.setAttribute("message", "Your post added!");
        resp.sendRedirect("/politics/posts");
    }
}
