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
       <title>Udregn to tal!</title>
    </head>
    <body>
        <%
             //local server
        URL url = new URL("http://localhost:3043/galgelegtjeneste?wsdl");
        
        //jacobs server
       // URL url = new URL("http://ubuntu4.javabog.dk:9943/galgelegtjeneste?wsdl");
        QName qname = new QName("http://galgeleg/", "GalgelegImplService");
              QName qnameport = new QName("http://galgeleg/", "GalgelegImplPort");
        Service service = Service.create(url, qname);
        GalgelegI g = service.getPort(qnameport,GalgelegI.class);
        
  String name = request.getParameter("name");
  String pass = request.getParameter("pass");
  
  
  if (name == null || pass == null) {
    %>
      <form>
    Første tal : <input type="text"   name="name">   <br>
    Andet tal  : <input type="password"   name="pass">   <br>
                 <input type="submit" name="OK" value="Log ind">
  </form>
    <%
  } else {
while(true){
if(g.hentBruger(name, pass)){
break;
}
else{
out.println("Forkert bruger eller password");

}}
out.println("Du er nu logget ind");


//    int talsum = Integer.parseInt(tal1) + Integer.parseInt(tal2);
         
  }
    %>
    </body>
</html>
