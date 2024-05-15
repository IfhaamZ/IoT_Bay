package uts.isd.controller;

import uts.isd.dao.DBConnector;
import uts.isd.dao.DBManager;
import uts.isd.model.Product;
import uts.isd.model.Staff;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryServlet extends HttpServlet {
    private DBManager dbManager;

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
                case "/productnew":
                    showNewForm(request, response);
                    break;
                case "/productinsert":
                    insertProduct(request, response);
                    break;
                case "/staffdelete":
                    deleteStaff(request, response);
                    break;
                // case "/productedit":
                //     showEditForm(request, response);
                //     break;
                case "/staffupdate":
                    updateStaff(request, response);
                    break;
                default://302
                    listInventory(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
//302
    private void listInventory(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        ArrayList<Product> products = dbManager.displayProducts();
        request.setAttribute("productslist", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("displayProducts.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("productForm.jsp");
        dispatcher.forward(request, response);
    }

    // private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    //         throws SQLException, ServletException, IOException {
    //     String productID = request.getParameter("productID");
    //     Product existingProduct = dbManager.getProduct(productID);
    //     RequestDispatcher dispatcher = request.getRequestDispatcher("productForm.jsp");
    //     request.setAttribute("staff", existingProduct);
    //     dispatcher.forward(request, response);
    // }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String productID = request.getParameter("productID");
        String productName = request.getParameter("productName");
        String description = request.getParameter("description");
        float price = Float.parseFloat(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        String category = request.getParameter("category");
        String supplier = request.getParameter("supplier");
        Date manuDate = Date.valueOf(request.getParameter("manuDate"));

        Product newProduct = new Product(productID, productName, description, price, stock, category, supplier, manuDate);
        DBManager.insertProduct(newProduct);
        response.sendRedirect("productslist");
    }

    private void updateStaff(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String role = request.getParameter("role");
        String department = request.getParameter("department");

        Staff staff = new Staff(email, name, password, phone, city, country, role, department);
        DBManager.updateStaff(staff);
        response.sendRedirect("stafflist");
    }

    private void deleteStaff(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String email = request.getParameter("email");

        dbManager.deleteStaff(email);
        response.sendRedirect("stafflist");
    }
}
