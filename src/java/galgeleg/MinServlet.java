/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package galgeleg;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Scanner;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
import java.net.MalformedURLException;


/**
 *
 * @author Martin
 */
@WebServlet(name = "MinServlet", urlPatterns = {"/MinServlet"})
public class MinServlet extends HttpServlet {
    
    
    
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
//            /* TODO output your page here. You may use following sample code. */


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



//Vælg singleplayer eller multiplayer eller log ud
out.println("<form method=\"POST\" action=\"singleplayerServlet\">");
out.println("<p id=\"singleEllerMulti\">Vælg en funktion</p><br>");
out.println("<input type=\"text\" name=\"name\" value="+name+" readonly hidden/>");
out.println("<input type=\"submit\" name=\"singleMultiTilbage\" value=\"Singleplayer\"></form><br>");

out.println("<form method=\"POST\" action=\"multiplayerServlet\">");
out.println("<input type=\"text\" name=\"name\" value="+name+" readonly hidden/>");
out.println("<input type=\"submit\" name=\"singleMultiTilbage\" value=\"Multiplayer\"></form><br>");

out.println("<form method=\"POST\" action=\"galgelegWeb.jsp\">");
out.println("<input type=\"text\" name=\"name\" value="+name+" readonly hidden/>");
out.println("<input type=\"submit\" name=\"singleMultiTilbage\" value=\"Log ud\"></form><br>");

out.println("<form method=\"POST\" action=\"highscoreServlet\">");
out.println("<input type=\"text\" name=\"name\" value="+name+" readonly hidden/>");
out.println("<input type=\"submit\" name=\"singleMultiTilbage\" value=\"Highscores\"></form>");



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
        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        out.println("<html>");
//        out.println("<head><title>Syvtabellen - fra en servlet</title></head>");
//        out.println("<body>");
//
//        out.println("<label for=\"tal1\"> Første tal </label>");
//        out.println("<input type=\"text\" name=\"tal1\">");
//
//        out.println("<label for=\"tal2\"> Andet tal </label>");
//        out.println("<input type=\"text\" name=\"tal2\">");
//        out.println("<input type=\"submit\" value=\"Regn det ud!\">");
//        for (int i = 1; i <= 10; i++) {
//            out.println("Syv gange " + i + " er: " + 7 * i + ".<br>");
//        }
//out.println("</body>");
//out.println("</html>");

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
