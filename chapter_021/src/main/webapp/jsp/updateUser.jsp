<%@ page import="ru.job4j.httpexample.model.User" %>
<%@ page import="ru.job4j.httpexample.service.UserService" %><%--
  Created by IntelliJ IDEA.
  User: sanya
  Date: 02.02.2019
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update</title>
    <link href='css/style.css' rel='stylesheet' type='text/css'>
</head>
<body>
<%final UserService logic = UserService.getInstance();
    String par = request.getParameter("id");
    User user = logic.findById(Integer.parseInt(par));%>
<div class="form-style-2-heading">
    <h1>Update User!</h1>
</div>
<div class="form-style-2">
    <form action='<%=request.getContextPath()%>/list?action=update' method='post'>
    <table>
        <tr>
            <th>Name:</th>
        </tr>
        <td>
            <label>
                <input class="input-field" type='text' name='name' value='<%=user.getName()%>'/>
            </label>
        </td>
        <tr>
            <th>Login:</th>
        </tr>
        <td>
            <label>
                <input class="input-field" type='text' name='login' value='<%=user.getLogin()%>'/>
            </label>
        </td>
        <tr>
            <th>Password:</th>
        </tr>
        <td>
            <label>
                <input class="input-field" type='password' name='password' value='<%=user.getPassword()%>'/>
            </label>
        </td>
        <tr>
            <th>E-mail</th>
        </tr>
        <td>
            <label>
                <input type='text' name='email' value='<%=user.getEmail()%>'/>
            </label>
        </td>
    </table>
    <input type='hidden' name='id' value='<%=user.getId()%>'/><br>
    <input type='submit' value='update'/>
    </form>
</div>
</body>
</html>
