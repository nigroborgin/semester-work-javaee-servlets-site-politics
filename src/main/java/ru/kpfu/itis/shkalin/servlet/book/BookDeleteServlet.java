package ru.kpfu.itis.shkalin.servlet.book;

import ru.kpfu.itis.shkalin.service.crud.BookCrudService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "bookDeleteServlet", urlPatterns = {"/books/delete/"})
public class BookDeleteServlet extends HttpServlet {

    BookCrudService bookCrudService;

    @Override
    public void init() throws ServletException {
        bookCrudService = (BookCrudService) getServletContext().getAttribute("bookCrudService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        bookCrudService.delete(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect("/politics/books/");
    }
}
