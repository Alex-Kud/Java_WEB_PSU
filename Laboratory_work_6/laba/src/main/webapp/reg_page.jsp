<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Objects" %>
<%
    if (Objects.equals((String) session.getAttribute("AUTH"), "TRUE")){
        if (Objects.equals((String) session.getAttribute("STATUS"), "user")) {
            String url = "/show_my_questionnaires/";
            url += session.getAttribute("LOGIN");
            response.sendRedirect(url);
        }
        else if (Objects.equals((String) session.getAttribute("STATUS"), "admin"))
            response.sendRedirect("/show_users");
    }
%>
<html>
<head>
    <title>Регистрация</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="styles/style_reg.css">
    <script src="scripts/Font.js" type="text/javascript"></script>
</head>
<body>
<div id="container">
    <h1>Регистрация</h1>
    <div id="non-id">
        <form method="post" action="reg" id="reg_form">
            <p>${login_error}</p>
            <table>
                <tr>
                    <td>Имя</td>
                    <td><input type="text" name="firstName" id="firstName" required></td>
                </tr>
                <tr>
                    <td>Фамилия</td>
                    <td><input type="text" name="lastName" id="lastName" required></td>
                </tr>
                <tr>
                    <td>Логин</td>
                    <td><input type="text" name="login" id="login" required></td>
                </tr>
                <tr>
                    <td>Пароль</td>
                    <td><input type="password" name="password" id="password" required></td>
                </tr>
                <tr>
                    <td>E-mail</td>
                    <td><input type="text" name="email" id="email" required></td>
                </tr>
                <tr>
                    <td>Пол</td>
                    <td><select name="gender" id="gender" required>
                        <option value="null">Выберите из списка</option>
                        <option value="Девушка">Женский</option>
                        <option value="Парень">Мужской</option>
                    </select></td>
                </tr>
                <tr>
                    <td>Возраст</td>
                    <td><input type="text" name="age" id="age" required></td>
                </tr>
            </table>

            <input type="submit" value="Зарегистрироваться" id="button_reg">
        </form>
        <p>Уже зарегистрированы? <a href="login_page.jsp">Войти</a></p>
    </div>
</div>
</body>
</html>
