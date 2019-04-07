package ru.job4j.httpexample.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.httpexample.model.User;
import ru.job4j.httpexample.service.UserService;
import ru.job4j.httpexample.service.UserServiceImpl;
import ru.job4j.httpexample.service.UserServiceStubImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*"})
@RunWith(PowerMockRunner.class)
@PrepareForTest(UserServiceImpl.class)
public class UserUpdateControllerTest {

    @Mock
    private HttpServletRequest req;
    @Mock
    private HttpServletResponse resp;
    @Mock
    private HttpSession session;
    @Mock
    private RequestDispatcher dispatcher;

    private UserService userService;

    @Before
    public void setUp() {
        this.userService = new UserServiceStubImpl();
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(UserServiceImpl.class);
        when(UserServiceImpl.getInstance()).thenReturn(userService);
    }

    @Test
    public void whenDoGet() throws IOException, ServletException {
        var params = Map.of(
                "name", new String[]{"Aleksandr Sigaev"},
                "login", new String[]{"sanya"},
                "password", new String[]{"qwerty"},
                "email", new String[]{"sanya@mail.ru"},
                "role", new String[]{"user"}
        );
        User user = new User("Aleksandr Sigaev", "sanya", "qwerty", "sanya@mail.ru", "user");
        userService.add(params);
        when(req.getParameter("id")).thenReturn("1");
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(req.getSession()).thenReturn(session);
        new UserUpdateController().doGet(req, resp);
        verify(req, atLeastOnce()).setAttribute("user", user);
        verify(req, atLeastOnce()).getRequestDispatcher("/WEB-INF/jsp/UpdateUser.jsp");
        verify(dispatcher, atLeastOnce()).forward(req, resp);
    }

}