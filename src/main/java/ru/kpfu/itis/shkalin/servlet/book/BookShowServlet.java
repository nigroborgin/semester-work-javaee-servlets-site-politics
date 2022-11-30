package ru.kpfu.itis.shkalin.servlet.book;

import ru.kpfu.itis.shkalin.dto.BookFullDto;
import ru.kpfu.itis.shkalin.service.crud.BookCrudService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "bookServlet", urlPatterns = {"/books/"})
public class BookShowServlet extends HttpServlet {

    BookCrudService bookCrudService;

    @Override
    public void init() throws ServletException {
        bookCrudService = (BookCrudService) getServletContext().getAttribute("bookCrudService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if (id != null) {
            BookFullDto book = bookCrudService.get(Integer.parseInt(id));
            if (book != null) {
                req.setAttribute("book", book);
            } else {
                req.setAttribute("message", "There is no such book");
            }
        } else {
            List<BookFullDto> bookList = bookCrudService.getAll();
            if (!bookList.isEmpty()) {
                req.setAttribute("bookList", bookList);
            }
        }
        req.getRequestDispatcher("/WEB-INF/view/books.ftl").forward(req, resp);
    }
}
