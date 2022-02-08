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
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
/**
 * Сlass for demonstrating user questionnaires
 */
@WebServlet("/show_questionnaires")
public class ShowQuestionnairesServlet extends HttpServlet {
    /**
     * @param req User request (to server) variable
     * @param resp Server response variable
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List vec = new Vector();

        JSONParser parser = new JSONParser();
        Reader reader = new FileReader("questionnaires.json");

        try {
            JSONArray questionnaires = (JSONArray) parser.parse(reader);
            for(int i = 0; i < questionnaires.size(); i++) {
                JSONObject questionnaire = (JSONObject) questionnaires.get(i);
                HttpSession session = req.getSession();
                if (!Objects.equals((String) session.getAttribute("LOGIN"), (String) questionnaire.get("login"))) {
                    Map<String, String> jsonObg = new HashMap<>();
                    jsonObg.put("firstName", (String) questionnaire.get("firstName"));
                    jsonObg.put("lastName", (String) questionnaire.get("lastName"));
                    jsonObg.put("gender", (String) questionnaire.get("gender"));
                    jsonObg.put("email", (String) questionnaire.get("email"));
                    jsonObg.put("age", (String) questionnaire.get("age"));
                    jsonObg.put("login", (String) questionnaire.get("login"));
                    jsonObg.put("job", (String) questionnaire.get("job"));
                    jsonObg.put("activity", (String) questionnaire.get("activity"));
                    jsonObg.put("interests", (String) questionnaire.get("interests"));
                    jsonObg.put("fav_music", (String) questionnaire.get("fav_music"));
                    jsonObg.put("books", (String) questionnaire.get("books"));
                    jsonObg.put("socials", (String) questionnaire.get("socials"));
                    jsonObg.put("video", (String) questionnaire.get("video"));
                    vec.add(jsonObg);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        req.setAttribute("allQuestionnaires", vec);
        req.getRequestDispatcher("/view_questionnaires.jsp").forward(req,resp); // Ссылка на страницу с лентой
    }
}
