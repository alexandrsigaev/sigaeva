<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change</title>
    <link href='css/style.css' rel='stylesheet' type='text/css'>
</head>
<body>
<div class="form-style-2-heading">
    <h2>
        <%=request.getAttribute("massage")%>
    </h2>

    <form action='<%=request.getContextPath()%>/' method='get'>
        <input class="input-field" type='submit' value='OK'>
    </form>
</div>
</body>
</html>
