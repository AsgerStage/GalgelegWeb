<%-- 
    Document   : toTal
    Created on : 28-02-2017, 11:33:12
    Author     : Martin
--%>


<%@page import="galgeleg.GalgelegI"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Scanner,javax.xml.namespace.QName,javax.xml.ws.Service,java.net.URL,java.net.MalformedURLException" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <title>Galgeleg</title>
    </head>
    <body>
    <style>
        body {
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
        
    </style>
    <h1>Don Frankos Mobs Galgeleg</h1>
    <img src="https://i.gyazo.com/415abe6259f3857d23520968937c5f65.png" alt="Don Franko">

    
        <%
             //local server
       // URL url = new URL("http://localhost:3043/galgelegtjeneste?wsdl");
        
        //jacobs server
        URL url = new URL("http://ubuntu4.javabog.dk:3043/galgelegtjeneste?wsdl");
        QName qname = new QName("http://galgeleg/", "GalgelegImplService");
              QName qnameport = new QName("http://galgeleg/", "GalgelegImplPort");
        Service service = Service.create(url, qname);
        GalgelegI g = service.getPort(qnameport,GalgelegI.class);
        
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
if(g.hentBruger(name, pass)){
break;
}
else{
out.println("<p>Forkert bruger eller password</p>");

}}
out.println("<p>Du er nu logget ind</p>");
%>
<br>
                                                                                                    
<%
        
//out.println(""+g.logWeb());
   
%>
<!--<script>
function myFunction() { 
    document.getElementById("yourguess").innerHTML = document.getElementById("guess").value;
}
</script>-->

<form method="POST" action="MinServlet">
         
    <!--<input type="String" id="guess"  name="guess">   <br><br><br>-->
    <!--<p id="yourguess"> dit gæt</p>-->
    <input type="submit" name="guessKnap" value="Start spil">

  </form>
<%
   
}
    %>

    </body>
</html>
