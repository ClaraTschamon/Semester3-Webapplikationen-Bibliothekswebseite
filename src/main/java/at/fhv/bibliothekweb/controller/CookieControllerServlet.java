//Clara Tschamon
package at.fhv.bibliothekweb.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {""})
public class CookieControllerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        RequestDispatcher dispatcher;
        String url = new String();

        if(request.getCookies() == null){
            dispatcher = request.getRequestDispatcher("home.html");
            dispatcher.forward(request, response);
        }else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("lastVisited")) {
                    url = cookie.getValue();
                    break;
                }
            }
            url = url.substring(getServletContext().getContextPath().length());
            if (url.equals("/")) {
                dispatcher = request.getRequestDispatcher("home.html");
                dispatcher.forward(request, response);
            } else {
                dispatcher = request.getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}