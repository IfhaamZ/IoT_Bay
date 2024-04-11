<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Register</title>
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/css/register.css"
    />
  </head>
  <body>
    <% String email = request.getParameter("email"); String name =
    request.getParameter("name"); String tos = request.getParameter("tos");
    String submitted = request.getParameter("submitted"); if (submitted != null)
    { %>
    <h1>Welcome</h1>
    <h2>Email: <%= email %></h2>
    <h2>Name: <%= name %></h2>
    <% } else { %>
    <div class="container">
      <form action="/submit-form" method="post" class="registration-form">
        <h2>Registration</h2>

        <div class="row">
          <div class="column">
            <label for="full-name">Full Name</label>
            <input type="text" id="full-name" name="fullname" required />
          </div>
          <div class="column">
            <label for="personStat">Staff / Customer </label>
            <select id="personStat" name="personStat" required>
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
            <label for="phoneNum">Phone Number</label>
            <input type="text" id="phoneNum" name="phoneNum" required />
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
            <label for="Password">Password</label>
            <input type="password" id="password" name="password" required />
          </div>
          <div class="column">
            <label for="ConfirmPassword">Confirm Password</label>
            <input
              type="password"
              id="ConfirmPassword"
              name="ConfirmPassword"
              required
            />
          </div>
        </div>

        <div class="button-container">
          <button type="submit" class="submit-button">Register</button>
          <a href="${pageContext.request.contextPath}/" class="cancel-button"
            >Cancel Registration</a
          >
        </div>
      </form>
    </div>
    <% } %>
  </body>
</html>
