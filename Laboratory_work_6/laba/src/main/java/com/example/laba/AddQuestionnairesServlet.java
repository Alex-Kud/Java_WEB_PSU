package com.example.laba;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Objects;
/**
 * Class for adding a questionnaire
 */
@WebServlet("/add_questionnaire")
public class AddQuestionnairesServlet extends HttpServlet {
    /**
     * @param req User request (to server) variable
     * @param resp Server response variable
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String city = req.getParameter("city");
        String job = req.getParameter("job");
        String activity = req.getParameter("activity");
        String interests = req.getParameter("interests");
        String fav_music = req.getParameter("fav_music");
        String books = req.getParameter("books");
        String socials = req.getParameter("socials");
        String video = req.getParameter("video");
        if (video.contains("https://www.youtube.com/")) {
            video += "#t=";
            video += req.getParameter("minute");
            video += "m";
            video += req.getParameter("second");
            video += "s";
            video = video.replaceFirst("watch\\?v=","embed/");
        }
        else {
            video = "/images/video.jpg";
        }
        resp.setContentType("text/html;charset=UTF-8");
        File file = new File("questionnaires.json");
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader("questionnaires.json");

        try {
            JSONArray output = (JSONArray) parser.parse(reader);

            JSONObject obj = new JSONObject();
            HttpSession session = req.getSession();
            obj.put("login", (String) session.getAttribute("LOGIN"));
            obj.put("firstName", (String) session.getAttribute("FIRSTNAME"));
            obj.put("lastName", (String) session.getAttribute("LASTNAME"));
            obj.put("gender", (String) session.getAttribute("GENDER"));
            obj.put("email", (String) session.getAttribute("EMAIL"));
            obj.put("age", (String) session.getAttribute("AGE"));

            obj.put("city", city);
            obj.put("job", job);
            obj.put("activity", activity);
            obj.put("interests", interests);
            obj.put("fav_music", fav_music);
            obj.put("books", books);
            obj.put("socials", socials);
            obj.put("video", video);
            // Добавляем в json-массив json-объект с данными пользователя
            output.add(obj);

            // Запись в файл
            try {
                Writer writer = new FileWriter(file);
                writer.write(output.toJSONString());
                writer.flush();
                writer.close();
            }
            catch (IOException ex){}
            String url = "/show_my_questionnaires/";
            url += session.getAttribute("LOGIN");
            resp.sendRedirect(url); // Редирект в ЛК
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}

