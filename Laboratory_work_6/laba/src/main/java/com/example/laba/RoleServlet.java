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
import java.io.*;
import java.util.Objects;
/**
 * Class for changing the user role
 */
@WebServlet("/role")
public class RoleServlet extends HttpServlet {
    /**
     * @param req User request (to server) variable
     * @param resp Server response variable
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File file = new File("users.json");
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader("users.json");
        String id = req.getParameter("id");

        try {
            JSONArray oldUsers = (JSONArray) parser.parse(reader);
            JSONArray newUsers = new JSONArray();
            for (int i = 0; i < oldUsers.size(); i++) {
                JSONObject currentUser = (JSONObject) oldUsers.get(i);
                if (Objects.equals(Objects.toString(currentUser.get("id"), null), id))
                    if (Objects.equals((String) currentUser.get("role"), "user"))
                        currentUser.put("role", "admin");
                    else if (Objects.equals((String) currentUser.get("role"), "admin"))
                        currentUser.put("role", "user");
                newUsers.add(currentUser);
            }
            // Запись в файл
            Writer writer = new FileWriter(file);
            writer.write(newUsers.toJSONString());
            writer.flush();
            writer.close();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/show_users").forward(req,resp);
    }
}