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
 * Class for deleting users
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
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
            JSONArray users = (JSONArray) parser.parse(reader);
            for (int i = 0; i < users.size(); i++) {
                JSONObject obj = (JSONObject) users.get(i);
                if (Objects.equals(Objects.toString(obj.get("id"), null), id))
                    users.remove(i);
            }
            // Запись в файл
            try {
                Writer writer = new FileWriter(file);
                writer.write(users.toJSONString());
                writer.flush();
                writer.close();
            }
            catch (IOException ex){}

        } catch (ParseException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/show_users").forward(req,resp);
    }
}
