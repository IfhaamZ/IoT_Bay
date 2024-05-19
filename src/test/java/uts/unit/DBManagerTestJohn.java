package uts.unit;

import org.junit.jupiter.api.*;
import uts.isd.dao.DBConnector;
import uts.isd.dao.DBManager;
import uts.isd.model.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DBManagerTestJohn {

    private Connection conn;
    private DBManager dbManager;

    @BeforeAll
    public void setup() throws ClassNotFoundException, SQLException {
        conn = DBConnector.getConnection();
        dbManager = new DBManager(conn);
    }

    @AfterAll
    public void tearDown() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    @BeforeEach
    public void beforeEachTest() throws SQLException {
        conn.createStatement().execute("DELETE FROM `order`");
    }

    @Test
    public void testConnection() throws SQLException {
        assertNotNull(conn);
        // Try to execute a simple query to verify the connection
        try (Statement stmt = conn.createStatement()) {
            stmt.executeQuery("SELECT 1");
        }
    }

    @Test
    public void testInsertOrder() throws SQLException {
        Order order1 = new Order(1, "2024-05-19", "Shipped", 124, "789 Street", "101 Avenue", "Admin2", "2024-05-19");
        boolean result = DBManager.insertOrder(order1);
        assertTrue(result);

        List<Order> orderList = dbManager.fetchOrders();
        assertEquals(1, orderList.size());
        Order insertedOrder = orderList.get(0);
        System.out.println("Inserted Order ID: " + insertedOrder.getOrderID());
        assertEquals("Pending", insertedOrder.getStatus());
    }

    @Test
    public void testFetchOrders() throws SQLException {
        Order order1 = new Order(1, "2024-05-19", "Shipped", 124, "789 Street", "101 Avenue", "Admin2", "2024-05-19");
        DBManager.insertOrder(order1);

        List<Order> orderList = dbManager.fetchOrders();
        assertEquals(1, orderList.size());
        Order fetchedOrder = orderList.get(0);
        System.out.println("Fetched Order ID: " + fetchedOrder.getOrderID());
        assertEquals("Pending", fetchedOrder.getStatus());
    }

    @Test
    public void testUpdateOrder() throws SQLException {
        // Insert initial order
        Order order1 = new Order(1, "2024-05-19", "Shipped", 124, "789 Street", "101 Avenue", "Admin2", "2024-05-19");

        DBManager.insertOrder(order1);

        List<Order> orderList = dbManager.fetchOrders();
        Order insertedOrder = orderList.get(0);

        // Update order details
        Order updatedOrder = new Order(insertedOrder.getOrderID(), "2024-05-19",
                "Shipped", 124, "789 Street",
                "101 Avenue", "AdminUpdated",
                "2024-05-19");
        boolean result = DBManager.updateOrder(updatedOrder);
        assertTrue(result);

        // Fetch and verify updated order
        orderList = dbManager.fetchOrders();
        assertEquals(1, orderList.size());
        Order fetchedOrder = orderList.get(0);
        assertEquals(insertedOrder.getOrderID(), fetchedOrder.getOrderID());
        assertEquals("Shipped", fetchedOrder.getStatus());
        assertEquals(124, fetchedOrder.getCustomerID());
        assertEquals("789 Street", fetchedOrder.getShippingAddress());
        assertEquals("101 Avenue", fetchedOrder.getBillingAddress());
        assertEquals("AdminUpdated", fetchedOrder.getCreatedBy());
        assertEquals("2024-05-19", fetchedOrder.getCreatedDate().substring(0, 10));
        // Adjust for date format
    }

    @Test
    public void testDeleteOrder() throws SQLException {
        Order order1 = new Order(1, "2024-05-19", "Shipped", 124, "789 Street", "101 Avenue", "Admin2", "2024-05-19");
        DBManager.insertOrder(order1);

        List<Order> orderList = dbManager.fetchOrders();
        Order insertedOrder = orderList.get(0);

        boolean result = dbManager.deleteOrder(insertedOrder.getOrderID());
        assertTrue(result);

        orderList = dbManager.fetchOrders();
        assertEquals(0, orderList.size());
    }

    @Test
    public void testSearchOrdersByCustomerID() throws SQLException {
        Order order1 = new Order(1, "2024-05-19", "Shipped", 124, "789 Street", "101 Avenue", "Admin2", "2024-05-19");
        DBManager.insertOrder(order1);

        List<Order> orderList = dbManager.fetchOrders();
        Order insertedOrder1 = orderList.get(0);
        Order insertedOrder2 = orderList.get(1);

        List<Order> result = dbManager.searchOrdersByCustomerID(124);
        assertEquals(1, result.size());
        Order foundOrder = result.get(0);
        assertEquals(insertedOrder2.getOrderID(), foundOrder.getOrderID());
        assertEquals("Shipped", foundOrder.getStatus());
        assertEquals(124, foundOrder.getCustomerID());
        assertEquals("789 Street", foundOrder.getShippingAddress());
        assertEquals("101 Avenue", foundOrder.getBillingAddress());
        assertEquals("Admin2", foundOrder.getCreatedBy());
        assertEquals("2024-05-19", foundOrder.getCreatedDate().substring(0, 10)); //
    }
}
