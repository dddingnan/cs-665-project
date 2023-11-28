package edu.bu.met.cs665.database;

import org.junit.After;
import org.junit.Test;
import java.sql.Connection;
import static org.junit.Assert.*;

public class DatabaseTest {

    @After
    public void tearDown() throws Exception {
        Database.close();
    }

    @Test
    public void testDatabaseExists() {
        // This test checks if the database file exists
        assertTrue(Database.databaseExists());
    }

    @Test
    public void testConnectionEstablishment() throws Exception {
        // This test checks if the connection is successfully established
        Connection conn = Database.connect();
        assertNotNull(conn);
        assertTrue(conn.isValid(0));
    }

    @Test
    public void testConnectionClosure() throws Exception {
        // This test checks if the connection is successfully closed
        Connection conn = Database.connect();
        assertNotNull(conn);
        assertTrue(conn.isValid(0));

        Database.close();
        assertFalse(conn.isValid(0));
    }
}
