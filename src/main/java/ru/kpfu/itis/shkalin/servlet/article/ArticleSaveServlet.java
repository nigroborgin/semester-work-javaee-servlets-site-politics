package ru.kpfu.itis.shkalin.servlet.article;

import ru.kpfu.itis.shkalin.dto.PostFullDto;
import ru.kpfu.itis.shkalin.service.crud.PostCrudService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "articleSaveServlet", urlPatterns = {"/articles/add/", "/posts/add/", "/articles/edit/", "/posts/edit/"})
public class ArticleSaveServlet extends HttpServlet {

    private PostCrudService postCrudService;

    @Override
    public void init() throws ServletException {
        postCrudService = (PostCrudService) getServletContext().getAttribute("postCrudService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String uri = req.getRequestURI();
        String type = null;
        if (uri.contains("posts")) {
            type = "post";
        } else if (uri.contains("articles")) {
            type = "article";
        }
        req.setAttribute("type", type + "s");
        if (id != null) {
            PostFullDto post = postCrudService.get(Integer.parseInt(id));

            if (post != null && type.equals(post.getType())) {
                req.setAttribute("post", post);
            } else {
                req.setAttribute("message", "There is no such " + type);
            }
        }
        req.getRequestDispatcher("/WEB-INF/view/article-form.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        PostFullDto post = new PostFullDto();
        String uri = req.getRequestURI();
        String type = null;

        if (uri.contains("posts")) {
            type = "post";
        } else if (uri.contains("articles")) {
            type = "article";
        }
        req.setAttribute("type", type + "s");


        if (id != null) {
            post.setId(Integer.parseInt(req.getParameter("id")));
        }

        post.setUserId((Integer) req.getSession().getAttribute("id"));
        post.setTitle(req.getParameter("title"));
        post.setText(req.getParameter("text"));
        post.setType(type);

        if (type.equals("article")) {
            post.setDate(LocalDateTime.parse(req.getParameter("date")));
            post.setViewAuthor(req.getParameter("viewAuthor"));
        } else if (type.equals("post") && req.getRequestURI().contains("add")) {
            post.setViewAuthor(req.getSession().getAttribute("username").toString());
            post.setDate(LocalDateTime.now());
        }

        if (req.getRequestURI().contains("add")) {
            postCrudService.create(post);
        } else if (req.getRequestURI().contains("edit")) {
//            postCrudService.get(id).getUserId().equals(req.getSession().getAttribute("id"))){
            postCrudService.update(post);
        }
        resp.sendRedirect("/politics/" + type + "s/");
    }
}
