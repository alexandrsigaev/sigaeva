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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*"})
@RunWith(PowerMockRunner.class)
@PrepareForTest(UserServiceImpl.class)
public class ExitControllerTest {

    @Mock
    private HttpServletRequest req;
    @Mock
    private HttpServletResponse resp;
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
        new ExitController().doGet(req, resp);
        verify(session, atLeastOnce()).invalidate();
        verify(resp, atLeastOnce()).sendRedirect(String.format("%s/signIn", req.getContextPath()));
        new ExitController().doGet(req, resp);
    }
}
