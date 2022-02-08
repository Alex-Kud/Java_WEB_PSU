<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Objects" %>
<%
    if (!Objects.equals((String) session.getAttribute("AUTH"), "TRUE"))
        response.sendRedirect("login_page.jsp");
%>
<html>
<head>
    <title>Познакомиться</title>
    <link rel="stylesheet" href="styles/style_user_account.css">
</head>
<body>
<a href="show_my_questionnaires/<%=session.getAttribute("LOGIN")%>">В личный кабинет</a>
<div id="questionnaires">
    <h3>Список анкет</h3>
    <c:forEach var="par" items="${allQuestionnaires}">
        <div id="questionnaire">
            <table>
                <tr>
                    <th><a href="show_my_questionnaires/${par.get("login")}">${par.get("login")}</a></th>
                </tr>
                <tr>
                    <td>Имя: </td>
                    <td>${par.get("firstName")}</td>
                </tr>
                <tr>
                    <td>Фамилия: </td>
                    <td>${par.get("lastName")}</td>
                </tr>
                <tr>
                    <td>Пол: </td>
                    <td>${par.get("gender")}</td>
                </tr>
                <tr>
                    <td>Почта: </td>
                    <td>${par.get("email")}</td>
                </tr>
                <tr>
                    <td>Возраст: </td>
                    <td>${par.get("age")}</td>
                </tr>
                <tr>
                    <td>Работа/учеба: </td>
                    <td>${par.get("job")}</td>
                </tr>
                <tr>
                    <td>Деятельность: </td>
                    <td>${par.get("activity")}</td>
                </tr>
                <tr>
                    <td>Интересы: </td>
                    <td>${par.get("interests")}</td>
                </tr>
                <tr>
                    <td>Любимая музыка: </td>
                    <td>${par.get("fav_music")}</td>
                </tr>
                <tr>
                    <td>Любимые книги: </td>
                    <td>${par.get("books")}</td>
                </tr>
                <tr>
                    <td>Соц. сети: </td>
                    <td>${par.get("socials")}</td>
                </tr>
                <tr>
            </table>
            <iframe id="video" seamless allowfullscreen scrolling="no" src="${par.get("video")}"></iframe>
        </div>
    </c:forEach>
</div>
</body>
</html>
