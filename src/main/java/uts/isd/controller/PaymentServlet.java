package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uts.isd.model.Payment;
import uts.isd.model.dao.PaymentDAO;

public class PaymentServlet extends HttpServlet {

    private PaymentDAO paymentDAO;

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        paymentDAO = new PaymentDAO(jdbcURL, jdbcUsername, jdbcPassword);

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
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    CreatePayment(request, response);
                    break;
                case "/delete":
                    deletePayment(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
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

    private void listPayments(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Payment> listPayments = paymentDAO.listAllPayments();
        request.setAttribute("listPayments", listPayments);
        RequestDispatcher dispatcher = request.getRequestDispatcher("paymentListing.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("paymentForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String paymentID = request.getParameter("paymentID");
        Payment existingPayment = paymentDAO.getPayment(paymentID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("paymentForm.jsp");
        request.setAttribute("payment", existingPayment);
        dispatcher.forward(request, response);
    }

    private void CreatePayment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
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

        Payment newPayment = new Payment(paymentID, method, cardNum, expMonth, expYear, cvn, GCNum, pin, paymentAmount,
                paymentDate);
        paymentDAO.CreatePayment(newPayment);
        response.sendRedirect("paymentListing.jsp");
    }

    private void updatePayment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
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

        Payment payment = new Payment(paymentID, method, cardNum, expMonth, expYear, cvn, GCNum, pin, paymentAmount,
                paymentDate);
        paymentDAO.updatePayment(payment);
        response.sendRedirect("paymentListing.jsp");
    }

    private void deletePayment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        // String paymentDate = request.getParameter("paymentDate");
        String paymentID = request.getParameter("paymentID");
        // Need help with this method. Particularly the next line if only takes one
        // paymentDate, it errors and requires 9
        // Payment payment = new Payment(paymentID);
        paymentDAO.deletePayment(paymentID);
        response.sendRedirect("paymentListing.jsp");

    }

    // @Override
    // public void doPost(HttpServletRequest request, HttpServletResponse response)
    // throws ServletException, IOException {
    // HttpSession session = request.getSession();

    // String method = request.getParameter("method");
    // String cardNum = request.getParameter("cardNum");
    // String expMonth = request.getParameter("expMonth");
    // String expYear = request.getParameter("expYear");
    // String cvn = request.getParameter("cvn");
    // String GCNum = request.getParameter("GCNum");
    // String pin = request.getParameter("pin");
    // String paymentAmount = request.getParameter("paymentAmount");
    // String paymentDate = request.getParameter("paymentDate");

    // PaymentDAO paymentDAO = (PaymentDAO) session.getAttribute("paymentDAO");

    // try {

    // paymentDAO.CreatePayment(method, cardNum, expMonth, expYear, cvn, GCNum, pin,
    // paymentAmount,
    // paymentDate);

    // Payment payment = new Payment();
    // payment.setMethod(method);
    // payment.setCardNum(cardNum);
    // payment.setExpYear(expYear);
    // payment.setExpMonth(expMonth);
    // payment.setCVN(cvn);
    // payment.setGCNum(GCNum);
    // payment.setPIN(pin);
    // payment.setPaymentAmount(paymentAmount);
    // payment.setPaymentDate(paymentDate);

    // session.setAttribute("payment", payment);

    // request.getRequestDispatcher("account.jsp").include(request, response);
    // } catch (SQLException e) {
    // System.out.println(e);
    // }
    // }
}