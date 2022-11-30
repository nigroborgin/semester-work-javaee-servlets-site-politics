package ru.kpfu.itis.shkalin.filter;

import ru.kpfu.itis.shkalin.exception.NoRigntsException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "securityFilter",
        urlPatterns = {"/users/delete/",
                "/books/add/", "/books/edit/", "/books/delete/",
                "/articles/add/", "/articles/edit/", "/articles/delete/"})
public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String uri = req.getRequestURI();
        HttpSession session = req.getSession(false);
        if (session != null
                && !session.getAttribute("role").equals("admin")) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have the rights for open this page.");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
