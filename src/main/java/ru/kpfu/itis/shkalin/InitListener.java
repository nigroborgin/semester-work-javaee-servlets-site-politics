package ru.kpfu.itis.shkalin;

import ru.kpfu.itis.shkalin.dao.*;
import ru.kpfu.itis.shkalin.service.converter.ConverterServiceImpl;
import ru.kpfu.itis.shkalin.service.сrud.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();

        sc.setAttribute("converter", new ConverterServiceImpl());

        sc.setAttribute("bookDao", new BookDao());
        sc.setAttribute("commentDao", new CommentDao());
        sc.setAttribute("postDao", new PostDao());
        sc.setAttribute("roleDao", new RoleDao());
        sc.setAttribute("userDao", new UserDao());

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

        sc.setAttribute("userCrudService",
                new UserCrudService(
                        (UserDao) sc.getAttribute("userDao"),
                        (ConverterServiceImpl) sc.getAttribute("converter")));
    }
}
