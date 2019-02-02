<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create</title>
    <link href='css/style.css' rel='stylesheet' type='text/css'>
</head>
<body>
<form action="<%=request.getContextPath()%>/list" method="post"></form>
<div class="form-style-2-heading">
    <h1>Create User!</h1>
</div>
    <div class="form-style-2">
        <form action='<%=request.getContextPath()%>/list?action=create' method='post'>
        <table>
            <tr>
                <th>Name:<th>
            </tr>
            <td>
                <label>
                    <input class="input-field" type='text' name='name'/>
                </label>
            </td>
            <tr>
                <th>Login:<th>
            </tr>
            <td>
                <label>
                    <input class="input-field" type='text' name='login'/>
                </label>
            </td>
            <tr>
                <th>Password:<th>
            </tr>
            <td>
                <label>
                    <input class="input-field" type='password' name='password'/>
                </label>
            </td>
            <tr>
                <th>E-mail<th>
            </tr>
            <td>
                <label>
                    <input class="input-field" type='text' name='email'/>
                </label>
            </td>
        </table>
            <input type='submit' value='create'/>
        </form>
    </div>
</form>
</body>
</html>
