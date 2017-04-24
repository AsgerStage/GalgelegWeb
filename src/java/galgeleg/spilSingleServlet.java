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
@WebServlet(name = "spilSingleServlet", urlPatterns = {"/spilSingleServlet"})
public class spilSingleServlet extends HttpServlet {

    String name;
    String nulstil = "nej";
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


String guess = request.getParameter("guess");
g.gætBogstav(""+guess, name);

            
            
            
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

        
//            out.println("<h1>Hej " + name + "</h1>");
            

String a = g.log(name);
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

            
            
    if (nulstil.equals("nulstil")) g.nulstil(name);
    g.playerCheck(name);
    
    
    if(!g.spilSlut()){
        //Herfra kører spillet
//        out.println("<p>"+"VI TESTER"+g.synligtOrd(name)+"</p>");
        
//        out.println("<p>"+"VI TESTER"+g.log(name)+"</p>");
        
        out.println("<p>"+g.logWeb(name)+"</p>");
        out.println("<form method=\"POST\" action=\"spilSingleServlet\">");
        out.println("<p>Dit gæt: </p>");
        out.println("<input type=\"String\" id=\"guess\"  name=\"guess\" autofocus>   ");
        out.println("<input type=\"text\" name=\"name\" value="+name+" readonly hidden/>");
        out.println("<input type=\"submit\" name=\"guessKnap\" value=\"Gæt\"><br></form>");
    }
    else if(g.spilSlut()){
        out.println("<p>"+g.logWeb(name)+"</p>");
        out.println("<form method=\"POST\" action=\"singleplayerServlet\">");
        out.println("<input type=\"text\" name=\"name\" value="+name+" readonly hidden/>");
        out.println("<input type=\"submit\" name=\"guessKnap\" value=\"Start nyt spil\" id=\"nytspilknap\"></form>");
        g.nulstil(name);
    }
    
    
//            
            
//if (g.isContinueAvailable(name) == true) {            

//} else {            
//    if(!g.spilSlut()){
//        //Herfra kører spillet
//        out.println("<p>"+g.logWeb(name)+"</p>");
//        out.println("<form method=\"POST\" action=\"spilSingleplayerServlet\">");
//        out.println("<p>Dit gæt: </p>");
//        out.println("<input type=\"String\" id=\"guess1\"  name=\"guess\" autofocus>   ");
//        out.println("<input type=\"submit\" name=\"guessKnap\" value=\"Gæt\"><br></form>");
//    }
//    else if(g.spilSlut()){
//        out.println("<p>"+g.logWeb(name)+"</p>");
//        out.println("<form method=\"POST\" action=\"singleplayerServlet\">");
//        out.println("<input type=\"submit\" name=\"guessKnap\" value=\"Start nyt spil\" id=\"nytspilknap\"></form>");
//        g.nulstil(name);
//    }
//}            
            
// når man forsøger at gætte sker følgende:
//
// type Status report
//
// messageNot Found
//
// descriptionThe requested resource is not available.

            
            
            
            
            
            
            
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
