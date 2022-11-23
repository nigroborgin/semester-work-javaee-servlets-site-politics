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

@WebServlet(name = "addArticleServlet", urlPatterns = "/addArticle")
public class AddArticleServlet extends HttpServlet {

    private PostCrudService postCrudService;

    @Override
    public void init() throws ServletException {
        postCrudService = (PostCrudService) getServletContext().getAttribute("postCrudService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/article-form.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PostFullDto article = new PostFullDto();
        HttpSession session = req.getSession();

        article.setUserId((Integer) session.getAttribute("id"));
        article.setViewAuthor(req.getParameter("viewAuthor"));
        article.setTitle(req.getParameter("title"));
        article.setText(req.getParameter("text"));
        article.setDate(req.getParameter("date"));
        article.setType("article");

        postCrudService.create(article);

        session.setAttribute("message", "Your post added!");
        resp.sendRedirect("/politics/articles");
    }
}
