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
  <script>
    const dropdownToggles = document.querySelectorAll('.dropdown-content');

    dropdownToggles.forEach((toggle) => {
      toggle.addEventListener('click', () => {
        const dropdownMenu = toggle.nextElementSibling;
    dropdownMenu.style.display = dropdownMenu.style.display === 'block' ? 'none' : 'block';
  });
});
  </script>
  <body>
    <% User user = (User) session.getAttribute("user"); %>
    <% String name = (user != null) ? user.getName() : ""; %>
    <header>
      <div style="display: flex; justify-content: space-between; width: 100%; align-items: center;">
        <h1>TechTide</h1>
      </div>
      <div>
        <nav>
          <ul>
            <% if (user != null) { %>
              <li><a href="logout.jsp">Logout</a></li>
              <li><%=name %></li>
              <% } else { %>
              <li><a href="register.jsp">Register</a></li>
              <li><a href="index.jsp">Home</a></li>
              <% } %>
          </ul>
        </nav>
      </div>
    </header>
  </body>
</html>
