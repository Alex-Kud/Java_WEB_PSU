<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Objects" %>
<%
    if (Objects.equals((String) session.getAttribute("AUTH"), "TRUE"))
        response.sendRedirect("user_account.jsp");
%>
<html>
<head>
    <title>Вход</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="styles/style_login.css">
    <script src="scripts/Font.js" type="text/javascript"></script>
</head>
<body>
<h1>Вход</h1>
<div id="non-id">
    <form method="post" action="auth" id="log_form">
        <p>${auth_error}</p>
        <table>
            <tr>
                <td>Логин</td>
                <td><input type="text" required name="login" id="login"></td>
            </tr>
            <tr>
                <td>Пароль</td>
                <td><input type="password" name="password" id="password"></td>
            </tr>
        </table>
        <input type="submit" value="Войти" id="button_log">
    </form>
    <p>Не зарегистрированы?<a href="reg_page.jsp">Регистрация</a></p>
</div>
</body>
</html>