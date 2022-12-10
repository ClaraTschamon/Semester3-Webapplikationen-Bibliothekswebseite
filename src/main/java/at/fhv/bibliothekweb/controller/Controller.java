package at.fhv.bibliothekweb.controller;

import at.fhv.bibliothekweb.model.AuthenticationBean;
import at.fhv.bibliothekweb.model.User;
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

    private HttpSession session;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext application = getServletContext();
        if(session == null){
            session = request.getSession(); //session speichern
            session.setAttribute("loggedIn", false);
        }
        String dispatchto = "";

        if (request.getParameter("dispatchto") != null) {
            dispatchto = request.getParameter("dispatchto");
        }


        switch (dispatchto) {
            case "LogIn":
                dispatchToLogIn(request, response, application);
                break;

            case "LogInUser":
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
                    String page = "/home.html";
                    RequestDispatcher dispatcher = application.getRequestDispatcher(page);
                    System.out.println("Success");
                    User currentUser = authenticationBean.getUser(userID);
                    session.setAttribute("loggedIn", true);
                    System.out.println("loggedIn: " + session.getAttribute("loggedIn") .toString());
                    dispatcher.forward(request, response);;
                }else{
                    String page = "/LogInErrorView.jsp";
                    session.setAttribute("loggedIn", false);
                    RequestDispatcher dispatcher = application.getRequestDispatcher(page);
                    dispatcher.forward(request, response);
                }
                break;
            case "Buecher.html":
                String booksPage = "/Buecher.html";
                forward(booksPage, request, response, application);
                break;
            case "Filme.html":
                String filmPage = "/Filme.html";
                forward(filmPage, request, response, application);
                break;
            case "./gaestebuch":
                String guestbookPage = "/./gaestebuch";
                forward(guestbookPage, request, response, application); //funktioniert noch nicht ganz
                break;
            case "RegisterUser":
                String firstName = request.getParameter("Vorname");
                String lastName = request.getParameter("Nachname");
                String userId = request.getParameter("User-ID");
                String pwd = request.getParameter("Passwort");
                User newUser = new User(firstName, lastName, userId, pwd);
                AuthenticationBean authenticationBean1 = new AuthenticationBean();

                if(authenticationBean1.addNewUser(newUser)){
                    String page = "/RegistrationSuccessfullView.jsp";
                    System.out.println(newUser.toString());
                    session.setAttribute("currentUser", newUser); //damit in jsp darauf zugegriffen werden kann
                    session.setAttribute("loggedIn", true);
                    RequestDispatcher dispatcher = application.getRequestDispatcher(page);
                    dispatcher.forward(request, response);
                } else {
                    session.setAttribute("loggedIn", false);
                    String page = "/RegistrationErrorView.jsp";
                    RequestDispatcher dispatcher = application.getRequestDispatcher(page);
                    dispatcher.forward(request, response);
                }
                break;

        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void dispatchToLogIn(HttpServletRequest request, HttpServletResponse response, ServletContext application) throws ServletException, IOException {
        String page = "/LogInView.jsp";
        RequestDispatcher dispatcher = application.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    private void forward(String page, HttpServletRequest request, HttpServletResponse response, ServletContext application) throws ServletException, IOException {
        System.out.println("logged in:"  + session.getAttribute("loggedIn").toString());
        if(session.getAttribute("loggedIn").equals(true)){
            RequestDispatcher dispatcher = application.getRequestDispatcher(page);
            System.out.println("here"); //bei gästebuch komme ich nicht hierher
            dispatcher.forward(request, response);
        } else {
            dispatchToLogIn(request, response, application);
        }
    }
}
