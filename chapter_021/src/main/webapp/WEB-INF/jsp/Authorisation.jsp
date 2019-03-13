<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Authorisation</title>
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>

<div class="form-style-2">
    <form action="${pageContext.servletContext.contextPath}/signIn" method="post">
        <table>
            <tr>
                <td>Login</td>
                <td>
                    <label>
                        <input class="input-field" type="text" name="login">
                    </label>
                </td>
            </tr>
            <tr>
                <td>Password</td>
                <td>
                    <label>
                        <input class="input-field" type="password" name="password">
                    </label>
                </td>
            </tr>
        </table>
        <input type="submit">
    </form>
</div>


</body>
</html>
