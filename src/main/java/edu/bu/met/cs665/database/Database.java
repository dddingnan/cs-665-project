/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/21/2023
 * File Name: Database.java
 * Description: The Database class provides functionality to 
 * manage the SQLite database connection.
 * It employs the Lazy Loading Pattern to ensure that only one 
 * instance of the database connection exists throughout the application.
 */

package edu.bu.met.cs665.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String DATABASE_LOCATION = "src/main/resources/data/cs665-project.db";
    private static final String SQLITE_CONN_STRING = "jdbc:sqlite:" + DATABASE_LOCATION;
    private static volatile Connection conn = null; // volatile keyword ensures visibility of changes across threads.

    /**
     * Establishes a connection to the SQLite database, using synchronized and
     * double-checked locking to ensure thread safety and performance optimization.
     * 
     * @return A Connection object representing the database connection.
     * @throws SQLException If a database access error occurs or the url is null.
     */
    public static synchronized Connection connect() throws SQLException {
        if (conn == null || conn.isClosed() || !conn.isValid(0)) {
            if (conn == null || conn.isClosed()) { // Double-checked locking to avoid unnecessary synchronization.
                synchronized (Database.class) { // Synchronize on the class object's monitor.
                    if (conn == null || conn.isClosed()) {
                        conn = DriverManager.getConnection(SQLITE_CONN_STRING);
                        System.out.println("Connection to SQLite has been established.");
                    }
                }
            }
        }
        return conn;
    }

    /**
     * Closes the database connection if it is currently open. This method is
     * synchronized
     * to ensure that the connection is safely closed in a multi-threaded
     * environment.
     */
    public static synchronized void close() {
        synchronized (Database.class) { // Synchronize on the class object's monitor.
            try {
                if (conn != null) {
                    conn.close();
                    conn = null; // Set conn to null so it can be re-initialized safely.
                    System.out.println("Connection to SQLite has been closed.");
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    /**
     * Checks if the database file exists at the specified location.
     * 
     * @return A boolean indicating whether the database file exists.
     */
    public static boolean databaseExists() {
        File dbFile = new File(DATABASE_LOCATION);
        return dbFile.exists();
    }
}
