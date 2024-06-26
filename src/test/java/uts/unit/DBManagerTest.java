// package uts.unit;

// import org.junit.jupiter.api.*;
// import uts.isd.dao.DBConnector;
// import uts.isd.dao.DBManager;
// import uts.isd.model.Staff;

// import java.sql.Connection;
// import java.sql.SQLException;
// import java.util.List;

// import static org.junit.jupiter.api.Assertions.*;

// @TestInstance(TestInstance.Lifecycle.PER_CLASS)
// public class DBManagerTest {

//     private Connection conn;
//     private DBManager dbManager;

//     @BeforeAll
//     public void setup() throws ClassNotFoundException, SQLException {
//         conn = DBConnector.getConnection();
//         dbManager = new DBManager(conn);
//     }

//     @AfterAll
//     public void tearDown() throws SQLException {
//         if (conn != null) {
//             conn.close();
//         }
//     }

//     @BeforeEach
//     public void beforeEachTest() throws SQLException {
//         conn.createStatement().execute("DELETE FROM staff");
//     }

//     @Test
//     public void testConnection() {
//         assertNotNull(conn);
//     }

//     @Test
//     public void testInsertStaff() throws SQLException {
//         Staff staff = new Staff("john.doe@example.com", "John Doe", "password1", "1234567890", "Sydney", "Australia",
//                 "Manager", "Operations", true);
//         boolean result = DBManager.insertStaff(staff);
//         assertTrue(result);

//         List<Staff> staffList = dbManager.fetchStaff();
//         assertEquals(1, staffList.size());
//         Staff insertedStaff = staffList.get(0);
//         assertEquals("john.doe@example.com", insertedStaff.getEmail());
//         assertEquals("John Doe", insertedStaff.getName());
//     }

//     @Test
//     public void testFetchStaff() throws SQLException {
//         Staff staff = new Staff("john.doe@example.com", "John Doe", "password1", "1234567890", "Sydney", "Australia",
//                 "Manager", "Operations", true);
//         DBManager.insertStaff(staff);

//         List<Staff> staffList = dbManager.fetchStaff();
//         assertEquals(1, staffList.size());
//         Staff fetchedStaff = staffList.get(0);
//         assertEquals("john.doe@example.com", fetchedStaff.getEmail());
//         assertEquals("John Doe", fetchedStaff.getName());
//     }

//     @Test
//     public void testUpdateStaff() throws SQLException {
//         Staff staff = new Staff("john.doe@example.com", "John Doe", "password1", "1234567890", "Sydney", "Australia",
//                 "Manager", "Operations", true);
//         DBManager.insertStaff(staff);

//         Staff updatedStaff = new Staff("john.doe@example.com", "John Doe Updated", "password1", "1234567890", "Sydney",
//                 "Australia",
//                 "Manager", "Operations", false);
//         boolean result = DBManager.updateStaff(updatedStaff);
//         assertTrue(result);

//         List<Staff> staffList = dbManager.fetchStaff();
//         assertEquals(1, staffList.size());
//         Staff fetchedStaff = staffList.get(0);
//         assertEquals("John Doe Updated", fetchedStaff.getName());
//         assertFalse(fetchedStaff.isActive());
//     }

//     @Test
//     public void testDeleteStaff() throws SQLException {
//         Staff staff = new Staff("john.doe@example.com", "John Doe", "password1", "1234567890", "Sydney", "Australia",
//                 "Manager", "Operations", true);
//         DBManager.insertStaff(staff);

//         boolean result = dbManager.deleteStaff("john.doe@example.com");
//         assertTrue(result);

//         List<Staff> staffList = dbManager.fetchStaff();
//         assertEquals(0, staffList.size());
//     }

//     @Test
//     public void testActivateStaff() throws SQLException {
//         Staff staff = new Staff("john.doe@example.com", "John Doe", "password1", "1234567890", "Sydney", "Australia",
//                 "Manager", "Operations", true);
//         DBManager.insertStaff(staff);

//         boolean result = DBManager.activateStaff("john.doe@example.com");
//         assertTrue(result);

//         Staff activatedStaff = dbManager.getStaff("john.doe@example.com");
//         assertTrue(activatedStaff.isActive());
//     }

//     @Test
//     public void testDeactivateStaff() throws SQLException {
//         Staff staff = new Staff("john.doe@example.com", "John Doe", "password1", "1234567890", "Sydney", "Australia",
//                 "Manager", "Operations", false);
//         DBManager.insertStaff(staff);

//         boolean result = DBManager.deactivateStaff("john.doe@example.com");
//         assertTrue(result);

//         Staff deactivatedStaff = dbManager.getStaff("john.doe@example.com");
//         assertFalse(deactivatedStaff.isActive());
//     }

//     @Test
//     public void testSearchStaff() throws SQLException {
//         Staff staff1 = new Staff("john.doe@example.com", "John Doe", "password1", "1234567890", "Sydney",
//                 "Australia", "Manager", "Operations", true);
//         Staff staff2 = new Staff("jane.doe@example.com", "Jane Doe", "password2", "0987654321", "Melbourne",
//                 "Australia", "Engineer", "Development", true);
//         DBManager.insertStaff(staff1);
//         DBManager.insertStaff(staff2);

//         List<Staff> result = dbManager.searchStaff("Jane", "Engineer", "Development");
//         assertEquals(1, result.size());
//         Staff foundStaff = result.get(0);
//         assertEquals("jane.doe@example.com", foundStaff.getEmail());
//         assertEquals("Jane Doe", foundStaff.getName());
//     }
// }
