//Clara Tschamon
package at.fhv.bibliothekweb.controller;

import at.fhv.bibliothekweb.model.GuestBookEntry;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

//https://www.codejava.net/java-ee/servlet/handling-html-form-data-with-java-servlet


@WebServlet(urlPatterns = {"/gaestebuch"}) //über welche Seite will ich im internet erreichbar sein?
public class GaestebuchServlet extends HttpServlet {

    private ArrayList<GuestBookEntry> entries = new ArrayList<>();

    public GaestebuchServlet(){
        super();
    }

    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        entries.add(new GuestBookEntry("John", "john@email.com", "Hallo liebe Bibliothek Blons," +
                "ich finde es sehr schade, dass ihr keine James Bond Filme habt. Ich würde sie sehr gerne sehen und ich" +
                "wäre bestimmt nicht der Einzige, der die Filme ausleihen würde."));
        entries.add(new GuestBookEntry("Jane", "jane@email.com", "Guten Tag, ich habe einen Buchwunsch:" +
                "gerne würde ich die Buchreihe '5 Freunde' für meine Kinder ausleihen. " +
                "Die 5 Freunde Bücher waren als Kind meine Lieblingsbücher."));
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();

        if(request.getParameter("name") != null){
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String message = request.getParameter("message");
            GuestBookEntry newEntry = new GuestBookEntry(name, email, message);
            entries.add(newEntry);
        }

        response.setContentType("text/html");

        writer.println("<html><head><title>Gästebuch</title></head>");
        writer.println("<link rel='stylesheet' type='text/css' href='Browseransicht.css'>");
        writer.println("<link rel='stylesheet' type='text/css' href='Druckansicht.css' media='print'/>");
        writer.println("<link rel='stylesheet' type='text/css' href='Druckansicht.css' media='print'/>");
        writer.println("<script defer type=\"text/javascript\" src=\"SaveCookie.js\"></script>");
        writer.println("<body>");

        writer.println("<div class=\"navbar\">\n" +
                "            <div class=\"left\">\n" +
                "                <a href=\"home.html\"> <!--bild ist link zu startseite-->\n" +
                "                    <img src=\"bilder/Logo.png\" id=\"logo\" alt=\"Email schreiben\">\n" +
                "                </a>\n" +
                "            </div>\n" +
                "            <div class=\"navbar\">\n" +
                "                <div class=\"dropdown\">\n" +
                "                    <button class=\"dropbtn\">Unsere Medien\n" +
                "                        <i class=\"fa fa-caret-down\"></i>\n" +
                "                    </button>\n" +
                "                    <div class=\"dropdown-content\">\n" +
                "                        <a href=\"Buecher.html\">Bücher</a>\n" +
                "                        <a href=\"#\">Zeitschriften</a>\n" +
                "                        <a href=\"Filme.html\">Filme</a>\n" +
                "                        <a href=\"#\">CDs</a>\n" +
                "                        <a href=\"#\">Spiele</a>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "                <a href=\"mailto: blons@bibliothek.at\" class=\"email\">\n" +
                "                    <img src=\"bilder/icons/email.png\" id=\"email\" alt=\"Email schreiben\">\n" +
                "                </a>\n" +
                "                <a href=\"Formular.html\" class=\"email\">\n" +
                "                    User ID und Passwort beantragen\n" +
                "                </a>\n" +
                "                <a href=\"./gaestebuch\" class=\"email\">\n" +
                "                    Gästebuch\n" +
                "                </a>\n" +
                "            </div>\n" +
                "        </div>");

        writer.println("<div class='centerContent'>");
        writer.println("<h2>Gästebuch</h2>");
        for(GuestBookEntry entry : entries){
            writer.println("<h3>" + entry.getName() + "<i> (" + entry.getEmail() +")</i> </h3></br>");
            writer.println("<p class='guestBookEntry'>" + entry.getMessage() + "</p><hr>");
        }
        writer.println("</br><button><a class='guestBookEntry' href='GaestebuchEintrag.html'>Eintrag hinzufügen</a></button><br/>");
        writer.println("</div>");
        writer.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
