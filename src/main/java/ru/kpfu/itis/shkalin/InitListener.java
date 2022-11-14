package ru.kpfu.itis.shkalin;

import ru.kpfu.itis.shkalin.service.PostListService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("postListService", new PostListService());
    }
}
