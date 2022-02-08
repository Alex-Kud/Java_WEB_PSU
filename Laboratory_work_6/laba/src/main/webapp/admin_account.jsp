<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Objects" %>

<%
    if (!Objects.equals((String) session.getAttribute("AUTH"), "TRUE"))
        response.sendRedirect("login_page.jsp");
    else if (!Objects.equals((String) session.getAttribute("STATUS"), "admin")) {
        String url = "/show_my_questionnaires/";
        url += session.getAttribute("LOGIN");
        response.sendRedirect(url);
    }

%>
<html>
<head>
    <title>Кабинет администратора</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="styles/style_admin.css">
    <script src="scripts/Font.js" type="text/javascript"></script>
</head>
<body>
<h1>Панель администратора</h1>
<div id="navigate">
    <div id="cent">
        <p>Здравствуйте, Админ <%=(String) session.getAttribute("LOGIN")%></p>
        <form action="logout" method="post">
            <input type="submit" value="Выход" id="button_exit">
        </form>
    </div>
</div>
<div id="all_users">
<table border="1" width="600">
    <thead>
        <tr>
            <th>Id</th>
            <th>Login</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Age</th>
            <th>Gender</th>
            <th>Email</th>
            <th>Password</th>
            <th>Role</th>
            <th>Delete</th>
            <th>Edit</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="par" items="${users}">
        <tr>
            <td>${par.get("id")}</td>
            <td>${par.get("login")}</td>
            <td>${par.get("firstName")}</td>
            <td>${par.get("lastName")}</td>
            <td>${par.get("age")}</td>
            <td>${par.get("gender")}</td>
            <td>${par.get("email")}</td>
            <td>${par.get("password")}</td>
            <td>${par.get("role")}</td>

            <td>
                <form action="delete" method="get">
                    <input type="hidden" name="id" value="${par.get("id")}">
                    <button id="button_delete" type="submit">Удалить</button>
                </form>
            </td>

            <td>
                <form action="role" method="get">
                    <input type="hidden" name="id" value="${par.get("id")}">
                    <button id="button_role" type="submit">Изменить роль</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>
</body>
</html>
