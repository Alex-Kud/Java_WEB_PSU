<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%
    int count = 0;
    File file = new File("visitors.txt");
    try
    {
        Scanner scaner = new Scanner(file);
        count = scaner.nextInt();
        scaner.close();
    }
    catch (FileNotFoundException ex){}
    count++;
    try
    {
        Writer writer = new FileWriter(file);
        writer.write(String.valueOf(count));
        writer.flush();
        writer.close();
    }
    catch (IOException ex){}
    
    SimpleDateFormat dateFormat = new SimpleDateFormat(" HH:mm:ss dd MMMM yyyy", new Locale("ru"));
    String time_string = dateFormat.format(new Date());
%>

<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="style/style.css">
    <title>Лабораторная работа №5</title>
</head>
<body>
    <div id="main">
        <h1 align="center">Лабораторная работа 5 (Вариант 1)</h1>
        <h1 align="center">по курсу Java, Web-разработка</h1>
        <h2 align="center">Выполнили:</h2>
        <h2 align="center">Студенты группы 20ВП1</h2>
        
        <div class="inline">
            <h3 align="center">Агапова Валерия</h3>
            <img src="img/photo1.jpg" width="400px" height="300px"/>
        </div>
        
        <div class="inline">
            <h3 align="center">Кудашов Александр</h3>
            <img src="img/photo2.jpg" width="400px" height="300px"/>
        </div>
        
        <p>Количество посещений сайта: <%= count %></p>
        <p>Время и дата: <%= time_string %></p>
    </div>
</body>
</html>
