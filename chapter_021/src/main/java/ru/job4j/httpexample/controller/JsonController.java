package ru.job4j.httpexample.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.httpexample.model.User;
import ru.job4j.httpexample.service.UserService;
import ru.job4j.httpexample.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/json")
public class JsonController extends HttpServlet {

    private final UserService logic = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = logic.findAll();
        resp.setContentType("text/json");
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        objectMapper.writeValue(printWriter, users);
        printWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String jsonUser = reader.readLine();
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(jsonUser, User.class);
        boolean res = this.logic.add(user);
    }
}
