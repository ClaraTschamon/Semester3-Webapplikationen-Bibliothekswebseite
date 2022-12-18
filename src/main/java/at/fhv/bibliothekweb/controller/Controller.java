//Clara Tschamon
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
import java.util.Date;
import java.util.HashMap;

@WebServlet(urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {
    private HttpSession session;
    String page;
    HashMap<Date, String> history;

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

        if (session.getAttribute("historyHashMap") == null){
            history = new HashMap<>();
            session.setAttribute("historyHashMap", history);
        }


        switch (dispatchto) {
            case "LogIn":
                history = (HashMap<Date, String>) session.getAttribute("historyHashMap");
                history.put(new Date(), "LogIn");
                session.setAttribute("historyHashMap", history);
                dispatchToLogIn(request, response, application);
                break;

            case "LogInUser": //hier nichts machen für history
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
                    RequestDispatcher dispatcher = application.getRequestDispatcher(page);
                    this.page = "";
                    System.out.println("Success");
                    session.setAttribute("loggedIn", true);
                    System.out.println("loggedIn: " + session.getAttribute("loggedIn") .toString());
                    dispatcher.forward(request, response);;
                }else{
                    page = "/LogInErrorView.jsp";
                    session.setAttribute("loggedIn", false);
                    RequestDispatcher dispatcher = application.getRequestDispatcher(page);
                    page="";
                    dispatcher.forward(request, response);
                }
                break;
            case "Buecher.html":
                history = (HashMap<Date, String>) session.getAttribute("historyHashMap");
                history.put(new Date(), "Buecher");
                session.setAttribute("historyHashMap", history);

                String booksPage = "/Buecher.html";
                forward(booksPage, request, response, application);
                break;
            case "Filme.html":
                history = (HashMap<Date, String>) session.getAttribute("historyHashMap");
                history.put(new Date(), "Filme");
                session.setAttribute("historyHashMap", history);

                String filmPage = "/Filme.html";
                forward(filmPage, request, response, application);
                break;
            case "./gaestebuch":
                history = (HashMap<Date, String>) session.getAttribute("historyHashMap");
                history.put(new Date(), "Gästebuch");
                session.setAttribute("historyHashMap", history);

                String guestbookPage = "/./gaestebuch";
                forward(guestbookPage, request, response, application);
                break;
            case "RegisterUser":
                history = (HashMap<Date, String>) session.getAttribute("historyHashMap");
                history.put(new Date(), "Registrierung");
                session.setAttribute("historyHashMap", history);

                String firstName = request.getParameter("Vorname");
                String lastName = request.getParameter("Nachname");
                String userId = request.getParameter("User-ID");
                String pwd = request.getParameter("Passwort");
                User newUser = new User(firstName, lastName, userId, pwd);
                AuthenticationBean authenticationBean1 = new AuthenticationBean();

                if(authenticationBean1.addNewUser(newUser)){
                    String page2 = "/RegistrationSuccessfullView.jsp";
                    System.out.println(newUser.toString());
                    session.setAttribute("currentUser", newUser); //damit in jsp darauf zugegriffen werden kann
                    session.setAttribute("loggedIn", true);
                    RequestDispatcher dispatcher = application.getRequestDispatcher(page2);
                    dispatcher.forward(request, response);
                } else {
                    session.setAttribute("loggedIn", false);
                    String page2 = "/RegistrationErrorView.jsp";
                    RequestDispatcher dispatcher = application.getRequestDispatcher(page2);
                    dispatcher.forward(request, response);
                }
                break;
            case "home.html":
                history = (HashMap<Date, String>) session.getAttribute("historyHashMap");
                history.put(new Date(), "home");
                session.setAttribute("historyHashMap", history);
                String homePage = "/home.html";
                RequestDispatcher dispatcher = application.getRequestDispatcher(homePage);
                dispatcher.forward(request, response);
                break;
            case "beschreibungen/KönigDerLöwenBeschreibung.html":
                history = (HashMap<Date, String>) session.getAttribute("historyHashMap");
                history.put(new Date(), "Beschreibung König Der Löwen");
                session.setAttribute("historyHashMap", history);
                String pageKonigDL = "/beschreibungen/KönigDerLöwenBeschreibung.html";
                dispatcher = application.getRequestDispatcher(pageKonigDL);
                dispatcher.forward(request, response);
                break;
            case "beschreibungen/HarryPotter1Beschreibung.html":
                history = (HashMap<Date, String>) session.getAttribute("historyHashMap");
                history.put(new Date(), "Beschreibung Harry Potter Teil 1");
                session.setAttribute("historyHashMap", history);
                String pageHP1 = "/beschreibungen/HarryPotter1Beschreibung.html";
                dispatcher = application.getRequestDispatcher(pageHP1);
                dispatcher.forward(request, response);
                break;
            case "beschreibungen/GrimmMärchenBeschreibung.html":
                history = (HashMap<Date, String>) session.getAttribute("historyHashMap");
                history.put(new Date(), "Beschreibung Grimm Märchen");
                session.setAttribute("historyHashMap", history);
                String pageGrimm = "/beschreibungen/GrimmMärchenBeschreibung.html";
                dispatcher = application.getRequestDispatcher(pageGrimm);
                dispatcher.forward(request, response);
                break;
            case "History.jsp":
                history = (HashMap<Date, String>) session.getAttribute("historyHashMap");
                history.put(new Date(), "History");
                session.setAttribute("historyHashMap", history);
                String pageHistory = "/History.jsp";
                dispatcher = application.getRequestDispatcher(pageHistory);
                dispatcher.forward(request, response);
                break;
            case "Formular.html":
                history = (HashMap<Date, String>) session.getAttribute("historyHashMap");
                history.put(new Date(), "History");
                session.setAttribute("Registrierungsformular", history);
                String pageRegistrationForm = "/Form.html";
                dispatcher = application.getRequestDispatcher(pageRegistrationForm);
                dispatcher.forward(request, response);
                break;
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void dispatchToLogIn(HttpServletRequest request, HttpServletResponse response, ServletContext application) throws ServletException, IOException {
        String logInPage = "/LogInView.jsp";
        RequestDispatcher dispatcher = application.getRequestDispatcher(logInPage);
        dispatcher.forward(request, response);
    }

    private void forward(String page, HttpServletRequest request, HttpServletResponse response, ServletContext application) throws ServletException, IOException {
        System.out.println("logged in:"  + session.getAttribute("loggedIn").toString());
        if(session.getAttribute("loggedIn").equals(true)){ //wird bei bücher und filme nicht aufgerufen
            RequestDispatcher dispatcher = application.getRequestDispatcher(page);
            System.out.println("here");
            dispatcher.forward(request, response);
        } else {
            this.page = page;
            dispatchToLogIn(request, response, application);
        }
    }
}
