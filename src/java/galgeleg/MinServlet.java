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


String guess = request.getParameter("guess");
g.gætBogstav(""+guess);



out.println("<!DOCTYPE html>");
out.println("<html>");
out.println("<head>");
out.println("<title>Galgeleg</title>");
out.println("</head>");
out.println("<body>");
out.println("<style>"
        + "body {"
        + "background-color: #000000;"
        + "text-align: center;"
        + "}"
        
        + "h1 {"
        + "color:#20C20E;"
        + "font-family: Monospace;"
        + "}"
        
        + "p {"
        + "color:#20C20E;"
        + "margin-bottom: 2px;"
        + "}"
        
        + "#input2 {"
        + "margin-bottom: 10px;"
        + "}"
        
        + "#guess {"
        + "margin-bottom: 10px;"
        + "margin-top: 10px;"
        + "}"
        
        + "#nytspilknap {"
        + "margin-top: 10px;"
        + "}"
        
        + "</style>"
        
        + "<h1>Don Frankos Mobs Galgeleg</h1>");


String a = g.log();
int indexstring = a.indexOf("Antal forkerte bogstaver");
a = a.substring(indexstring+27, indexstring+28);
int status = Integer.parseInt(a);

switch (status) {
    case 0:
        out.println("<img src=\"http://i65.tinypic.com/2cnb88j.png\" border=\"0\" alt=\"Galge\">");
        break;
    case 1:
        out.println("<img src=\"http://i68.tinypic.com/2kem28.png\" border=\"0\" alt=\"forkert 1\">");
        break;
    case 2:
        out.println("<img src=\"http://i67.tinypic.com/2v9c7cy.png\" border=\"0\" alt=\"forkert 2\">");
        break;
    case 3:
        out.println("<img src=\"http://i68.tinypic.com/34exoqh.png\" border=\"0\" alt=\"forkert 3\">");
        break;
    case 4:
        out.println("<img src=\"http://i67.tinypic.com/1z3uxdv.png\" border=\"0\" alt=\"forkert 4\">");
        break;
    case 5:
        out.println("<img src=\"http://i65.tinypic.com/25qxlk8.png\" border=\"0\" alt=\"forkert 5\">");
        break;
    case 6:
        out.println("<img src=\"http://i65.tinypic.com/optn2b.png\" border=\"0\" alt=\"forkert 6\">");
        break;
        }

//out.println("<img src=\"http://i65.tinypic.com/2cnb88j.png\" border=\"0\" alt=\"Galge\">");
//out.println("<img src=\"http://i68.tinypic.com/2kem28.png\" border=\"0\" alt=\"forkert 1\">");
//out.println("<img src=\"http://i67.tinypic.com/2v9c7cy.png\" border=\"0\" alt=\"forkert 2\">");
//out.println("<img src=\"http://i68.tinypic.com/34exoqh.png\" border=\"0\" alt=\"forkert 3\">");
//out.println("<img src=\"http://i67.tinypic.com/1z3uxdv.png\" border=\"0\" alt=\"forkert 4\">");
//out.println("<img src=\"http://i65.tinypic.com/25qxlk8.png\" border=\"0\" alt=\"forkert 5\">");
//out.println("<img src=\"http://i65.tinypic.com/optn2b.png\" border=\"0\" alt=\"forkert 6\">");




if(!g.spilSlut()){
    
out.println("<p>"+g.logWeb()+"</p>");
out.println("<form method=\"POST\" action=\"MinServlet\">");
out.println("<input type=\"String\" id=\"guess\"  name=\"guess\" autofocus>   <br>");
//out.println("<p id=\"yourguess\"> dit gæt</p>");
out.println("<input type=\"submit\" name=\"guessKnap\" value=\"Gæt\"></form>");
}


else if(g.spilSlut()){

out.println("<p>"+g.logWeb()+"</p>");
out.println("<form method=\"POST\" action=\"MinServlet\">");
out.println("<input type=\"submit\" name=\"guessKnap\" value=\"Start nyt spil\" id=\"nytspilknap\"></form>");

g.nulstil();
    

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
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Syvtabellen - fra en servlet</title></head>");
        out.println("<body>");
        
        out.println("<label for=\"tal1\"> Første tal </label>");
        out.println("<input type=\"text\" name=\"tal1\">");
        
        out.println("<label for=\"tal2\"> Andet tal </label>");
        out.println("<input type=\"text\" name=\"tal2\">");
        out.println("<input type=\"submit\" value=\"Regn det ud!\">");
//        for (int i = 1; i <= 10; i++) {
//            out.println("Syv gange " + i + " er: " + 7 * i + ".<br>");
//        }
out.println("</body>");
out.println("</html>");

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