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

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*"})
@RunWith(PowerMockRunner.class)
@PrepareForTest(UserServiceImpl.class)
public class UserControllerTest {

    @Mock
    private HttpServletRequest req;
    @Mock
    private HttpServletResponse resp;
    @Mock
    private RequestDispatcher dispatcher;
    @Mock
    private HttpSession session;


    private UserService userService;

    @Before
    public void setUp() {
        this.userService = new UserServiceStubImpl();
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(UserServiceImpl.class);
        when(UserServiceImpl.getInstance()).thenReturn(userService);
    }

    @Test
    public void whenDoGet() throws ServletException, IOException {
        when(req.getSession()).thenReturn(session);
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        new UserController().doGet(req, resp);
        verify(req, atLeastOnce()).getRequestDispatcher("/WEB-INF/jsp/UserView.jsp");
        verify(dispatcher, atLeastOnce()).forward(req, resp);
    }

    @Test
    public void whenAddUserThanStoreIt() throws IOException, ServletException {
        when(req.getParameter("action")).thenReturn("create");
        var params = Map.of(
                "name", new String[]{"Aleksandr Sigaev"},
                "login", new String[]{"sanya"},
                "password", new String[]{"qwerty"},
                "email", new String[]{"sanya@mail.ru"},
                "role", new String[]{"user"}
        );
        when(req.getParameterMap()).thenReturn(params);
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        new UserController().doPost(req, resp);
        verify(req, atLeastOnce()).getRequestDispatcher("/WEB-INF/jsp/ChangeResponseUser.jsp");
        verify(dispatcher, atLeastOnce()).forward(req, resp);
        assertThat(userService.findAll().iterator().next().getName(), is("Aleksandr Sigaev"));
        assertThat(userService.findAll().iterator().next().getLogin(), is("sanya"));
        assertThat(userService.findAll().iterator().next().getPassword(), is("qwerty"));
        assertThat(userService.findAll().iterator().next().getEmail(), is("sanya@mail.ru"));
        assertThat(userService.findAll().iterator().next().getRole(), is("user"));

    }

    @Test
    public void whenUpdateUserThenInStoreUpdateIt() throws IOException, ServletException {
        var originParams = Map.of(
                "name", new String[]{"Aleksandr Sigaev"},
                "login", new String[]{"sanya"},
                "password", new String[]{"qwerty"},
                "email", new String[]{"sanya@mail.ru"},
                "role", new String[]{"user"}
        );
        userService.add(originParams);
        when(req.getParameter("action")).thenReturn("update");

        var updateParams = Map.of(
                "id", new String[] {"1"},
                "name", new String[]{"Mikhail Ivanov"},
                "login", new String[]{"sanya"},
                "password", new String[]{"qwerty123"},
                "email", new String[]{"sany4a@mail.ru"},
                "role", new String[]{"user"}
        );
        when(req.getParameterMap()).thenReturn(updateParams);
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        new UserController().doPost(req, resp);
        verify(req, atLeastOnce()).getRequestDispatcher("/WEB-INF/jsp/ChangeResponseUser.jsp");
        verify(dispatcher, atLeastOnce()).forward(req, resp);
        assertThat(userService.findAll().iterator().next().getName(), is("Mikhail Ivanov"));
        assertThat(userService.findAll().iterator().next().getLogin(), is("sanya"));
        assertThat(userService.findAll().iterator().next().getPassword(), is("qwerty123"));
        assertThat(userService.findAll().iterator().next().getEmail(), is("sany4a@mail.ru"));
        assertThat(userService.findAll().iterator().next().getRole(), is("user"));
    }

    @Test
    public void whhenDeleteUserThenInStoreDeleteIt() throws IOException, ServletException {
        var originParams = Map.of(
                "name", new String[]{"Aleksandr Sigaev"},
                "login", new String[]{"sanya"},
                "password", new String[]{"qwerty"},
                "email", new String[]{"sanya@mail.ru"},
                "role", new String[]{"user"}
        );
        userService.add(originParams);
        var deleteParams = Map.of(
                "id", new String[]{"1"},
                "name", new String[]{"Aleksandr Sigaev"}
                );
        when(req.getParameter("action")).thenReturn("delete");
        when(req.getParameterMap()).thenReturn(deleteParams);
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        new UserController().doPost(req, resp);
        verify(req, atLeastOnce()).getRequestDispatcher("/WEB-INF/jsp/ChangeResponseUser.jsp");
        verify(dispatcher, atLeastOnce()).forward(req, resp);
        assertTrue(userService.findAll().isEmpty());
    }
}
