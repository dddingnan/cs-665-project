package edu.bu.met.cs665.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String DATABASE_LOCATION = "src/main/resources/data/cs665-project.db";
    private static final String SQLITE_CONN_STRING = "jdbc:sqlite:" + DATABASE_LOCATION;
    private static volatile Connection conn = null; // volatile keyword ensures visibility of changes across threads.

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

    public static boolean databaseExists() {
        File dbFile = new File(DATABASE_LOCATION);
        return dbFile.exists();
    }
}
