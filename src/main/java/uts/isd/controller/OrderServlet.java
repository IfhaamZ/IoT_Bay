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
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

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
                case "/orderlist":
                    listOrder(request, response);
                    break;
                default:
                    listOrder(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    // List Orders
    private void listOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Order> listOrder = dbManager.getOrdersByCustomerID(Integer.parseInt(request.getParameter("customerID")));
        request.setAttribute("listOrder", listOrder);
        RequestDispatcher dispatcher = request.getRequestDispatcher("orderList.jsp");
        dispatcher.forward(request, response);
    }

    // Show New Order Form
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("orderForm.jsp");
        dispatcher.forward(request, response);
    }

    // Show Edit Order Form
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        Order existingOrder = dbManager.getOrder(orderID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("orderForm.jsp");
        request.setAttribute("order", existingOrder);
        dispatcher.forward(request, response);
    }

    // Insert Order
    private void insertOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int customerID = Integer.parseInt(request.getParameter("customerID"));
        String datePlaced = request.getParameter("datePlaced");
        String status = request.getParameter("status");
        String shippingAddress = request.getParameter("shippingAddress");
        String billingAddress = request.getParameter("billingAddress");
        String createdBy = request.getParameter("createdBy");
        String createdDate = request.getParameter("createdDate");

        Order newOrder = new Order(0, datePlaced, status, customerID);
        newOrder.setShippingAddress(shippingAddress);
        newOrder.setBillingAddress(billingAddress);
        newOrder.setCreatedBy(createdBy);
        newOrder.setCreatedDate(createdDate);

        DBManager.insertOrder(newOrder);
        response.sendRedirect("orderlist");
    }

    // Update Order
    private void updateOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        String datePlaced = request.getParameter("datePlaced");
        String status = request.getParameter("status");
        String shippingAddress = request.getParameter("shippingAddress");
        String billingAddress = request.getParameter("billingAddress");
        String createdBy = request.getParameter("createdBy");
        String createdDate = request.getParameter("createdDate");

        Order updateOrder = new Order(orderID, datePlaced, status, 0);
        updateOrder.setShippingAddress(shippingAddress);
        updateOrder.setBillingAddress(billingAddress);
        updateOrder.setCreatedBy(createdBy);
        updateOrder.setCreatedDate(createdDate);

        dbManager.updateOrder(updateOrder); // Use instance method call
        response.sendRedirect("orderlist");
    }

    // Search Order
    private void searchOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int customerID = Integer.parseInt(request.getParameter("customerID"));

        List<Order> searchResults = dbManager.getOrdersByCustomerID(customerID);
        request.setAttribute("listOrder", searchResults);
        RequestDispatcher dispatcher = request.getRequestDispatcher("orderList.jsp");
        dispatcher.forward(request, response);
    }

    // Delete Order
    private void deleteOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));

        dbManager.deleteOrder(orderID);
        response.sendRedirect("orderlist");
    }
}
