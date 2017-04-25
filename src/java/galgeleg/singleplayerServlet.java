/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package galgeleg;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 *
 * @author magnu
 */
@WebServlet(name = "singleplayerServlet", urlPatterns = {"/singleplayerServlet"})
public class singleplayerServlet extends HttpServlet {
    
    String name;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            
            URL url = new URL("http://ubuntu4.javabog.dk:3033/galgelegtjeneste?wsdl");
            QName qname = new QName("http://galgeleg/", "GalgelegImplService");
            QName qnameport = new QName("http://galgeleg/", "GalgelegImplPort");
            Service service = Service.create(url, qname);
            GalgelegI g = service.getPort(qnameport,GalgelegI.class);
            
            
//String guess = request.getParameter("guess");
//g.gætBogstav(""+guess, name);




out.println("<!DOCTYPE html>");
out.println("<html>");
out.println("<head>");
out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheet.css\">");
out.println("<title>Galgeleg</title>");
out.println("</head>");
out.println("<body>");
out.println("<h1>Don Frankos Mobs Galgeleg</h1>");


//            out.println("<h1>Hej " + name + "</h1>");



//    String fortsæt = "0";
//    if (g.isContinueAvailable(name) == true){


if(g.isContinueAvailable(name) != false) {
    out.println("<form method=\"POST\" action=\"spilSingleServlet\">"); //spilSingleServlet
    out.println("<p id=\"single\">Du har allerede et igangværende spil.<br>Ønsker du at spille videre eller starte et nyt?</p><br>");
    out.println("<input type=\"text\" name=\"nulstil\" value=\"nulstilMigIkke\" readonly hidden/>");
    out.println("<input type=\"text\" name=\"name\" value="+name+" readonly hidden/>");
    out.println("<input type=\"submit\" name=\"fortsæt\" value=\"Fortsæt gammelt spil\"></form><br>");
    
    out.println("<form method=\"POST\" action=\"spilSingleServlet\">");
    out.println("<input type=\"text\" name=\"name\" value="+name+" readonly hidden/>");
    out.println("<input type=\"text\" name=\"nulstil\" value=\"nulstil\" readonly hidden/>");
    out.println("<input type=\"submit\" name=\"fortsæt\" value=\"Start nyt spil\"></form><br>");
    
    out.println("<form method=\"POST\" action=\"MinServlet\">");
    out.println("<input type=\"text\" name=\"name\" value="+name+" readonly hidden/>");
    out.println("<input type=\"submit\" name=\"fortsæt\" value=\"Tilbage\"></form><br>");
    
} else if (g.isContinueAvailable(name) == false) {
    out.println("<p>Du har ikke et igangværende spil.<br>Ønsker du at starte et?</p><br>");
    out.println("<form method=\"POST\" action=\"spilSingleServlet\">");
    out.println("<input type=\"text\" name=\"name\" value="+name+" readonly hidden/>");
    out.println("<input type=\"text\" name=\"nulstil\" value=\"nulstil\" readonly hidden/>");
    out.println("<input type=\"submit\" name=\"fortsæt\" value=\"Start nyt spil\"></form><br>");
    
    out.println("<form method=\"POST\" action=\"MinServlet\">");
    out.println("<input type=\"text\" name=\"name\" value="+name+" readonly hidden/>");
    out.println("<input type=\"submit\" name=\"fortsæt\" value=\"Tilbage\"></form><br>");
}

//    else if (g.isContinueAvailable(name) == false) {
//        out.println("<form method=\"POST\" action=\"MinServlet\">");
//        out.println("<p id=\"single2\">Du har ikke et igangværende spil, ønsker du at starte et?</p>");
//        out.println("<input type=\"submit\" name=\"fortsæt\" value=\"Start nyt spil\"></form>");
//
//        out.println("<form method=\"POST\" action=\"MinServlet\">");
//        out.println("<input type=\"submit\" name=\"fortsæt\" value=\"Tilbage\"></form>");
//    }




//        out.println("<form method=\"POST\" action=\"singleplayerServlet\">");
//    out.println("<p id=\"singleEllerMulti\">Vælg en funktion</p>");
//    out.println("<input type=\"text\" name=\"name\" value="+name+" readonly hidden/>");
//    out.println("<input type=\"submit\" name=\"singleMultiTilbage\" value=\"Singleplayer\"></form><br>");
//
//    out.println("<form method=\"POST\" action=\"MinServlet\">");
//    out.println("<input type=\"text\" name=\"name\" value="+name+" readonly hidden/>");
//    out.println("<input type=\"submit\" name=\"singleMultiTilbage\" value=\"Multiplayer\"></form><br>");
//
//    out.println("<form method=\"POST\" action=\"galgelegWeb.jsp\">");
//    out.println("<input type=\"text\" name=\"name\" value="+name+" readonly hidden/>");
//    out.println("<input type=\"submit\" name=\"singleMultiTilbage\" value=\"Log ud\"></form>");




//    fortsæt = request.getParameter("fortsæt");
//    if (fortsæt.equals("Fortsæt gammelt spil")) {
//        out.print("<p> Du har valgt at fortsætte dit gamle spil </p>");
//    } else if (fortsæt.equals("Start nyt spil")) {
//        out.print("<p> Du har valgt at starte et nyt spil </p>");
//    } else {
//        //Her skal den gå tilbage til galgelegWeb.jsp. Det finder vi ud af senereeeeeeee.
//    }
//
//










out.println("</body>");
out.println("</html>");
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        name = request.getParameter("name");
        processRequest(request, response);
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
