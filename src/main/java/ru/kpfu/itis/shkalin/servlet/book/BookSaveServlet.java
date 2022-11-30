package ru.kpfu.itis.shkalin.servlet.book;

import ru.kpfu.itis.shkalin.dto.BookFullDto;
import ru.kpfu.itis.shkalin.service.crud.BookCrudService;
import ru.kpfu.itis.shkalin.util.FileUpDownUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@MultipartConfig
@WebServlet(name = "bookSaveServlet", urlPatterns = {"/books/edit/", "/books/add/"})
public class BookSaveServlet extends HttpServlet {

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
        }
        req.getRequestDispatcher("/WEB-INF/view/book-form.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        BookFullDto book = new BookFullDto();

        if (id != null) {
            book.setId(Integer.parseInt(req.getParameter("id")));
        }

        book.setTitle(req.getParameter("title"));
        book.setAuthor(req.getParameter("author"));
        book.setDescription(req.getParameter("description"));

        Part filePart = req.getPart("file");
        String uploadPath = getServletContext().getRealPath("") + "books";
        int lengthFilePathSplitArray = filePart.getSubmittedFileName().split("\\.").length;
        String fileExtension = filePart.getSubmittedFileName().split("\\.")[lengthFilePathSplitArray - 1];
        String newFileName = req.getParameter("author") + ". "
                + req.getParameter("title") + "." + fileExtension;

        FileUpDownUtil fileUpDownUtil = (FileUpDownUtil) getServletContext().getAttribute("fileUpDownUtil");
        fileUpDownUtil.uploadFile(filePart, uploadPath, newFileName);

        book.setFileURL("/politics/books/" + newFileName);

        if (req.getRequestURI().contains("add")) {
            bookCrudService.create(book);
            req.setAttribute("message", "This book was added!");
        } else if (req.getRequestURI().contains("edit")) {
            bookCrudService.update(book);
            req.setAttribute("message", "This book was edited!");
        }

        resp.sendRedirect("/politics/books/");
    }
}
