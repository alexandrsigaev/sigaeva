<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"></head>
<body>
<div class="form-style-2-heading">
    <h1>Update User!</h1>
</div>
<div class="form-style-2">
    <form action='${pageContext.servletContext.contextPath}/list?action=update' method='post'>
    <table>
        <tr>
            <th>Name:</th>
        </tr>
        <td>
            <label>
                <input class="input-field" type='text' name='name' value='${user.name}'/>
            </label>
        </td>
        <tr>
            <th>Login:</th>
        </tr>
        <td>
            <label>
                <input class="input-field" type='text' name='login' value='${user.login}'/>
            </label>
        </td>
        <tr>
            <th>Password:</th>
        </tr>
        <td>
            <label>
                <input class="input-field" type='password' name='password' value='${user.password}'/>
            </label>
        </td>
        <tr>
            <th>E-mail</th>
        </tr>
        <td>
            <label>
                <input class="input-field" type='text' name='email' value='${user.email}'/>
            </label>
        </td>
    </table>
    <input type='hidden' name='id' value='${user.id}'/><br>
    <input type='submit' value='update'/>
    </form>
</div>
</body>
</html>
