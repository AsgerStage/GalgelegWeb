<%-- 
    Document   : toTal
    Created on : 28-02-2017, 11:33:12
    Author     : Martin
--%>


<%@page import="brugerautorisation.data.Bruger"%>
<%@page import="brugerautorisation.transport.soap.Brugeradmin"%>
<%@page import="galgeleg.GalgelegI"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Scanner,javax.xml.namespace.QName,javax.xml.ws.Service,java.net.URL,java.net.MalformedURLException" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
       <title>Galgeleg</title>
    </head>
    <body>
    <style>
/*        body {
            background-color: #000000;
            text-align: center;
        }
        h1 {
            color:#20C20E;
            font-family: Monospace;
        }
        p {
            color:#20C20E;
            margin-bottom: 2px;

        }
        #input2 {
            margin-bottom: 10px
        }
        input {
            background: #34d98f;
            background-image: -webkit-linear-gradient(top, #34d98f, #2bb850);
            background-image: -moz-linear-gradient(top, #34d98f, #2bb850);
            background-image: -ms-linear-gradient(top, #34d98f, #2bb850);
            background-image: -o-linear-gradient(top, #34d98f, #2bb850);
  background-image: linear-gradient(to bottom, #34d98f, #2bb850);
  -webkit-border-radius: 28;
  -moz-border-radius: 28;
  border-radius: 28px;
  font-family: Arial;
  color: #ffffff;
  font-size: 20px;
  padding: 10px 20px 10px 20px;
  text-decoration: none;
}

input:hover {
  background: #3cb0fd;
  background-image: -webkit-linear-gradient(top, #3cb0fd, #3498db);
  background-image: -moz-linear-gradient(top, #3cb0fd, #3498db);
  background-image: -ms-linear-gradient(top, #3cb0fd, #3498db);
  background-image: -o-linear-gradient(top, #3cb0fd, #3498db);
  background-image: linear-gradient(to bottom, #3cb0fd, #3498db);
  text-decoration: none;
}*/
        
    </style>
    <h1>Don Frankos Mobs Galgeleg</h1>
    <img src="https://i.gyazo.com/415abe6259f3857d23520968937c5f65.png" alt="Don Franko">

    
        <%
             //local server
       // URL url = new URL("http://localhost:9943/galgelegtjeneste?wsdl");
        
        //jacobs server
        URL url = new URL("http://ubuntu4.javabog.dk:3033/galgelegtjeneste?wsdl");
        QName qname = new QName("http://galgeleg/", "GalgelegImplService");
              QName qnameport = new QName("http://galgeleg/", "GalgelegImplPort");
        Service service = Service.create(url, qname);
        GalgelegI g = service.getPort(qnameport,GalgelegI.class);
        
        
        
        URL url1 = new URL("http://javabog.dk:9901/brugeradmin?wsdl");
        QName qname1 = new QName("http://soap.transport.brugerautorisation/", "BrugeradminImplService");
        Service service2 = Service.create(url1, qname1);
        Brugeradmin ba = service2.getPort(Brugeradmin.class);
        
  String name = request.getParameter("name");
  String pass = request.getParameter("pass");
  
  
  if (name == null || pass == null) {
    %>
      <form method="post" id="form1">
          <p>Brugernavn</p>
          <input type="text"   name="name" id="input1" autofocus>   <br>
          <p>Adgangskode</p>
          <input type="password"   name="pass" id="input2">   <br>
                 <input type="submit" name="OK" value="Log ind">
  </form>
    <%
  } else {
        while(true){
            Bruger user;
            Boolean loggedin = false;
            try {
                user = ba.hentBruger(name, pass);
                g.playerCheck(name);
                
                if (user.brugernavn.equals(name) && user.adgangskode.equals(pass)) {
                    loggedin = true;
                }
                    
            } catch(Exception e) {
                System.out.println("Forkert login - prøv igen");
            }
//            if(g.hentBruger(name, pass)){
            if (loggedin) {
                break;
            }
            else {
                out.println("<p>Forkert bruger eller password</p>");
            }
        }
out.println("<p>Du er nu logget ind</p>");
%>
<br>
                                                                                                    
<%
        
//out.println(""+g.logWeb());
//out.println("<p>"+name+"</p>");
   
%>
<!--<script>
function myFunction() { 
    document.getElementById("yourguess").innerHTML = document.getElementById("guess").value;
}
</script>-->

<form method="POST" action="MinServlet">
         
    <!--<input type="String" id="guess"  name="guess">   <br><br><br>-->
    <!--<p id="yourguess"> dit gæt</p>-->
    <input type="text" name="name" value="<%out.println(name);%>" readonly hidden/>
    
    <input type="submit" name="guessKnap" value="Forsæt til spillet">

  </form>
<%
   
}
    %>

    </body>
</html>
