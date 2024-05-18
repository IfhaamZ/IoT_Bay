package uts.unit;

import org.junit.jupiter.api.*;
import uts.isd.dao.DBConnector;
import uts.isd.dao.DBManager;
import uts.isd.model.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DBManagerTest {

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
    public void testConnection() {
        assertNotNull(conn);
    }

    @Test
    public void testInsertOrder() throws SQLException {
        Order order = new Order(1, "2024-05-18", "Pending", 123, "123 Street", "456 Avenue", "Admin", "2024-05-18");
        boolean result = DBManager.insertOrder(order);
        assertTrue(result);

        List<Order> orderList = dbManager.fetchOrders();
        assertEquals(1, orderList.size());
        Order insertedOrder = orderList.get(0);
        assertEquals(1, insertedOrder.getOrderID());
        assertEquals("Pending", insertedOrder.getStatus());
    }

    @Test
    public void testFetchOrders() throws SQLException {
        Order order = new Order(1, "2024-05-18", "Pending", 123, "123 Street", "456 Avenue", "Admin", "2024-05-18");
        DBManager.insertOrder(order);

        List<Order> orderList = dbManager.fetchOrders();
        assertEquals(1, orderList.size());
        Order fetchedOrder = orderList.get(0);
        assertEquals(1, fetchedOrder.getOrderID());
        assertEquals("Pending", fetchedOrder.getStatus());
    }

    @Test
    public void testUpdateOrder() throws SQLException {
        // Insert initial order
        Order order = new Order(1, "2024-05-18", "Pending", 123, "123 Street", "456 Avenue", "Admin", "2024-05-18");
        DBManager.insertOrder(order);

        // Update order details
        Order updatedOrder = new Order(1, "2024-05-19", "Shipped", 124, "789 Street", "101 Avenue", "AdminUpdated",
                "2024-05-19");
        boolean result = DBManager.updateOrder(updatedOrder);
        assertTrue(result);

        // Fetch and verify updated order
        List<Order> orderList = dbManager.fetchOrders();
        assertEquals(1, orderList.size());
        Order fetchedOrder = orderList.get(0);
        assertEquals(1, fetchedOrder.getOrderID());
        assertEquals("Shipped", fetchedOrder.getStatus());
        assertEquals(124, fetchedOrder.getCustomerID());
        assertEquals("789 Street", fetchedOrder.getShippingAddress());
        assertEquals("101 Avenue", fetchedOrder.getBillingAddress());
        assertEquals("AdminUpdated", fetchedOrder.getCreatedBy());
        assertEquals("2024-05-19", fetchedOrder.getCreatedDate());
    }

    @Test
    public void testDeleteOrder() throws SQLException {
        Order order = new Order(1, "2024-05-18", "Pending", 123, "123 Street", "456 Avenue", "Admin", "2024-05-18");
        DBManager.insertOrder(order);

        boolean result = dbManager.deleteOrder(1);
        assertTrue(result);

        List<Order> orderList = dbManager.fetchOrders();
        assertEquals(0, orderList.size());
    }

    @Test
    public void testSearchOrdersByCustomerID() throws SQLException {
        Order order1 = new Order(1, "2024-05-18", "Pending", 123, "123 Street", "456 Avenue", "Admin", "2024-05-18");
        Order order2 = new Order(2, "2024-05-19", "Shipped", 124, "789 Street", "101 Avenue", "Admin2", "2024-05-19");
        DBManager.insertOrder(order1);
        DBManager.insertOrder(order2);

        List<Order> result = dbManager.searchOrdersByCustomerID(124);
        assertEquals(1, result.size());
        Order foundOrder = result.get(0);
        assertEquals(2, foundOrder.getOrderID());
        assertEquals("Shipped", foundOrder.getStatus());
        assertEquals(124, foundOrder.getCustomerID());
        assertEquals("789 Street", foundOrder.getShippingAddress());
        assertEquals("101 Avenue", foundOrder.getBillingAddress());
        assertEquals("Admin2", foundOrder.getCreatedBy());
        assertEquals("2024-05-19", foundOrder.getCreatedDate());
    }
}
