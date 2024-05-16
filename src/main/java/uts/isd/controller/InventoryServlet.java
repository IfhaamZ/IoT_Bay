package uts.isd.controller;

<<<<<<< HEAD

=======
>>>>>>> 3d2044a617155110454948b14ca8cbd8211819c7
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
<<<<<<< HEAD
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
=======
>>>>>>> 3d2044a617155110454948b14ca8cbd8211819c7
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

<<<<<<< HEAD
    private void listProducts(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        ArrayList<Product> products = dbManager.displayProducts();
        request.setAttribute("productview", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("displayProductsC.jsp");
        dispatcher.forward(request, response);
    }

=======
>>>>>>> 3d2044a617155110454948b14ca8cbd8211819c7
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("productForm.jsp");
        dispatcher.forward(request, response);
    }

<<<<<<< HEAD
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String productID = request.getParameter("productID");
        Product existingProduct = dbManager.getProduct(productID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("productForm.jsp");
        request.setAttribute("product", existingProduct);
        dispatcher.forward(request, response);
    }

=======
>>>>>>> 3d2044a617155110454948b14ca8cbd8211819c7
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
<<<<<<< HEAD
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
=======
>>>>>>> 3d2044a617155110454948b14ca8cbd8211819c7
}