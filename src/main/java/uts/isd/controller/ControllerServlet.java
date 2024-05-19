package uts.isd.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

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
                case "/staff":
                    handleStaffRequests(request, response);
                    break;
                case "/product":
                    handleProductRequests(request, response);
                    break;
                case "/payment":
                    handlePaymentRequests(request, response);
                    break;
                default:
                    showDefaultPage(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    // Handle requests related to staff management
    private void handleStaffRequests(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/StaffServlet");
        dispatcher.forward(request, response);
    }

    // Handle requests related to product management
    private void handleProductRequests(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/InventoryServlet");
        dispatcher.forward(request, response);
    }

    // Handle requests related to payment processing
    private void handlePaymentRequests(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/PaymentServlet");
        dispatcher.forward(request, response);
    }

    // Show the default page (index.jsp)
    private void showDefaultPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
