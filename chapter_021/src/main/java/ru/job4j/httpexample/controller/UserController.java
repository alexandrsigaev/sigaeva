package ru.job4j.httpexample.controller;

import ru.job4j.httpexample.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 26.01.2019
 */
@WebServlet(urlPatterns = "/list", loadOnStartup = 1)
public class UserController extends HttpServlet {

    private final UserService logic = UserService.getInstance();
    private final DispatchActions actions = new DispatchActions();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        Map<String, String[]> parameters = req.getParameterMap();
        String result;
        try {
            result = actions.execute(action, parameters);
        } catch (NullPointerException e) {
            result = "error: incorrect request";
        }
        PrintWriter writer = resp.getWriter();
        writer.append(this.getHtml(
                result,
                "<form action='" + req.getContextPath() + "/' method='get'>"
                        + "<input type='submit' value='OK'>"
                        + "</form>"));
        writer.flush();
    }

    private String getHtml(String message, String form) {
        return "<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>User</title>"
                + "</head>"
                + "<body>"
                + message
                + form
                + "</body>"
                + "</html>";
    }

    private class DispatchActions {
        private HashMap<String, Function<Map<String, String[]>, String>> dispatch = new HashMap<>();

        private DispatchActions() {
            dispatch.put(
                    "create", stringMap -> {
                        if (!logic.add(stringMap)) {
                            return "User with this login is exists";
                        }
                        return "User added!";
                    }
            );
            dispatch.put(
                    "update",
                    stringMap -> {
                        if (!logic.update(stringMap)) {
                            return "User not found!";
                        }
                        return "User updated!";
                    }
            );
            dispatch.put(
                    "delete",
                    stringMap -> {
                        if (!logic.delete(stringMap)) {
                            return "User not found!";
                        }
                        return String.format("User with ID %s deleted!", stringMap.get("id")[0]);
                    }
            );
        }

        private String execute(String name, Map<String, String[]> param) {
            return dispatch.get(name).apply(param);
        }
    }
}
