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
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Objects;

import static java.lang.System.out;

@WebServlet("/getgeo/*")
public class GeoCoordinates extends HttpServlet {
    /**
     * @param req User request (to server) variable
     * @param resp Server response variable
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader("users.json");
        try {
            String user_login = req.getParameter("user_login");
            JSONArray users = (JSONArray) parser.parse(reader);
            for(int i = 0; i < users.size(); i++) {
                JSONObject currentUser = (JSONObject) users.get(i);
                if(Objects.equals(user_login, currentUser.get("login"))){
                    JSONObject response = new JSONObject();
                    response.put("latitude", currentUser.get("latitude"));
                    response.put("longitude", currentUser.get("longitude"));
                    resp.setContentType("text/html;charset=UTF-8");
                    resp.getWriter().write(response.toJSONString());
                    break;
                }

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
