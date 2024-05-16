package uts.isd.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.PaymentDAO;

public class ConnServlet extends HttpServlet{
    private DBConnector db;
    private PaymentDAO paymentDAO;
    private Connection con;
    
    @Override
    public void init() {
        try {
            db = new DBConnector();
        } catch (SQLException | ClassNotFoundException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        this.con = db.openConnection();

        try {
            this.paymentDAO = new PaymentDAO(con);
        } catch (SQLException e) {
            System.out.println(e);
        }

        session.setAttribute("paymentDAO", paymentDAO);
        request.getRequestDispatcher("index.jsp").include(request, response);
    }

    @Override
    public void destroy() {
        try {
            db.closeConnection();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
