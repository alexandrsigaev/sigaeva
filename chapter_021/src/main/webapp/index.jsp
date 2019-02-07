<%@ page import="ru.job4j.httpexample.model.User" %>
<%@ page import="ru.job4j.httpexample.service.UserService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>index</title>
    <link href='css/style.css' rel='stylesheet' type='text/css'>
</head>
<body>
<%!private final UserService service = UserService.getInstance();%>
<form action="<%=request.getContextPath()%>/list" method="post"></form>
<div class="form-style-2">
    <table>
        <div class="form-style-2-heading">
            <h1>All user</h1>
        </div>
        <tr>
            <th>name</th>
            <th>login</th>
            <th>email</th>
            <th>password</th>
        </tr>
        <%for (User user: service.findAll()) { %>
        <tr>
            <td><%=user.getName()%></td>
            <td><%=user.getLogin()%></td>
            <td><%=user.getEmail()%></td>
            <td><%=user.getPassword()%></td>
            <td>
                <form action= '<%=request.getContextPath()%>/update' method='get'>
                <input type='hidden' name='id' value='<%=user.getId()%>'/>
                <input type= 'submit' value='update'/>
                </form>
            </td>
            <td>
                <form action='<%=request.getContextPath()%>/list?action=delete' method='post'>
                <input type='hidden' action='delete' name='id' value='<%=user.getId()%>'/>
                <input type= 'submit' value='delete'/>
                </form>
            </td>
        </tr>
        <%}%>
    </table>
    <form action= '<%=request.getContextPath()%>/create' method='get'>
    <input type='submit' value='create'>
    </form>
    </div>
</body>
</html>