package ru.job4j.httpexample.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 01.02.2019
 */
@WebServlet(urlPatterns = "/create")
public class UserCreateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("/WEB-INF/jsp/CreateUser.jsp").forward(req, resp);
    }
}
