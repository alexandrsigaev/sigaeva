package ru.job4j.httpexample.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "Authorisation")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filter) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getRequestURI().contains("/signIn")) {
            filter.doFilter(req, resp);
        } else {
            HttpSession session = request.getSession();
            HttpServletResponse response = (HttpServletResponse) resp;
            synchronized (session) {
                if (session.getAttribute("login") == null) {
                    response.sendRedirect((String.format("%s/signIn", request.getContextPath())));
                    return;
                }
            }
            filter.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
