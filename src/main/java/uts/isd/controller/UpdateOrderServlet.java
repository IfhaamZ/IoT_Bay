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

// @WebServlet("/UpdateOrderServlet")
public class UpdateOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
                // Assuming you have a method to update the order in the database
                manager.updateOrderStatus(orderID, status);

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
}
