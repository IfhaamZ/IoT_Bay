<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@page
import="uts.isd.model.*"%>

<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/welcome.css" />
    <link
      href="https://fonts.googleapis.com/css?family=Roboto"
      rel="stylesheet"
    />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Welcome</title>
  </head>
  
  <body>
    <% User user = (User) session.getAttribute("user"); %>
    <header>
      <div style="display: flex; justify-content: space-between; width: 100%; align-items: center;">
        <h1>TechTide</h1>
      </div>
      <div>
        <nav>
          <ul>
            <li><a href="logout.jsp">Logout</a></li>
          </ul>
        </nav>
      </div>
    </header>

    <% String name = user.getName(); %>

    <main>
      <div>
        <h1>Welcome, <%= name%>!</h1>
        <p>
          Explore our latest devices and technologies designed to enhance your
          digital experience.
        </p>
        <br />
        <a href="main.jsp">
          <button>Explore</button>
        </a>
      </div>
    </main>
  </body>
</html>
