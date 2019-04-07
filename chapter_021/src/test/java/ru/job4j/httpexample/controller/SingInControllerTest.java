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
public class SingInControllerTest {

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
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        new SingInController().doGet(req, resp);
        verify(req, atLeastOnce()).getRequestDispatcher("/WEB-INF/jsp/Authorisation.jsp");
        verify(dispatcher, atLeastOnce()).forward(req, resp);
    }

    @Test
    public void whenUserIsCredentional() throws ServletException, IOException {
        var params = Map.of(
                "name", new String[]{"Aleksandr Sigaev"},
                "login", new String[]{"sanya"},
                "password", new String[]{"qwerty"},
                "email", new String[]{"sanya@mail.ru"},
                "role", new String[]{"user"}
        );
        userService.add(params);
        when(req.getParameter("login")).thenReturn("sanya");
        when(req.getParameter("password")).thenReturn("qwerty");
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(req.getSession()).thenReturn(session);
        new SingInController().doPost(req, resp);
        verify(req, atLeastOnce()).getRequestDispatcher(String.format("%s/list", req.getContextPath()));
    }

    @Test
    public void whenUserIsNotCredentional() throws ServletException, IOException {
        when(req.getParameter("login")).thenReturn("sanya");
        when(req.getParameter("password")).thenReturn("qwerty");
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(req.getSession()).thenReturn(session);
        new SingInController().doPost(req, resp);
        verify(req, atLeastOnce()).setAttribute("error", "Credentional is invalid");
    }
}