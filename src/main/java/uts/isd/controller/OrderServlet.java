package uts.isd.controller;

import uts.isd.dao.DBConnector;
import uts.isd.dao.DBManager;
import uts.isd.model.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class OrderServlet extends HttpServlet {
    private DBManager dbManager;
    private static final Logger logger = Logger.getLogger(OrderServlet.class.getName());

    public void init() throws ServletException {
        try {
            Connection conn = DBConnector.getConnection();
            dbManager = new DBManager(conn);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // Handle POST requests by forwarding to doGet
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath(); // Determine the action based on the request path

        try {
            switch (action) {
                case "/ordernew":
                    showNewForm(request, response);
                    break;
                case "/orderinsert":
                    insertOrder(request, response);
                    break;
                case "/orderdelete":
                    deleteOrder(request, response);
                    break;
                case "/orderedit":
                    showEditForm(request, response);
                    break;
                case "/orderupdate":
                    updateOrder(request, response);
                    break;
                case "/ordersearch":
                    searchOrder(request, response);
                    break;
                default:
                    listOrder(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Order> listOrder = dbManager.fetchOrders(); // Fetch all orders from the database
        request.setAttribute("listOrder", listOrder); // Set orders as request attribute
        RequestDispatcher dispatcher = request.getRequestDispatcher("orderList.jsp");
        dispatcher.forward(request, response); // Forward to orderList.jsp to display orders
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("orderForm.jsp");
        dispatcher.forward(request, response); // Forward to orderForm.jsp to display new order form
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int orderID = Integer.parseInt(request.getParameter("orderID")); // Get order ID from request
        Order existingOrder = dbManager.getOrder(orderID); // Fetch order details from the database
        RequestDispatcher dispatcher = request.getRequestDispatcher("orderForm.jsp");
        request.setAttribute("order", existingOrder); // Set order details as request attribute
        dispatcher.forward(request, response); // Forward to orderForm.jsp to display order edit form
    }

    private void insertOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        // Retrieve form parameters
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        int customerID = Integer.parseInt(request.getParameter("customerID"));
        String datePlaced = request.getParameter("datePlaced");
        String status = request.getParameter("status");
        String shippingAddress = request.getParameter("shippingAddress");
        String billingAddress = request.getParameter("billingAddress");
        String createdBy = request.getParameter("createdBy");
        String createdDate = request.getParameter("createdDate");

        // Create a new Order object
        Order newOrder = new Order(orderID, datePlaced, status, customerID, shippingAddress, billingAddress, createdBy,
                createdDate);

        // Insert the new order into the database
        dbManager.insertOrder(newOrder);
        response.sendRedirect("orderlist"); // Redirect to the list of orders
    }

    private void updateOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        // Retrieve form parameters
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        int customerID = Integer.parseInt(request.getParameter("customerID"));
        String datePlaced = request.getParameter("datePlaced");
        String status = request.getParameter("status");
        String shippingAddress = request.getParameter("shippingAddress");
        String billingAddress = request.getParameter("billingAddress");
        String createdBy = request.getParameter("createdBy");
        String createdDate = request.getParameter("createdDate");

        // Create an updated Order object
        Order updateOrder = new Order(orderID, datePlaced, status, customerID, shippingAddress, billingAddress,
                createdBy, createdDate);

        // Update the order in the database
        dbManager.updateOrder(updateOrder);
        response.sendRedirect("orderlist"); // Redirect to the list of orders
    }

    private void searchOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int customerID = Integer.parseInt(request.getParameter("customerID")); // Get customer ID from request

        List<Order> searchResults = dbManager.searchOrdersByCustomerID(customerID); // Search orders by customer ID
        request.setAttribute("listOrder", searchResults); // Set search results as request attribute
        RequestDispatcher dispatcher = request.getRequestDispatcher("orderList.jsp");
        dispatcher.forward(request, response); // Forward to orderList.jsp to display search results
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int orderID = Integer.parseInt(request.getParameter("orderID")); // Get order ID from request

        dbManager.deleteOrder(orderID); // Delete the order from the database
        response.sendRedirect("orderlist"); // Redirect to the list of orders
    }
}
