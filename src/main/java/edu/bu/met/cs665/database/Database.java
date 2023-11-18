package edu.bu.met.cs665.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String SQLITE_CONN_STRING = "jdbc:sqlite:C:/sqlite/database";
    private static final String DATABASE_NAME = "cs665-project.db";
    private static Connection conn = null;

    public static Connection connect() {
        if (conn == null) {
            try {
                String url = SQLITE_CONN_STRING + "/" + DATABASE_NAME;
                conn = DriverManager.getConnection(url);
                System.out.println("Connection to SQLite has been established.");
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return conn;
    }

    public static void close() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Connection to SQLite has been closed.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void createNewDatabase() throws SQLException {
        try {
            connect(); // Ensure connection is established
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void createAirplaneTable() throws SQLException {
        try {
            connect(); // Ensure connection is established
            // SQL statement for creating a new table
            String sql = "CREATE TABLE IF NOT EXISTS airplanes (\n"
                    + " id integer PRIMARY KEY,\n"
                    + " name text NOT NULL,\n"
                    + " range integer NOT NULL,\n"
                    + " fuel_capacity integer NOT NULL,\n"
                    + " fuel_consumption integer NOT NULL,\n"
                    + " speed integer NOT NULL\n"
                    + ");";
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
                System.out.println("Airplane table has been created.");
            }
        } catch (SQLException e) {
            System.out.println("Airplane table Error: " + e.getMessage());
        }
    }

    public static void createLocationsTable() throws SQLException {
        try {
            connect(); // Ensure connection is established
            String sql = "CREATE TABLE IF NOT EXISTS locations (\n"
                    + " id integer PRIMARY KEY,\n"
                    + " name text NOT NULL,\n"
                    + " latitude real NOT NULL,\n"
                    + " longitude real NOT NULL\n"
                    + ");";

            try (Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
                System.out.println("Locations table has been created.");
            }
        } catch (SQLException e) {
            System.out.println("Locations table Error: " + e.getMessage());
        }
    }

    public static void createWeatherTable() throws SQLException {
        try {
            connect(); // Ensure connection is established
            String sql = "CREATE TABLE IF NOT EXISTS weather (\n"
                    + " season text PRIMARY KEY,\n"
                    + " wind_speed real NOT NULL,\n"
                    + " temperature real NOT NULL,\n"
                    + " humidity real NOT NULL\n"
                    + ");";

            try (Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
                System.out.println("Weather table has been created.");
            }
        } catch (SQLException e) {
            System.out.println("Weather table Error: " + e.getMessage());
        }
    }
}
