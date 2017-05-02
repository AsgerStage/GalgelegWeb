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
@WebServlet(name = "spilSingleServlet", urlPatterns = {"/spilSingleServlet"})
public class spilSingleServlet extends HttpServlet {
    
    String name;
    String nulstil = "nej";


    Connector connector = new Connector();
    
//    public int getScores() {
//        int score = 0;
//        ResultSet rs = null;
//        try {
////            connector.doUpdate("INSERT INTO highscores (studentID, score) VALUES ('"+name+"', 0);");
//            rs = connector.doQuery("SELECT score FROM galgescores.highscores WHERE studentID = '"+name+"'");
//        } catch (SQLException e) {}
//        try {
//            while (rs.next()) {
//		score = rs.getInt("score");
//		}
//            } catch (SQLException e) {}
//        System.out.println("score 1: "+score);
//        return score;
//    }
    
    public void updateScore() {
        try {
//            System.out.println("score 2: "+score);
//            score = score + 1;
//            System.out.println("score 3: "+score);
            connector.doUpdate("INSERT INTO highscores (studentID, score) VALUES ('"+name+"', 1) ON DUPLICATE KEY UPDATE score=score+1;");
//            connector.doUpdate("UPDATE highscores SET score ="+score+" WHERE studentID = '"+name+"'");
        } catch (SQLException ex) {}
    }
    
    
    
    
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
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheet.css\">");
            out.println("<title>Galgeleg</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Don Frankos Mobs Galgeleg</h1>");
            
            
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
out.println("<input type=\"text\" name=\"nulstil\" value=\"nulstilMigIkke\" readonly hidden/>");
out.println("<input type=\"text\" name=\"name\" value="+name+" readonly hidden/>");
out.println("<input type=\"submit\" name=\"guessKnap\" value=\"Gæt\"></form><br>");

out.println("<form method=\"POST\" action=\"singleplayerServlet\">");
out.println("<input type=\"text\" name=\"name\" value="+name+" readonly hidden/>");
out.println("<input type=\"submit\" name=\"fortsæt\" value=\"Afslut spil\"></form>");
}
else if(g.spilSlut()){
    


    //highscore
String b = g.logWeb(name);
int indexstringb = b.indexOf("SPILLET ER ");
b = b.substring(indexstringb+11, indexstringb+12);
if (b.startsWith("V"))
    updateScore();


    out.println("<p>"+g.logWeb(name)+"</p>");
    out.println("<form method=\"POST\" action=\"singleplayerServlet\">");
    out.println("<input type=\"text\" name=\"nulstil\" value=\"nulstilMigIkke\" readonly hidden/>");
    out.println("<input type=\"text\" name=\"name\" value="+name+" readonly hidden/>");
    out.println("<input type=\"submit\" name=\"guessKnap\" value=\"Start nyt spil\" id=\"nytspilknap\"></form><br>");
    out.println("<form method=\"POST\" action=\"MinServlet\">");
    out.println("<input type=\"text\" name=\"name\" value="+name+" readonly hidden/>");
    out.println("<input type=\"submit\" name=\"fortsæt\" value=\"Hovedmenu\"></form>");
    g.nulstil(name);
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
