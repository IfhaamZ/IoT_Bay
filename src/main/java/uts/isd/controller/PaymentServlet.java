package uts.isd.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uts.isd.dao.DBConnector;
import uts.isd.dao.DBManager;
import uts.isd.model.Payment;

//servlet relating to all payment management handling and executions
//through the HTML requests

public class PaymentServlet extends HttpServlet {

    private DBManager dbManager;

    // initialising the servlet
    public void init() throws ServletException {
        try {
            Connection conn = DBConnector.getConnection();
            dbManager = new DBManager(conn);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    // routes to appropriate method and jsp page based on request inputted by user
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            // switch case statement to direct user to corresponding page
            // and method, depending on action performed
            switch (action) {
                case "/paymentnew":
                    showNewForm(request, response);
                    break;
                case "/paymentinsert":
                    CreatePayment(request, response);
                    break;
                case "/paymentdelete":
                    deletePayment(request, response);
                    break;
                case "/paymentedit":
                    showEditForm(request, response);
                    break;
                case "/paymentupdate":
                    updatePayment(request, response);
                    break;
                default:
                    listPayments(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    // retrieves a list of all payments, setting list as a request attribute,
    // and then forwarding user to the payment listing page
    private void listPayments(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Payment> listPayments = dbManager.listAllPayments();
        request.setAttribute("listPayments", listPayments);
        RequestDispatcher dispatcher = request.getRequestDispatcher("paymentListing.jsp");
        dispatcher.forward(request, response);
    }

    // forwards use to the payment form page to create a new payment
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("paymentForm.jsp");
        dispatcher.forward(request, response);
    }

    // shows the form to edit an already existing payment
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String paymentID = request.getParameter("paymentID");
        Payment existingPayment = dbManager.getPayment(paymentID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("paymentForm.jsp");
        request.setAttribute("payment", existingPayment);
        dispatcher.forward(request, response);
    }

    // creating a new payment record to be added to the database
    private void CreatePayment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        // retrieving payment details from user inputs            
        String paymentID = request.getParameter("paymentID");
        String method = request.getParameter("method");
        String cardNum = request.getParameter("cardNum");
        String expMonth = request.getParameter("expMonth");
        String expYear = request.getParameter("expYear");
        String cvn = request.getParameter("cvn");
        String GCNum = request.getParameter("GCNum");
        String pin = request.getParameter("pin");
        String paymentAmount = request.getParameter("paymentAmount");
        String paymentDate = request.getParameter("paymentDate");

        // creates a new Payment object with the retrieved details
        Payment newPayment = new Payment(
                paymentID, method, cardNum, expMonth, expYear, cvn, GCNum, pin, paymentAmount,
                paymentDate);
        // inserting the retrieved details into the database as a new payment
        dbManager.createPayment(newPayment);
        // redirecting user to the payment listing page
        response.sendRedirect("paymentlist");
    }

    // updates an existing payment record in the database
    // user selects specific payment to update and this method
    // commences for that specific payment
    // similar to creating a payment however it simply overwrites the existing
    // details for the chosen specific payment instead of adding a new payment
    private void updatePayment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        // retrieving updated attributes from the user
        String paymentID = request.getParameter("paymentID");
        String method = request.getParameter("method");
        String cardNum = request.getParameter("cardNum");
        String expMonth = request.getParameter("expMonth");
        String expYear = request.getParameter("expYear");
        String cvn = request.getParameter("cvn");
        String GCNum = request.getParameter("GCNum");
        String pin = request.getParameter("pin");
        String paymentAmount = request.getParameter("paymentAmount");
        String paymentDate = request.getParameter("paymentDate");

        // creating a new payment object with the retrieved payment details
        Payment payment = new Payment(paymentID, method, cardNum, expMonth, expYear, cvn, GCNum, pin, paymentAmount,
                paymentDate);

        // updates patyment records in the database
        dbManager.updatePayment(payment);
        // redirects user to the payment listing page
        response.sendRedirect("paymentlist");
    }

    // deletes an existing payment record from the database
    private void deletePayment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        // deletes the payment linked to that ID retrieved
        String paymentID = request.getParameter("paymentID");
        // Need help with this method. Particularly the next line if only takes one
        // paymentDate, it errors and requires 9
        Payment payment = new Payment(paymentID);
        dbManager.deletePayment(payment);
        // redirects user to the listing page, now with the payment linked
        // to the payment ID retrieved, removed from listing
        response.sendRedirect("paymentlist");

    }

}