package uts.isd.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;

import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uts.isd.model.User;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.DBManager;

public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DBManager dbManager;

    
    /*old init
    public void init() {
        String db_url = getServletContext().getInitParameter("jdbc:mysql://localhost:3306/database");
        String db_user = getServletContext().getInitParameter("root");
        String db_pass = getServletContext().getInitParameter("Password");
 
        userDAO = new UserDAO(db_url, db_user, db_pass);
 
    }
*/
    //new init
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
            case "/usernew":
                showNewForm(request, response);
                break;
            case "/userinsert":
                insertUser(request, response);
                break;
            case "/userdelete":
                deleteUser(request, response);
                break;
            case "/useredit":
                showEditForm(request, response);
                break;
            case "/userupdate":
                updateUser(request, response);
                break;
            case "/useractivate":
                activateUser(request, response);
                break;
            case "/userdeactivate":
                deactivateUser(request, response);
                break;
            case "/usersearch":
                searchUser(request, response);
                break;
            default:
                listUser(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
 
    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> listUser = dbManager.listAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("UserManagement.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("UserForm.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("AccountID"));
        User existingUser = dbManager.getUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("UserForm.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);
 
    }
 
    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
 
        User newUser = new User(0, name, password, email);
        dbManager.insertUser(newUser);
        String redirectURL = "register.jsp";
        response.sendRedirect(redirectURL);
    } 
    
    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("AccountID"));
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
 
        User user = new User(id, name, password, email);
        dbManager.updateUser(user);
        response.sendRedirect("userlist");
    }

    private void searchUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
    String name = request.getParameter("name");
    String phone = request.getParameter("phone");

    List<User> searchResults = dbManager.searchUser(name, phone);
    request.setAttribute("listUser", searchResults);
    RequestDispatcher dispatcher = request.getRequestDispatcher("SearchUser.jsp");
    dispatcher.forward(request, response);
    }
 
    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("AccountID"));
 
        User user = new User(id);
        dbManager.deleteUser(user);
        response.sendRedirect("userlist");
 
    }

    private void activateUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("AccountID"));
        dbManager.activateUser(id);
        response.sendRedirect("userlist");
    }

    private void deactivateUser(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
            int id = Integer.parseInt(request.getParameter("AccountID"));
            dbManager.deactivateUser(id);
            response.sendRedirect("userlist");
        }
    }