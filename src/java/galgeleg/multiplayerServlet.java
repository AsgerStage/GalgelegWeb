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
@WebServlet(name = "multiplayerServlet", urlPatterns = {"/multiplayerServlet"})
public class multiplayerServlet extends HttpServlet {
    
    String name;
    String leaveLobby = "";
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
            
            
            URL url = new URL("http://ubuntu4.javabog.dk:3043/galgelegtjeneste?wsdl");
            QName qname = new QName("http://galgeleg/", "GalgelegImplService");
            QName qnameport = new QName("http://galgeleg/", "GalgelegImplPort");
            Service service = Service.create(url, qname);
            GalgelegI g = service.getPort(qnameport,GalgelegI.class);

            
            
out.println("<!DOCTYPE html>");
out.println("<html>");
out.println("<head>");
out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheet.css\">");
out.println("<title>Galgeleg</title>");
out.println("</head>");
out.println("<body>");
out.println("<h1>Don Frankos Mobs Galgeleg</h1>");

if (leaveLobby.equals("leaveLobby")){
    g.leaveLobby(name);
    out.println("<p>Du har forladt lobbyen</p>");
} else if (leaveLobby.equals("clearLobby")) {
    g.clearLobby(name);
    out.println("<p>Du har stoppet lobbyen</p>");
}


    out.println("<p id=\"single\">Vil du starte eller deltage i et spil?</p><br>");

    //Start spil
    out.println("<form method=\"POST\" action=\"createLobbyServlet\">");
    out.println("<input type=\"text\" name=\"name\" value="+name+" readonly hidden/>");
    out.println("<input type=\"submit\" name=\"fortsæt\" value=\"Opret lobby\"></form><br>");
    //join spil
    out.println("<form method=\"POST\" action=\"lobbyServlet\">");
    out.println("<input type=\"text\" name=\"name\" value="+name+" readonly hidden/>");
    out.println("<input type=\"submit\" name=\"fortsæt\" value=\"Deltag i lobby\"></form><br>");
    
    //se highscore
    out.println("<form method=\"POST\" action=\"highscoreServlet\">");
    out.println("<input type=\"text\" name=\"name\" value="+name+" readonly hidden/>");
    out.println("<input type=\"submit\" name=\"singleMultiTilbage\" value=\"Highscores\"></form><br>");


    //gå tilbage
    out.println("<form method=\"POST\" action=\"MinServlet\">");
    out.println("<input type=\"text\" name=\"name\" value="+name+" readonly hidden/>");
    out.println("<input type=\"submit\" name=\"fortsæt\" value=\"Tilbage\"></form><br>");
    









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
        leaveLobby = request.getParameter("leaveLobby");
        
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
