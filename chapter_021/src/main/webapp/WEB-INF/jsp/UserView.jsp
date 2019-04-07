<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>UserList</title>
    <link rel='stylesheet' type='text/css' href='<c:url value="/resources/css/style.css"/>'>
</head>
<body>

<form action="${pageContext.servletContext.contextPath}/list" method="post"></form>
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
            <th>role</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.login}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.password}"/></td>
                <td><c:out value="${user.role}"/></td>
                <c:if test="${login_user.role == 'admin' || login_user.id == user.id}">
                    <td>
                        <form action='${pageContext.servletContext.contextPath}/update' method='get'>
                            <input type='hidden' name='id' value='<c:out value="${user.id}"/>'/>
                            <input type='submit' value='update'/>
                        </form>
                    </td>
                    <td>
                        <form action='${pageContext.servletContext.contextPath}/list?action=delete' method='post'>
                            <input type='hidden' name='id' value='<c:out value="${user.id}"/>'/>
                            <input type='hidden' name='name' value='<c:out value="${user.name}"/>'/>
                            <input type='submit' value='delete'/>
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${login_user.role == 'admin' || login_user.id == user.id}">
        <form action='${pageContext.servletContext.contextPath}/create' method='get'>
            <input type='submit' value='create'>
        </form>
    </c:if>
    <br>
    <form action='${pageContext.servletContext.contextPath}/exit' method='get'>
        <input type='submit' value='exit'>
    </form>
</div>
</body>
</html>