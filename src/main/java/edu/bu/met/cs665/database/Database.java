package edu.bu.met.cs665.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String SQLITE_CONN_STRING = "jdbc:sqlite:C:/sqlite/database";
    private static final String DATABASE_NAME = "cs665-project.db";

    public static void createNewDatabase() {
        String url = SQLITE_CONN_STRING + "/" + DATABASE_NAME;
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static Connection connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = SQLITE_CONN_STRING + "/" + DATABASE_NAME;
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return conn;
    }

    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Connection to SQLite has been closed.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void createAirplaneTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS airplanes (\n"
                + " id integer PRIMARY KEY,\n"
                + " name text NOT NULL,\n"
                + " range integer NOT NULL,\n"
                + " fuel_capacity integer NOT NULL,\n"
                + " fuel_consumption integer NOT NULL,\n"
                + " speed integer NOT NULL\n"
                + ");";
        String url = SQLITE_CONN_STRING + "/" + DATABASE_NAME;
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void createLocationsTable() {
        String sql = "CREATE TABLE IF NOT EXISTS locations (\n"
                + " id integer PRIMARY KEY,\n"
                + " name text NOT NULL,\n"
                + " latitude real NOT NULL,\n"
                + " longitude real NOT NULL\n"
                + ");";

        String url = SQLITE_CONN_STRING + "/" + DATABASE_NAME;
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
