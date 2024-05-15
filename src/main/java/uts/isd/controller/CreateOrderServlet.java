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
public class CreateOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");

        int customerID = Integer.parseInt(request.getParameter("customerID"));
        String datePlaced = request.getParameter("datePlaced");
        String status = request.getParameter("status");
        String shippingAddress = request.getParameter("shippingAddress");
        String billingAddress = request.getParameter("billingAddress");
        String createdBy = request.getParameter("createdBy");
        String createdDate = request.getParameter("createdDate");

        Order order = new Order(0, datePlaced, status, customerID); // orderID will be auto-generated
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
}
