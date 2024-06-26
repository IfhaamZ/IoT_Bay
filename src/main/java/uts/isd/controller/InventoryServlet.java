package uts.isd.controller;

import uts.isd.dao.DBConnector;
import uts.isd.dao.DBManager;
import uts.isd.model.Product;

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
                case "/productdelete":
                    deleteProduct(request, response);
                    break;
                case "/productedit":
                    showEditForm(request, response);
                    break;
                case "/productupdate":
                    updateProduct(request, response);
                    break;
                case "/productview":
                    listProducts(request, response);
                    break;
                case "/productsearch":
                    searchProduct(request, response);
                    break;
                case "/productsearchC":
                    searchProductC(request, response);
                    break;
                default:
                    listInventory(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    private void listInventory(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        ArrayList<Product> products = dbManager.displayProducts();
        request.setAttribute("productslist", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("displayProducts.jsp");
        dispatcher.forward(request, response);
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        ArrayList<Product> products = dbManager.displayProducts();
        request.setAttribute("productview", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("displayProductsC.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("productForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String productID = request.getParameter("productID");
        Product existingProduct = dbManager.getProduct(productID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("productForm.jsp");
        request.setAttribute("product", existingProduct);
        dispatcher.forward(request, response);
    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String productID = request.getParameter("productID");
        String productName = request.getParameter("productName");
        String description = request.getParameter("description");
        float price = Float.parseFloat(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        String category = request.getParameter("category");
        String supplier = request.getParameter("supplier");
        Date manuDate = Date.valueOf(request.getParameter("manuDate"));

        if (dbManager.getProduct(productID) != null) {
            request.setAttribute("error", "duplicateID");
            request.getRequestDispatcher("productForm.jsp").forward(request, response);
            return;
        }

        if (DBManager.insertProduct(new Product(productID, productName, description, price, stock, category, supplier, manuDate))){
            response.sendRedirect("productslist");
            return;
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String productID = request.getParameter("productID");
        dbManager.deleteProduct(productID);
        response.sendRedirect("productslist");
    }
    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String productID = request.getParameter("productID");
        String productName = request.getParameter("productName");
        String description = request.getParameter("description");
        float price = Float.parseFloat(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        String category = request.getParameter("category");
        String supplier = request.getParameter("supplier");
        Date manuDate = Date.valueOf(request.getParameter("manuDate"));
        
        Product product = new Product(productID, productName, description, price, stock, category, supplier, manuDate);
        DBManager.updateProduct(product);
        response.sendRedirect("productslist");
    }
    private void searchProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("productName");
        String category = request.getParameter("category");

        List<Product> searchResults = dbManager.searchProduct(name, category);
        request.setAttribute("productslist", searchResults);
        RequestDispatcher dispatcher = request.getRequestDispatcher("displayProducts.jsp");
        dispatcher.forward(request, response);
    }
    private void searchProductC(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("productName");
        String category = request.getParameter("category");

        List<Product> searchResults = dbManager.searchProduct(name, category);
        request.setAttribute("productview", searchResults);
        RequestDispatcher dispatcher = request.getRequestDispatcher("displayProductsC.jsp");
        dispatcher.forward(request, response);
    }
    
}