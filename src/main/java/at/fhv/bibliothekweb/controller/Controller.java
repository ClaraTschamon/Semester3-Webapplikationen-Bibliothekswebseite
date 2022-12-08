package at.fhv.bibliothekweb.controller;

import at.fhv.bibliothekweb.model.AuthenticationBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext application = getServletContext();
        HttpSession session = request.getSession();
        String dispatchto = "";
        if (request.getParameter("dispatchto") != null) {
            dispatchto = request.getParameter("dispatchto");
        }
        if (dispatchto.equals("LogIn")) {
            String page = "/LogInView.jsp";
            RequestDispatcher dispatcher = application.getRequestDispatcher(page);
            dispatcher.forward(request, response);
            return;
        }
        if (dispatchto.equals("LogInUser")) { //in LogInView.jsp aufgerufen
            AuthenticationBean authenticationBean = (AuthenticationBean) session.getAttribute("authenticationBean"); //wo setze ich das AuthenticationBean?

            if(authenticationBean == null){
                authenticationBean = new AuthenticationBean();
                session.setAttribute("authenticationBean", authenticationBean);
            }
            String userID = request.getParameter("userID");
            String password = request.getParameter("password");
            System.out.println(userID);
            System.out.println(password);
            if(authenticationBean.checkUser(userID, password)){
                String page = "/LogInSuccessView.jsp";
                RequestDispatcher dispatcher = application.getRequestDispatcher(page);
                System.out.println("Success");
                session.setAttribute("loggedIn", true);
                dispatcher.forward(request, response);;
                return;
            }else{
                String page = "/LogInErrorView.jsp";
                RequestDispatcher dispatcher = application.getRequestDispatcher(page);
                dispatcher.forward(request, response);
                return;
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
