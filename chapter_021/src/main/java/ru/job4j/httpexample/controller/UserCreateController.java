package ru.job4j.httpexample.controller;

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
@WebServlet(urlPatterns = "/create")
public class UserCreateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>Create</title>"
                + "</head>"
                + "<body>");
        sb.append("<h1>Create User!</h1>");
        sb.append("<form action='").append(req.getContextPath()).append("/list?action=create' method='post'>")
                .append("<table>")
                .append("<tr><th>Name:<th></tr>")
                .append("<td><input type='text' name='name'><br></td>")
                .append("<tr><th>Login:<th></tr>")
                .append("<td><input type='text' name='login'></td>")
                .append("<tr><th>Password:<th></tr>")
                .append("<td><input type='password' name='password'></td>")
                .append("<tr><th>E-mail<th></tr>")
                .append("<td><input type='text' name='email' ></td>")
                .append("</table>").append("<br>")
                .append("<input type='submit' value='create'>")
                .append("</form>");
        sb.append("</body></html>");
        writer.write(sb.toString());
    }
}
