package uts.unit;

import org.junit.jupiter.api.*;
import uts.isd.dao.DBConnector;
import uts.isd.dao.DBManager;
import uts.isd.model.Product;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DBManagerTestDean {

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
        conn.createStatement().execute("DELETE FROM product");
    }

    @Test
    public void testConnection() {
        assertNotNull(conn);
    }

    private Date date = Date.valueOf("2020-10-12");

    @Test
    public void testInsertProduct() throws SQLException {
        Product product = new Product("PR1", "Temperature Sensor", "Sense the temperature of your room.", 9.98f, 
        20, "Sensors", "MicroTech", date);
        boolean result = DBManager.insertProduct(product);
        assertTrue(result);

        ArrayList<Product> productList = dbManager.displayProducts();
        assertEquals(1, productList.size());
        Product insertedProduct = productList.get(0);
        assertEquals("PR1", insertedProduct.getProductID());
        assertEquals("Temperature Sensor", insertedProduct.getName());
    }

    @Test
    public void testDisplayProducts() throws SQLException {
        Product product = new Product("PR1", "Temperature Sensor", "Sense the temperature of your room.", 9.98f, 
        20, "Sensors", "MicroTech", date);
        DBManager.insertProduct(product);

        ArrayList<Product> productList = dbManager.displayProducts();
        assertEquals(1, productList.size());
        Product displayedProduct = productList.get(0);
        assertEquals("PR1", displayedProduct.getProductID());
        assertEquals("Temperature Sensor", displayedProduct.getName());
    }

    @Test
    public void testUpdateProduct() throws SQLException {
        Product product = new Product("PR1", "Temperature Sensor", "Sense the temperature of your room.", 9.98f, 
        20, "Sensors", "MicroTech", date);
        DBManager.insertProduct(product);

        Product updatedProduct = new Product("PR1", "Temperature Sensor Update", "Sense the temperature of your room.", 9.98f, 
        20, "Sensors", "MicroTech", date);
        boolean result = DBManager.updateProduct(updatedProduct);
        assertTrue(result);

        ArrayList<Product> productList = dbManager.displayProducts();
        assertEquals(1, productList.size());
        Product displayedProduct = productList.get(0);
        assertEquals("Temperature Sensor Update", displayedProduct.getName());
    }

    @Test
    public void testDeleteProduct() throws SQLException {
        Product product = new Product("PR1", "Temperature Sensor", "Sense the temperature of your room.", 9.98f, 
        20, "Sensors", "MicroTech", date);
        DBManager.insertProduct(product);

        boolean result = dbManager.deleteProduct("PR1");
        assertTrue(result);

        ArrayList<Product> productList = dbManager.displayProducts();
        assertEquals(0, productList.size());
    }

    @Test
    public void testSearchProduct() throws SQLException {
        Date date2 = Date.valueOf("2019-08-11");
        Date date3 = Date.valueOf("2023-02-22");
        Product product1 = new Product("PR1", "Temperature Sensor", "Sense the temperature of your room.", 9.98f, 
        20, "Sensors", "MicroTech", date);
        Product product2 = new Product("PR2", "Small Camera", "Take pictures on the go.", 14.49f, 
        50, "Cameras", "CamMerchant", date2);
        Product product3 = new Product("PR3", "Product Three", "This is product 3.", 119.98f, 
        5, "Products", "IoTBay", date3);
        DBManager.insertProduct(product1);
        DBManager.insertProduct(product2);
        DBManager.insertProduct(product3);

        List<Product> result = dbManager.searchProduct("small", "cam");
        assertEquals(1, result.size());
        Product resultProduct = result.get(0);
        assertEquals("PR2", resultProduct.getProductID());
        assertEquals("Small Camera", resultProduct.getName());
    }
}
