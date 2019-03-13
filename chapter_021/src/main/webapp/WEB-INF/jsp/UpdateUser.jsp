<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>
<div class="form-style-2-heading">
    <h1>Update User: <c:out value="${user.login}"/></h1>
</div>
<div class="form-style-2">
    <form action='${pageContext.servletContext.contextPath}/list?action=update' method='post'>
        <input type="hidden" name='login' value='${user.login}'/>
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
            <tr>
                <th>Role</th>
            </tr>
            <c:choose >
                <c:when test="${login_user.role == 'admin'}">
                <td>
                    <select name="role">
                        <option value="admin">Administrator</option>
                        <option value="user">User</option>
                    </select>
                </td>
                </c:when>
                <c:otherwise>
                    <td>
                    <input type="hidden" name="role" value="${user.role}"/>
                    <c:out value="${user.role}"/>
                    </td>
                </c:otherwise>
            </c:choose>
        </table>
        <input type='hidden' name='id' value='${user.id}'/><br>
        <input type='submit' value='update'/>
    </form>
</div>
</body>
</html>
