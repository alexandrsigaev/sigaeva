package ru.job4j.httpexample.controller;

import ru.job4j.httpexample.model.User;
import ru.job4j.httpexample.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        String par = req.getParameter("id");
        User user = logic.findById(Integer.parseInt(par));

        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>Update</title>"
                + "</head>"
                + "<body>");
        sb.append("<h1>Update User!</h1>");
        sb.append("<form action='")
                .append(req.getContextPath())
                .append("/list?action=update' method='post'>")
                .append("<table>").append("<tr><th>Name:<th></tr>")
                .append("<td><input type='text' name='name' value='").append(user.getName()).append("'><br></td>")
                .append("<tr><th>Login:<th></tr>")
                .append("<td><input type='text' name='login' value='").append(user.getLogin()).append("'></td>")
                .append("<tr><th>Password:<th></tr>")
                .append("<td><input type='password' name='password' value='").append(user.getPassword()).append("'></td>")
                .append("<tr><th>E-mail<th></tr>")
                .append("<td><input type='text' name='email' value='").append(user.getEmail()).append("'></td>")
                .append("</table>")
                .append("<input type='hidden' name='id' value='").append(user.getId()).append("'/>")
                .append("<br>")
                .append("<input type='submit' value='update'>")
                .append("</form>");
        sb.append("</body></html>");
        writer.write(sb.toString());
    }
}
