package ru.kpfu.itis.shkalin.servlet.article;

import ru.kpfu.itis.shkalin.service.crud.PostCrudService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "articleDeleteServlet", urlPatterns = {"/articles/delete/", "/posts/delete/"})
public class ArticleDeleteServlet extends HttpServlet {

    private PostCrudService postCrudService;

    @Override
    public void init() throws ServletException {
        postCrudService = (PostCrudService) getServletContext().getAttribute("postCrudService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String type = null;
        if (uri.contains("posts")) {
            type = "post";
        } else if (uri.contains("articles")) {
            type = "article";
        }
        req.setAttribute("type", type + "s");

        postCrudService.delete(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect("/politics/" + type + "s/");
    }
}
