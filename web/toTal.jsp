<%-- 
    Document   : toTal
    Created on : 28-02-2017, 11:33:12
    Author     : Martin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <title>Udregn to tal!</title>
    </head>
    <body>
        <%
  String tal1 = request.getParameter("tal1");
  String tal2 = request.getParameter("tal2");
  if (tal1 == null || tal2 == null) {
    %>
      <form>
    Første tal : <input type="number"   name="tal1">   <br>
    Andet tal  : <input type="number"   name="tal2">   <br>
                 <input type="submit" name="OK" value="Regn det ud!">
  </form>
    <%
  } else {
    int talsum = Integer.parseInt(tal1) + Integer.parseInt(tal2);
    %>
     <%= tal1 %> + <%= tal2 %> = <%= talsum %>
    <%      
  }
    %>
    </body>
</html>
