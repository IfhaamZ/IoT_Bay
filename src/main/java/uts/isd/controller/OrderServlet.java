package uts.isd.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uts.isd.model.Order;
import uts.isd.dao.DBManager;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

// @WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("view".equals(action)) {
            viewOrder(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "create":
                createOrder(request, response);
                break;
            case "update":
                updateOrder(request, response);
                break;
            case "delete":
                deleteOrder(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void createOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");

        int customerID = Integer.parseInt(request.getParameter("customerID"));
        String datePlaced = request.getParameter("datePlaced");
        String status = "pending"; // Initial status
        String shippingAddress = request.getParameter("shippingAddress");
        String billingAddress = request.getParameter("billingAddress");
        String createdBy = request.getParameter("createdBy");
        String createdDate = request.getParameter("createdDate");

        Order order = new Order(0, datePlaced, status, customerID);
        order.setShippingAddress(shippingAddress);
        order.setBillingAddress(billingAddress);
        order.setCreatedBy(createdBy);
        order.setCreatedDate(createdDate);

        try {
            manager.insertOrder(order);
            session.setAttribute("order", order);
            request.getRequestDispatcher("orderSuccess.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error creating order");
            request.getRequestDispatcher("createOrder.jsp").forward(request, response);
        }
    }

    private void updateOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");

        int orderID = Integer.parseInt(request.getParameter("orderID"));
        String datePlaced = request.getParameter("datePlaced");
        String status = request.getParameter("status");
        String shippingAddress = request.getParameter("shippingAddress");
        String billingAddress = request.getParameter("billingAddress");
        String createdBy = request.getParameter("createdBy");
        String createdDate = request.getParameter("createdDate");

        try {
            Order order = manager.getOrder(orderID);
            if (order != null) {
                order.setDatePlaced(datePlaced);
                order.setStatus(status);
                order.setShippingAddress(shippingAddress);
                order.setBillingAddress(billingAddress);
                order.setCreatedBy(createdBy);
                order.setCreatedDate(createdDate);
                manager.updateOrder(order);
                session.setAttribute("order", order);
                request.getRequestDispatcher("orderUpdateSuccess.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Order not found");
                request.getRequestDispatcher("updateOrder.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error updating order");
            request.getRequestDispatcher("updateOrder.jsp").forward(request, response);
        }
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");

        int orderID = Integer.parseInt(request.getParameter("orderID"));

        try {
            manager.deleteOrder(orderID);
            request.getRequestDispatcher("orderDeleteSuccess.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error deleting order");
            request.getRequestDispatcher("deleteOrder.jsp").forward(request, response);
        }
    }

    private void viewOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");

        int orderID = Integer.parseInt(request.getParameter("orderID"));
        try {
            Order order = manager.getOrder(orderID);
            session.setAttribute("order", order);
            request.getRequestDispatcher("viewOrder.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error fetching order details");
            request.getRequestDispatcher("viewOrder.jsp").forward(request, response);
        }
    }

}
