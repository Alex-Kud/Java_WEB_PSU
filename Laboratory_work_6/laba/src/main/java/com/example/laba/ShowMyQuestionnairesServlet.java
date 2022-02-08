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
 * Class to demonstrate your questionnaires
 */
@WebServlet("/show_my_questionnaires/*")
public class ShowMyQuestionnairesServlet extends HttpServlet {
    /**
     * @param req User request (to server) variable
     * @param resp Server response variable
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] pathInfo = req.getPathInfo().split("/");
        String user_login = pathInfo[1];

        List vec = new Vector();

        JSONParser parser = new JSONParser();
        Reader reader = new FileReader("questionnaires.json");

        try {
            JSONArray questionnaires = (JSONArray) parser.parse(reader);
            boolean found = false;
            Map<String, String> questionnairesOut = new HashMap<>();
            for(int i = 0; i < questionnaires.size(); i++) {
                JSONObject questionnaire = (JSONObject) questionnaires.get(i);
                HttpSession session = req.getSession();
                if (Objects.equals((String) user_login, (String) questionnaire.get("login"))) {
                    found = true;
                    questionnairesOut = new HashMap<>();
                    questionnairesOut.put("firstName", (String) questionnaire.get("firstName"));
                    questionnairesOut.put("lastName", (String) questionnaire.get("lastName"));
                    questionnairesOut.put("gender", (String) questionnaire.get("gender"));
                    questionnairesOut.put("email", (String) questionnaire.get("email"));
                    questionnairesOut.put("age", (String) questionnaire.get("age"));
                    questionnairesOut.put("login", (String) questionnaire.get("login"));
                    questionnairesOut.put("job", (String) questionnaire.get("job"));
                    questionnairesOut.put("activity", (String) questionnaire.get("activity"));
                    questionnairesOut.put("interests", (String) questionnaire.get("interests"));
                    questionnairesOut.put("fav_music", (String) questionnaire.get("fav_music"));
                    questionnairesOut.put("books", (String) questionnaire.get("books"));
                    questionnairesOut.put("socials", (String) questionnaire.get("socials"));
                    questionnairesOut.put("video", (String) questionnaire.get("video"));
                    questionnairesOut.put("flag", "true");
                    vec.add(questionnairesOut);
                }
            }
            if(!found) {
                questionnairesOut.put("login", user_login);
                questionnairesOut.put("flag", "false");
                vec.add(questionnairesOut);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        req.setAttribute("myQuestionnaires", vec);
        req.getRequestDispatcher("/user_account.jsp").forward(req,resp);
    }
}
