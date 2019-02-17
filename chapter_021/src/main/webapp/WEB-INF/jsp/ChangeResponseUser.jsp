<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Change</title>
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form-style-2-heading">
    <h2>
        <c:out value='${massage}'/>
    </h2>

    <form action='${pageContext.servletContext.contextPath}/list' method='get'>
        <input class="input-field" type='submit' value='OK'>
    </form>
</div>
</body>
</html>
