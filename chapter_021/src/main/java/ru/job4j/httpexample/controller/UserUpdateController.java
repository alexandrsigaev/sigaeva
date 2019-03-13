package ru.job4j.httpexample.controller;

import ru.job4j.httpexample.model.User;
import ru.job4j.httpexample.service.UserService;

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
@WebServlet(urlPatterns = "/update")
public class UserUpdateController extends HttpServlet {

    private final UserService logic = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String par = req.getParameter("id");
        User user = logic.findById(Integer.parseInt(par));
        req.setAttribute("user", user);
        req.setAttribute("login_user", logic.findUserByLogin((String) req.getSession().getAttribute("login")));
        req.getRequestDispatcher("/WEB-INF/jsp/UpdateUser.jsp").forward(req, resp);
    }
}
