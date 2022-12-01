package ru.kpfu.itis.shkalin.servlet.article;

import ru.kpfu.itis.shkalin.dto.PostFullDto;
import ru.kpfu.itis.shkalin.dto.PostViewDto;
import ru.kpfu.itis.shkalin.service.converter.ConverterService;
import ru.kpfu.itis.shkalin.service.converter.ConverterServiceImpl;
import ru.kpfu.itis.shkalin.service.crud.PostCrudService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "articleShowServlet", urlPatterns = {"/articles/", "/posts/"})
public class ArticleShowServlet extends HttpServlet {

    private PostCrudService postCrudService;

    @Override
    public void init() throws ServletException {
        postCrudService = (PostCrudService) getServletContext().getAttribute("postCrudService");
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
        String id = req.getParameter("id");
        PostFullDto postFullDto;
        PostViewDto post = new PostViewDto();
        List<PostViewDto> postList = new ArrayList<>();
        ConverterService converter = (ConverterServiceImpl) getServletContext().getAttribute("converter");

        if (id != null) {
            postFullDto = postCrudService.get(Integer.parseInt(id));
            if (postFullDto != null && postFullDto.getType().equals(type)) {
                converter.update(postFullDto, post);
                post.setDate(postCrudService.dateForView(postFullDto.getDate()));
                req.setAttribute("post", post);
            } else {
                req.setAttribute("message", "There is no such article");
            }
        } else {
            String finalType = type;
            postCrudService.getAll().stream()
                    .filter(p -> p.getType().equals(finalType))
                    .forEach(p -> {
                        PostViewDto postViewDto = new PostViewDto();
                        converter.update(p, postViewDto);
                        postViewDto.setDate(postCrudService.dateForView(p.getDate()));
                        postList.add(postViewDto);
                    });
            if (!postList.isEmpty()) {
                req.setAttribute("postList", postList);
            }
        }

        if (req.getSession() != null && req.getSession().getAttribute("role") != null) {
            if (req.getSession().getAttribute("role").toString().equals("admin")) {
                req.setAttribute("showEditButtons", true);
            } else {
                req.setAttribute("showEditButtons", false);
            }
        } else {
            req.setAttribute("showEditButtons", false);
        }
        req.getRequestDispatcher("/WEB-INF/view/articles.ftl").forward(req, resp);
    }
}
