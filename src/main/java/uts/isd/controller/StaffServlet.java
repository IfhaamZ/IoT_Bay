package uts.isd.controller;

import uts.isd.dao.DBConnector;
import uts.isd.dao.DBManager;
import uts.isd.model.Staff;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StaffServlet extends HttpServlet {
    private DBManager dbManager;

    // Initialize servlet
    public void init() throws ServletException {
        try {
            // Establish a database connection
            Connection conn = DBConnector.getConnection();
            dbManager = new DBManager(conn);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    // Handle POST requests by forwarding to doGet
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    // Handle GET requests
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the action from the request path
        String action = request.getServletPath();

        try {
            // Route to appropriate method based on action
            switch (action) {
                case "/staffnew":
                    showNewForm(request, response);
                    break;
                case "/staffinsert":
                    insertStaff(request, response);
                    break;
                case "/staffdelete":
                    deleteStaff(request, response);
                    break;
                case "/staffedit":
                    showEditForm(request, response);
                    break;
                case "/staffupdate":
                    updateStaff(request, response);
                    break;
                case "/staffsearch":
                    searchStaff(request, response);
                    break;
                case "/staffactivate":
                    activateStaff(request, response);
                    break;
                case "/staffdeactivate":
                    deactivateStaff(request, response);
                    break;
                default:
                    listStaff(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    // List all staff members
    private void listStaff(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Staff> listStaff = dbManager.fetchStaff();
        request.setAttribute("ListOfStaff", listStaff);
        RequestDispatcher dispatcher = request.getRequestDispatcher("staffList.jsp");
        dispatcher.forward(request, response);
    }

    // Show form for creating new staff member
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("staffForm.jsp");
        dispatcher.forward(request, response);
    }

    // Show form for editing existing staff member
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String email = request.getParameter("email");
        Staff existingStaff = dbManager.getStaff(email);
        RequestDispatcher dispatcher = request.getRequestDispatcher("staffForm.jsp");
        request.setAttribute("staff", existingStaff);
        dispatcher.forward(request, response);
    }

    // Insert new staff member
    private void insertStaff(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        // Get parameters from request
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String role = request.getParameter("role");
        String department = request.getParameter("department");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        // Create new staff object and insert into database
        Staff newStaff = new Staff(email, name, password, phone, city, country, role, department, status);
        DBManager.insertStaff(newStaff);
        response.sendRedirect("stafflist");
    }

    // Update existing staff member
    private void updateStaff(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        // Get parameters from request
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String role = request.getParameter("role");
        String department = request.getParameter("department");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        // Create staff object with updated details and update in database
        Staff updateStaff = new Staff(email, name, password, phone, city, country, role, department, status);
        DBManager.updateStaff(updateStaff);
        response.sendRedirect("stafflist");
    }

    // Search for staff members
    private void searchStaff(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        // Get search parameters from request
        String name = request.getParameter("name");
        String role = request.getParameter("role");
        String department = request.getParameter("department");

        // Search staff in database and forward to list view
        List<Staff> searchResults = dbManager.searchStaff(name, role, department);
        request.setAttribute("ListOfStaff", searchResults);
        RequestDispatcher dispatcher = request.getRequestDispatcher("staffList.jsp");
        dispatcher.forward(request, response);
    }

    // Activate staff member
    private void activateStaff(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String email = request.getParameter("email");
        DBManager.activateStaff(email);
        response.sendRedirect("stafflist");
    }

    // Deactivate staff member
    private void deactivateStaff(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String email = request.getParameter("email");
        DBManager.deactivateStaff(email);
        response.sendRedirect("stafflist");
    }

    // Delete staff member
    private void deleteStaff(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String email = request.getParameter("email");

        // Delete staff from database and redirect to list view
        dbManager.deleteStaff(email);
        response.sendRedirect("stafflist");
    }
}
