package uts.isd.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uts.isd.dao.DBManager;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@WebServlet("/UpdateOrderServlet")
public class DeleteOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
}
