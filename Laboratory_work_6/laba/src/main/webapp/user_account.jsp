<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <title>Страница пользователя ${myQuestionnaires[0].get("login")} </title>
  <link rel="stylesheet" href="\styles/style_user_account.css">
  <script src="\scripts/Font.js" type="text/javascript"></script>
  <script src="https://api-maps.yandex.ru/2.1/?apikey=4bda9320-f45b-46b6-8742-1cb1cb7db2a9&lang=ru_RU" type="text/javascript"></script>
</head>
<body>

<h1>Страница пользователя <a href="\show_my_questionnaires/${myQuestionnaires[0].get("login")}">${myQuestionnaires[0].get("login")}</a></h1>
<div id="navigate">
  Вы вошли как: <a id="hello" href="\show_my_questionnaires/<%=session.getAttribute("LOGIN")%>"><%=(String) session.getAttribute("LOGIN")%></a><br>
  <a href="\add_questionnaire.jsp" id="add_questionnaire">Добавить анкету</a>
  <br>
  <a href="\show_questionnaires" id="show_questionnaires">Познакомиться</a>
  <form action="\logout" method="post">
    <input type="submit" value="Выход" id="button_exit">
  </form>
</div>
<div id="questionnaires">
  <c:choose>
  <c:when test="${myQuestionnaires[0].get(\"flag\") == \"true\"}">
    <h3>Анкеты</h3>
    <c:forEach var="par" items="${myQuestionnaires}">
      <div id="questionnaire">
        <table>
          <tr>
            <th><a href="\show_my_questionnaires/${par.get("login")}">${par.get("login")}</a></th>
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
        <iframe id="video" seamless allowfullscreen scrolling="no" src="${par.get("video")}"><p>Видео не найдено</p></iframe>
      </div>
    </c:forEach>
  </c:when>
    <c:otherwise>
      <p>Анкеты не найдены</p>
    </c:otherwise>
  </c:choose>
  <div id="qr">
    <h2>QR-код</h2>
  <%--Проверка на то, что ты смотришь свою страницу--%>
    <img src="https://api.qrserver.com/v1/create-qr-code/?size=120x120&data=http://localhost:8080/show_my_questionnaires/${myQuestionnaires[0].get("login")}" />
    <div class="share-buttons">
      <button
          class="facebook"
          onClick="window.open('https://www.facebook.com/sharer.php?u=https://api.qrserver.com/v1/create-qr-code/?data=http://localhost:8080/show_my_questionnaires/${myQuestionnaires[0].get("login")}','sharer','status=0,toolbar=0,width=650,height=500');"
          title="Поделиться в Facebook">
      </button>
      <button
          class="vkontakte"
          onClick="window.open('https://vkontakte.ru/share.php?url=https://api.qrserver.com/v1/create-qr-code/?data=http://localhost:8080/show_my_questionnaires/${myQuestionnaires[0].get("login")}','sharer','status=0,toolbar=0,width=650,height=500');"
          title="Поделиться Вконтакте">
      </button>
      <button
          class="telegram"
          onClick="window.open('https://telegram.me/share/url?url=https://api.qrserver.com/v1/create-qr-code/?data=http://localhost:8080/show_my_questionnaires/${myQuestionnaires[0].get("login")}','sharer','status=0,toolbar=0,width=650,height=500');"
          title="Поделиться в Телеграм">
      </button>
    </div>
  </div>
  <div id="map"></div>
  <input type="text" id="help" value="${myQuestionnaires[0].get("login")}">
</div>
<script src="\scripts/Map.js" type="text/javascript"></script>
</body>
</html>