<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Objects" %>
<%
    if (!Objects.equals((String) session.getAttribute("AUTH"), "TRUE"))
        response.sendRedirect("login_page.jsp");
    else if (Objects.equals((String) session.getAttribute("STATUS"), "admin"))
        response.sendRedirect("/show_users");
%>
<html>
<head>
    <title>Добавить анкету</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="styles/style.css">
    <script src="scripts/Font.js" type="text/javascript"></script>
</head>
<body>
<h1>Заполните информацию о себе</h1>
<div id="questionnaire">
        <form action="add_questionnaire" method="post" id="add_form">
            <a href="show_my_questionnaires/<%=session.getAttribute("LOGIN")%>">В личный кабинет</a>
            <p>Город</p>
            <input type="text" required placeholder="Город" id="city" name="city">
            <p>Место работы/учебы</p>
            <input type="text" required placeholder="Место работы/учебы" id="job" name="job">
            <p>Деятельность</p>
            <input type="text" required placeholder="Деятельность" id="activity" name="activity">
            <p>Интересы</p>
            <input type="text" required placeholder="Интересы" id="interests" name="interests">
            <p>Любимая музыка</p>
            <input type="text" placeholder="Любимая музыка" id="fav_music" name="fav_music">
            <p>Любимые книги</p>
            <input type="text" placeholder="Любимые книги" id="books" name="books">
            <p>Ссылки на другие соцсети</p>
            <input type="text" placeholder="Ссылки на другие соц.сети" id="socials" name="socials"><br>
            <p>Добавить видео с Youtube</p>
            <input type="text" required placeholder="Ссылка" id="video" name="video">
            <input type="number" placeholder="Минуты" id="minute" name="minute" min="0" max="">
            <input type="number" placeholder="Секунды" id="second" name="second" min="0" max="60">
            <br>
            <input type="submit" value="Добавить анкету" id="button_add">
        </form>
</div>
</body>
</html>
