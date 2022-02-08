package com.example.laba;

import java.io.*;
import java.net.InetAddress;
import java.net.URL;
import java.util.Objects;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
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
/**
 * Class for user registration
 */
@WebServlet("/reg")
public class RegistrationServlet extends HttpServlet {
    /**
     * @param req User request (to server) variable
     * @param resp Server response variable
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String gender = req.getParameter("gender");
        String age = req.getParameter("age");
        resp.setContentType("text/html;charset=UTF-8");

        File file = new File("users.json");
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader("users.json");

        try {
            // Считывание json
            JSONArray output = (JSONArray) parser.parse(reader);
            boolean repeatLogin = false;

            for(int i = 0; i < output.size(); i++) {
                JSONObject user = (JSONObject) output.get(i);
                if (Objects.equals(login, (String) user.get("login"))) {
                    repeatLogin = true;
                    break;
                }
            }
            // Получение ip-адреса
            URL ipAddress = new URL("http://myexternalip.com/raw");
            BufferedReader in = new BufferedReader(new InputStreamReader(ipAddress.openStream()));
            String ip = in.readLine();
            // Получение данных о местоположении по ip
            File database = new File("GeoLite2-City.mmdb");
            DatabaseReader dbReader = new DatabaseReader.Builder(database).build();
            CityResponse response = dbReader.city(InetAddress.getByName(ip));
            String latitude = response.getLocation().getLatitude().toString();
            String longitude = response.getLocation().getLongitude().toString();

            if (!repeatLogin) {
                // Заполнение json-объекта с данными о новом пользователе
                JSONObject newUser = new JSONObject();
                JSONObject lastUser = (JSONObject) output.get(output.size()-1);
                int lastId = (int) (long) lastUser.get("id");

                newUser.put("id", lastId + 1);
                newUser.put("firstName", firstName);
                newUser.put("lastName", lastName);
                newUser.put("login", login);
                newUser.put("password", password);
                newUser.put("email", email);
                newUser.put("gender", gender);
                newUser.put("age", age);
                newUser.put("role", "user");
                newUser.put("latitude", latitude);
                newUser.put("longitude", longitude);

                // Добавляем в json-массив json-объект с данными пользователя
                output.add(newUser);

                // Запись в файл
                Writer writer = new FileWriter(file);
                writer.write(output.toJSONString());
                writer.flush();
                writer.close();

                HttpSession session = req.getSession();
                session.setAttribute("AUTH", "TRUE");
                session.setAttribute("LOGIN", login);
                session.setAttribute("STATUS", "user");
                session.setAttribute("FIRSTNAME", firstName);
                session.setAttribute("LASTNAME", lastName);
                session.setAttribute("GENDER", gender);
                session.setAttribute("EMAIL", email);
                session.setAttribute("AGE", age);
                req.setAttribute("login_user", login);
                String url = "/show_my_questionnaires/";
                url += session.getAttribute("LOGIN");
                resp.sendRedirect(url);
                resp.sendRedirect(url); // Редирект в ЛК
            }
            else{
                req.setAttribute("login_error","Ну ты и краб! Логин уже занят");
                req.getRequestDispatcher("/reg_page.jsp").forward(req,resp);
            }
        } catch (IOException | GeoIp2Exception | ParseException e) { e.printStackTrace(); }
    }
}



