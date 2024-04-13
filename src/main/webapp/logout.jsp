<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@page
import="java.util.Random"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!-- <link rel="stylesheet" href="css/layout.css"> -->
    <script type="text/javascript" src="js/index.js"></script>
    <title>Logout</title>
  </head>

  <body onload="startTime()">
    <% String name = request.getParameter("name"); %>
    <h1>User <%= name %> has logged out</h1>
    <p>
      Click <a class="link" href="index.jsp">here</a> to return to the home
      page.
    </p>
    <% if(null != session){ session.invalidate(); } %>
  </body>
</html>
