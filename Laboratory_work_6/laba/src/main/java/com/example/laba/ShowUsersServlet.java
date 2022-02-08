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
 * Class for displaying a list of users
 */
@WebServlet("/show_users")
public class ShowUsersServlet extends HttpServlet {
    /**
     * @param req User request (to server) variable
     * @param resp Server response variable
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List list = new Vector();

        JSONParser parser = new JSONParser();
        Reader reader = new FileReader("users.json");

        try {
            JSONArray users = (JSONArray) parser.parse(reader);
            for(int i = 0; i < users.size(); i++) {
                JSONObject user = (JSONObject) users.get(i);
                Map<String, String> jsonObg = new HashMap<>();
                jsonObg.put("id", Objects.toString(user.get("id"), null));
                jsonObg.put("login", (String) user.get("login"));
                jsonObg.put("firstName", (String) user.get("firstName"));
                jsonObg.put("lastName", (String) user.get("lastName"));
                jsonObg.put("password", (String) user.get("password"));
                jsonObg.put("gender", (String) user.get("gender"));
                jsonObg.put("email", (String) user.get("email"));
                jsonObg.put("age", (String) user.get("age"));
                jsonObg.put("role", (String) user.get("role"));
                list.add(jsonObg);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        req.setAttribute("users", list);
        req.getRequestDispatcher("/admin_account.jsp").forward(req,resp);
    }

}
