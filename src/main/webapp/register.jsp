<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="uts.isd.model.*"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Register</title>
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/css/register.css"
    />
    <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet'>
    <script src="https://kit.fontawesome.com/99df4661f3.js" crossorigin="anonymous"></script>
  </head>
  <body>
    <% 
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String phone = request.getParameter("phone");
            String city = request.getParameter("city");
            String country = request.getParameter("country");
            String role = request.getParameter("role");
            String tos = request.getParameter("tos");
            String submitted = request.getParameter("submitted");

            if (submitted != null){
                User user = new User(email, name, password, phone, city, country, role);
                session.setAttribute("user", user);
            }
    %>
    <% if (submitted != null) { %>
            <div class="confirmation">
              <i class="fa-regular fa-circle-check" style="color: #63E6BE; font-size: 8rem; display: flex; justify-content: center; align-items:center;"></i>
              <br>
              <h1>Congratulations! 
              <br>
              <%= name%></h1>
              <div>
                  <p class="message">Your account has been successfully created. Please Log-in to continue</p>
              </div>
              <div class="button-container">
                <a class="login-button" href="login.jsp" style="text-decoration:none;color:#f5f5f5;">Login</a>
              </div>
            </div>

    <% } else { %>
    <div class="container">
      <form method="post" class="registration-form" action="/userinsert">
        <h2>Registration</h2>
        <br>

        <div class="row">
          <div class="column">
            <label for="name">Full Name</label>
            <input type="text" id="name" name="name" required />
          </div>
          <div class="column">
            <label for="role">Staff / Customer </label>
            <select id="role" name="role" required>
              <option value="">Please Select</option>
              <!-- Placeholder for asking a choice -->
              <option value="staff">Staff</option>
              <option value="customer">Customer</option>
            </select>
          </div>
        </div>

        <div class="row">
          <div class="column">
            <label for="email">Email</label>
            <input type="text" id="email" name="email" required />
          </div>
          <div class="column">
            <label for="phone">Phone Number</label>
            <input type="text" id="phone" name="phone" required />
          </div>
        </div>

        <div class="row">
          <div class="column">
            <label for="country">Country</label>
            <input type="text" id="country" name="country" required />
          </div>
          <div class="column">
            <label for="city">City</label>
            <input type="text" id="city" name="city" required />
          </div>
        </div>

        <div class="row">
          <div class="column">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required />
          </div>
          <div class="column">
            <label for="confirmPassword">Confirm Password</label>
            <input
              type="password"
              id="confirmPassword"
              name="confirmPassword"
              required
            />
            <br>
            <label for="tos">TOS:</label>
              <input type="checkbox" name="tos" id="tos" placeholder="tos" />
            <br />
          </div>
        </div>
        <div class="button-container">
          <input type="hidden" name="submitted" id="submitted" value="true" />
          <button type="submit" class="submit-button">Register</button>
        </div>
      </form>

      <div class="button-container">
      <a href="${pageContext.request.contextPath}/" class="cancel-button"
            >Cancel Registration</a
          >
      <div>
      <% } %>
  </body>
</html>
