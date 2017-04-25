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




//String a = g.log(name);
//int indexstring = a.indexOf("Antal forkerte bogstaver");
//a = a.substring(indexstring+27, indexstring+28);
//int status = 7;
//try {
//    status = Integer.parseInt(a);
//} catch (NumberFormatException e) {
//    status = 7;
//}
//
//
//switch (status) {
//    case 0:
//        out.println("<img src=\"http://i65.tinypic.com/2cnb88j.png\" border=\"0\" alt=\"Galge\">");
//        break;
//    case 1:
//        out.println("<img src=\"http://i68.tinypic.com/2kem28.png\" border=\"0\" alt=\"forkert 1\">");
//        break;
//    case 2:
//        out.println("<img src=\"http://i67.tinypic.com/2v9c7cy.png\" border=\"0\" alt=\"forkert 2\">");
//        break;
//    case 3:
//        out.println("<img src=\"http://i68.tinypic.com/34exoqh.png\" border=\"0\" alt=\"forkert 3\">");
//        break;
//    case 4:
//        out.println("<img src=\"http://i67.tinypic.com/1z3uxdv.png\" border=\"0\" alt=\"forkert 4\">");
//        break;
//    case 5:
//        out.println("<img src=\"http://i65.tinypic.com/25qxlk8.png\" border=\"0\" alt=\"forkert 5\">");
//        break;
//    case 6:
//        out.println("<img src=\"http://i65.tinypic.com/optn2b.png\" border=\"0\" alt=\"forkert 6\">");
//        break;
//        }




//String singleEllerMulti = "0";
//
//if (singleEllerMulti.equals("0")) {

//Vælg singleplayer eller multiplayer eller log ud
out.println("<form method=\"POST\" action=\"singleplayerServlet\">");
out.println("<p id=\"singleEllerMulti\">Vælg en funktion</p><br>");
out.println("<input type=\"text\" name=\"name\" value="+name+" readonly hidden/>");
out.println("<input type=\"submit\" name=\"singleMultiTilbage\" value=\"Singleplayer\"></form><br>");

out.println("<form method=\"POST\" action=\"MinServlet\">");
out.println("<input type=\"text\" name=\"name\" value="+name+" readonly hidden/>");
out.println("<input type=\"submit\" name=\"singleMultiTilbage\" value=\"Multiplayer\"></form><br>");

out.println("<form method=\"POST\" action=\"galgelegWeb.jsp\">");
out.println("<input type=\"text\" name=\"name\" value="+name+" readonly hidden/>");
out.println("<input type=\"submit\" name=\"singleMultiTilbage\" value=\"Log ud\"></form>");

//    singleEllerMulti = request.getParameter("singleMultiTilbage");
//    if (singleEllerMulti.equals("Singleplayer")) {
//        out.print("<p> Du har valgt singleplayer </p>");
//    } else if (singleEllerMulti.equals("Multiplayer")) {
//        out.print("<p> Du har valgt multiplayer </p>");
//    } else {
//        //Her skal den gå tilbage til galgelegWeb.jsp. Det finder vi ud af senereeeeeeee.
//    }
//}

//if (singleEllerMulti.equals("Singleplayer")) {
//    String fortsæt = "0";
//    if (g.isContinueAvailable(name) == true){
//        out.println("<form method=\"POST\" action=\"MinServlet\">");
//        out.println("<p id=\"single\">Du har allerede et igangværende spil, ønsker du at spille videre eller starte et nyt?</p>");
//        out.print("<input type=\"submit\" name=\"fortsæt\" value=\"Fortsæt gammelt spil\">");
//        out.print("<input type=\"submit\" name=\"fortsæt\" value=\"Start nyt spil\">");
//        out.print("<input type=\"submit\" name=\"fortsæt\" value=\"Tilbage\"></form>");
//    }
//    else {
//        out.println("<form method=\"POST\" action=\"MinServlet\">");
//        out.println("<p id=\"single2\">Du har ikke et igangværende spil, ønsker du at starte et?</p>");
//        out.print("<input type=\"submit\" name=\"fortsæt\" value=\"Start nyt spil\"></form>");
//        out.print("<input type=\"submit\" name=\"fortsæt\" value=\"Tilbage\"></form>");
//    }
//
//    fortsæt = request.getParameter("fortsæt");
//    if (fortsæt.equals("Fortsæt gammelt spil")) {
//        out.print("<p> Du har valgt at fortsætte dit gamle spil </p>");
//    } else if (fortsæt.equals("Start nyt spil")) {
//        out.print("<p> Du har valgt at starte et nyt spil </p>");
//    } else {
//        //Her skal den gå tilbage til galgelegWeb.jsp. Det finder vi ud af senereeeeeeee.
//    }
//}




//    if(!g.spilSlut()){
//        //Herfra kører spillet
//        out.println("<p>"+g.logWeb(name)+"</p>");
//        out.println("<form method=\"POST\" action=\"MinServlet\">");
//        out.println("<input type=\"String\" id=\"guess\"  name=\"guess\" autofocus>   <br>");
//        out.println("<p id=\"yourguess\"> dit gæt</p>");
//        out.println("<input type=\"submit\" name=\"guessKnap\" value=\"Gæt\"></form>");
//    }



//    If Request.Form("submitButton") = "Previous Page" Then
//    ' Code for Previous Page
//ElseIf Request.Form("submitButton") = "Next Page" Then
//    ' Code for Next Page
//End If

//    if (Request.Form("submitButton") == "Previos") {
//    //update action
//    } else if (isset($_POST['multiplayerKnap'])) {
//    //delete action
//    } else {
//    //no button pressed
//    }





//    if(!g.spilSlut()){
//        //Herfra kører spillet
//        out.println("<p>"+g.logWeb(name)+"</p>");
//        out.println("<form method=\"POST\" action=\"MinServlet\">");
//        out.println("<input type=\"String\" id=\"guess\"  name=\"guess\" autofocus>   <br>");
//        out.println("<p id=\"yourguess\"> dit gæt</p>");
//        out.println("<input type=\"submit\" name=\"guessKnap\" value=\"Gæt\"></form>");
//    }
//    else if(g.spilSlut()){
//        out.println("<p>"+g.logWeb(name)+"</p>");
//        out.println("<form method=\"POST\" action=\"MinServlet\">");
//        out.println("<input type=\"submit\" name=\"guessKnap\" value=\"Start nyt spil\" id=\"nytspilknap\"></form>");
//        g.nulstil(name);
//    }
//}



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
