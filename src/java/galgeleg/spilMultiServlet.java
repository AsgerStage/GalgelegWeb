/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package galgeleg;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "spilMultiServlet", urlPatterns = {"/spilMultiServlet"})
public class spilMultiServlet extends HttpServlet {
    
    String name;
    String nulstil = "nej";
    public static GalgelegI g;
    
    
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            
            URL url = new URL("http://ubuntu4.javabog.dk:3043/galgelegtjeneste?wsdl");
            QName qname = new QName("http://galgeleg/", "GalgelegImplService");
            QName qnameport = new QName("http://galgeleg/", "GalgelegImplPort");
            Service service = Service.create(url, qname);
            g = service.getPort(qnameport,GalgelegI.class);
            
            
            String guess = request.getParameter("guess");
            
            g.gætBogstavMultiOgLog(""+guess, name);
            





out.println("<!DOCTYPE html>");
out.println("<html>");
out.println("<head>");
out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheet.css\">");
out.println("<title>Galgeleg</title>");
out.println("</head>");
out.println("<body>");
out.println("<h1>Don Frankos Mobs Galgeleg</h1>");


//AUTOREFRESHER hvert 5 sekundt.... midlertidig fix ?
out.println("<meta http-equiv=\"refresh\" content=\"5\" />");


String hvemVandt = g.isMyMultiOver(name);
if (hvemVandt.contains("slut")){
    //Hvis spillet er slut: lav en tilbageknap, announce hvem der vandt....
    out.println("<p>"+hvemVandt+"</p><br>");
    
    out.println("<form method=\"POST\" action=\"multiplayerServlet\">");
    out.println("<input type=\"text\" name=\"name\" value="+name+" readonly hidden/>");
    out.println("<input type=\"text\" name=\"leaveLobby\" value=\"dontLeaveLobby\" readonly hidden/>");
    out.println("<input type=\"submit\" name=\"fortsæt\" value=\"Tilbage\"></form><br>");
    
}

if (!hvemVandt.contains("slut") && !g.isMyMultiActive(name)){
    out.println("<p>Venter på de andre spillere</p>");
}

if (g.isMyMultiActive(name)){
    String a = g.multiLog(name);
    int indexstring = a.indexOf("Antal forkerte bogstaver");
    a = a.substring(indexstring+27, indexstring+28);
    int status = 7;
    try {
        status = Integer.parseInt(a);
    } catch (NumberFormatException e) {
        status = 7;
    }
    
    
    switch (status) {
        case 0:
            out.println("<img src=\"http://i66.tinypic.com/351ts0z.png\" border=\"0\" alt=\"Galge\">");
            break;
        case 1:
            out.println("<img src=\"http://i66.tinypic.com/2mw5r37.png\" border=\"0\" alt=\"forkert 1\">");
            break;
        case 2:
            out.println("<img src=\"http://i68.tinypic.com/fbydtc.png\" border=\"0\" alt=\"forkert 2\">");
            break;
        case 3:
            out.println("<img src=\"http://i65.tinypic.com/2dbtq9j.png\" border=\"0\" alt=\"forkert 3\">");
            break;
        case 4:
            out.println("<img src=\"http://i68.tinypic.com/2lsihdu.png\" border=\"0\" alt=\"forkert 4\">");
            break;
        case 5:
            out.println("<img src=\"http://i68.tinypic.com/232m2x.png\" border=\"0\" alt=\"forkert 5\">");
            break;
        case 6:
            out.println("<img src=\"http://i65.tinypic.com/2yuneo7.png\" border=\"0\" alt=\"forkert 6\">");
            break;
    }
    
    
    
    
//g.playerCheck(name);

out.println("<p>"+g.multiLog(name)+"</p>");
out.println("<form method=\"POST\" action=\"spilMultiServlet\">");
out.println("<p>Dit gæt: </p>");
out.println("<input type=\"String\" id=\"guess\"  name=\"guess\" autofocus>   ");
out.println("<input type=\"text\" name=\"name\" value="+name+" readonly hidden/>");
out.println("<input type=\"text\" name=\"iAmHost\" value=\"iAmNotHost\" readonly hidden/>");
out.println("<input type=\"submit\" name=\"guessKnap\" value=\"Gæt\"></form><br>");

out.println("<form method=\"POST\" action=\"multiplayerServlet\">");
out.println("<input type=\"text\" name=\"name\" value="+name+" readonly hidden/>");
out.println("<input type=\"text\" name=\"leaveLobby\" value=\"leaveLobby\" readonly hidden/>");
out.println("<input type=\"submit\" name=\"fortsæt\" value=\"Tilbage\"></form><br>");



}





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
        nulstil = request.getParameter("nulstil");
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
