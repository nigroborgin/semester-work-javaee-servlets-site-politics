package ru.kpfu.itis.shkalin;

import ru.kpfu.itis.shkalin.dao.*;
import ru.kpfu.itis.shkalin.service.converter.ConverterServiceImpl;
import ru.kpfu.itis.shkalin.service.crud.*;
import ru.kpfu.itis.shkalin.util.FileUpDownUtil;
import ru.kpfu.itis.shkalin.util.PostgresConnectionUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;

@WebListener
public class InitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        Connection connection = PostgresConnectionUtil.getConnection();

        sc.setAttribute("converter", new ConverterServiceImpl());

        sc.setAttribute("bookDao", new BookDao(connection));
        sc.setAttribute("commentDao", new CommentDao(connection));
        sc.setAttribute("postDao", new PostDao(connection));
        sc.setAttribute("roleDao", new RoleDao(connection));
        sc.setAttribute("accountDao", new AccountDao(connection));

        sc.setAttribute("bookCrudService",
                new BookCrudService(
                        (BookDao) sc.getAttribute("bookDao"),
                        (ConverterServiceImpl) sc.getAttribute("converter")));

        sc.setAttribute("commentCrudService",
                new CommentCrudService(
                        (CommentDao) sc.getAttribute("commentDao"),
                        (ConverterServiceImpl) sc.getAttribute("converter")));

        sc.setAttribute("postCrudService",
                new PostCrudService(
                        (PostDao) sc.getAttribute("postDao"),
                        (ConverterServiceImpl) sc.getAttribute("converter")));

        sc.setAttribute("roleCrudService",
                new RoleCrudService(
                        (RoleDao) sc.getAttribute("roleDao"),
                        (ConverterServiceImpl) sc.getAttribute("converter")));

        sc.setAttribute("accountCrudService",
                new AccountCrudService(
                        (AccountDao) sc.getAttribute("accountDao"),
                        (ConverterServiceImpl) sc.getAttribute("converter")));

        sc.setAttribute("fileUpDownUtil",
                new FileUpDownUtil());
    }
}
