<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@page
import="uts.isd.model.*"%>

<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="/css/main.css" />
    <link
      href="https://fonts.googleapis.com/css?family=Roboto"
      rel="stylesheet"
    />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Main</title>
  </head>
  <% User user = (User) session.getAttribute("user"); %>
  <body>
    <header>
      <div
        style="
          display: flex;
          justify-content: space-between;
          width: 100%;
          align-items: center;
        "
      >
        <h1>TechTide</h1>
      </div>
      <div>
        <nav>
          <ul>
            <li><a href="logout.jsp">Logout</a></li>
            <!-- Add other navigation elements as needed -->
          </ul>
        </nav>
      </div>
    </header>
  </body>
</html>
