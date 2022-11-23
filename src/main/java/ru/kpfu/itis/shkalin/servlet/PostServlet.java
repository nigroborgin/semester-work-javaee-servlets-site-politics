package ru.kpfu.itis.shkalin.servlet;

import ru.kpfu.itis.shkalin.dto.PostFullDto;
import ru.kpfu.itis.shkalin.service.сrud.PostCrudService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "postServlet", urlPatterns = {"/posts"})
public class PostServlet extends HttpServlet {

    private PostCrudService postCrudService;

    @Override
    public void init() throws ServletException {
        postCrudService = (PostCrudService) getServletContext().getAttribute("postCrudService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        PostFullDto post;
        List<PostFullDto> postList;

        if (id != null) {
            post = postCrudService.get(Integer.parseInt(id));
            if (post.getType().equals("post")) {
                req.setAttribute("post", post);
            } else {
                req.setAttribute("message", "There is no such post");
            }
        } else {
            postList = postCrudService.getAll().stream()
                    .filter(p -> p.getType().equals("post"))
                    .collect(Collectors.toList());
            req.setAttribute("postList", postList);
        }
        req.getRequestDispatcher("/WEB-INF/view/posts.ftl").forward(req, resp);
        req.getSession().removeAttribute("message");
    }
}
