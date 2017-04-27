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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Martin
 */
@WebServlet(name = "highscoreServlet", urlPatterns = {"/highscoreServlet"})
public class highscoreServlet extends HttpServlet {
    
    String name;
    Connector connector = new Connector();
    
    public ArrayList<scoreDTO> getScores() {
        ArrayList<scoreDTO> list = new ArrayList<scoreDTO>();
        ResultSet rs = null;
        try {
            rs = connector.doQuery("SELECT * FROM galgescores.highscores ORDER BY score DESC LIMIT 10");
        } catch (SQLException e) {
            
        }
        try {
            while (rs.next()) {
		list.add(new scoreDTO(rs.getString("studentID"), rs.getInt("score")));
		}
            } catch (SQLException e) {
                
            }
        
        return list;
    }
    
    

    
    
    
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

out.println("<p2>Highscores</p2><br>");


ArrayList<scoreDTO> list = getScores();



out.println("<div align=\"center\">");
out.println("        <table border=\"0\" cellpadding=\"10\">");
out.println("            <tr>");
out.println("                <th>Spiller</th>");
out.println("                <th>score</th>");
out.println("            </tr>");
out.println("            <tr>");
    for (int i = 0; i < list.size(); i++) {
        out.println("<tr>");
        out.println("<td>" + list.get(i).getUserID() + "</td>");
        out.println("<td>" + list.get(i).getScore() + "</td>");
        out.println("</tr>");
    }
out.println("</table>");
out.println("</div><br>");












//out.println("<sql:setDataSource" +
//"        var=\"myDS\""+
//"        driver=\"com.mysql.jdbc.Driver\""+
//"        url=\"jdbc:mysql://localhost:3306/galgescores\""+
//"        user=\"root\" password=\"\""+
//"    />");
//
//out.println("<sql:query var=\"listScores\"   dataSource=\"${myDS}\">"+
//"SELECT * FROM galgescores.highscores;"+
//"</sql:query>");
//
//out.println("<div align=\"center\">");
//out.println("        <table border=\"1\" cellpadding=\"5\">");
//out.println("            <tr>");
//out.println("                <th>studentID</th>");
//out.println("                <th>score</th>");
//out.println("            </tr>");
//out.println("            <c:forEach var=\"student\" items=\"${listScores.rows}\">");
//out.println("                <tr>");
//out.println("                    <td><c:out value=\"${student.studentID}\" /></td>");
//out.println("                    <td><c:out value=\"${student.score}\" /></td>");
//out.println("                </tr>");
//out.println("            </c:forEach>");
//out.println("        </table>");
//out.println("    </div><br>");









out.println("<form method=\"POST\" action=\"MinServlet\">");
out.println("<input type=\"text\" name=\"name\" value="+name+" readonly hidden/>");
out.println("<input type=\"submit\" name=\"singleMultiTilbage\" value=\"Gå tilbage\"></form><br>");

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
