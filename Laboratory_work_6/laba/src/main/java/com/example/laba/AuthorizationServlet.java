package com.example.laba;

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
import java.io.*;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
/**
 * Class of the authorisation servlet
 */
@WebServlet("/auth")
public class AuthorizationServlet extends HttpServlet {
    /**
     * @param req User request (to server) variable
     * @param resp Server response variable
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader("users.json");
        // Получение ip-адреса
        try {
            URL ipAddress = new URL("http://myexternalip.com/raw");
            BufferedReader in = new BufferedReader(new InputStreamReader(ipAddress.openStream()));
            String ip = in.readLine();
            // Получение данных о местоположении по ip
            File database = new File("GeoLite2-City.mmdb");
            DatabaseReader dbReader = new DatabaseReader.Builder(database).build();
            CityResponse response = dbReader.city(InetAddress.getByName(ip));
            String latitude = response.getLocation().getLatitude().toString();
            String longitude = response.getLocation().getLongitude().toString();
            // Авторизация
            JSONArray oldUsers = (JSONArray) parser.parse(reader);
            JSONArray newUsers = new JSONArray();
            boolean auth = false;
            for(int i = 0; i < oldUsers.size(); i++) {
                JSONObject user = (JSONObject) oldUsers.get(i);
                if (Objects.equals(login, user.get("login")))
                    if (Objects.equals(password, user.get("password"))) {
                        auth = true;
                        HttpSession session = req.getSession();
                        session.setAttribute("AUTH", "TRUE");
                        session.setAttribute("LOGIN", login);
                        session.setAttribute("STATUS", user.get("role"));
                        session.setAttribute("FIRSTNAME", user.get("firstName"));
                        session.setAttribute("LASTNAME", user.get("lastName"));
                        session.setAttribute("GENDER", user.get("gender"));
                        session.setAttribute("EMAIL", user.get("email"));
                        session.setAttribute("AGE", user.get("age"));
                        // Обновление геоданных
                        user.put("latitude", latitude);
                        user.put("longitude", longitude);
                    }
                newUsers.add(user);
                // Запись в файл
                File file = new File("users.json");
                Writer writer = new FileWriter(file);
                writer.write(newUsers.toJSONString());
                writer.flush();
                writer.close();
            }
            if (auth){
                HttpSession session = req.getSession();
                req.setAttribute("login_user", login);
                String url = "/show_my_questionnaires/";
                url += session.getAttribute("LOGIN");
                resp.sendRedirect(url); // Редирект в ЛК
            }
            else {
                resp.setContentType("text/html");
                req.setAttribute("auth_error","Ну ты и краб! Логин и/или пароль не подходит");
                req.getRequestDispatcher("/login_page.jsp").forward(req,resp);
            }
        } catch (IOException | GeoIp2Exception | ParseException e) { e.printStackTrace(); }
    }
}
