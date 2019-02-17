package ru.job4j.httpexample.controller;

import ru.job4j.httpexample.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", logic.findAll());
        req.getRequestDispatcher("/WEB-INF/jsp/UserView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action");
        Map<String, String[]> parameters = req.getParameterMap();
        String result;
        result = actions.execute(action, parameters);
        req.setAttribute("massage", result);
        req.getRequestDispatcher("/WEB-INF/jsp/ChangeResponseUser.jsp").forward(req, resp);
    }


    private class DispatchActions {
        private HashMap<String, Function<Map<String, String[]>, String>> dispatch = new HashMap<>();

        private DispatchActions() {
            dispatch.put(
                    "create", stringMap -> {
                        String name = stringMap.get("name")[0];
                        if (!logic.add(stringMap)) {
                            return "User with this login is exists or invalid value entered!";
                        }
                        return String.format("User: %s added!", name);
                    }
            );
            dispatch.put(
                    "update",
                    stringMap -> {
                        String name = stringMap.get("name")[0];
                        if (!logic.update(stringMap)) {
                            return "User not found or invalid value entered!";
                        }
                        return String.format("User: %s updated!", name);
                    }
            );
            dispatch.put(
                    "delete",
                    stringMap -> {
                        if (!logic.delete(stringMap)) {
                            return "User not found!";
                        }
                        return String.format("User: %s deleted!", stringMap.get("name")[0]);
                    }
            );
        }

        private String execute(String name, Map<String, String[]> param) {
            return dispatch.get(name).apply(param);
        }
    }
}
