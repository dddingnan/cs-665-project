package edu.bu.met.cs665.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import edu.bu.met.cs665.airplane.Airplane;
import edu.bu.met.cs665.location.Location;
import edu.bu.met.cs665.weather.Weather;

public class Database {
    private static final String SQLITE_CONN_STRING = "jdbc:sqlite:src/main/resources/data";
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

    public static void insertLocations(List<Location> locations) throws SQLException {
        String sql = "INSERT INTO locations (name, latitude, longitude) VALUES (?, ?, ?)";
        PreparedStatement pstmt = null;
        try {
            conn = connect(); // Ensure connection is established
            pstmt = conn.prepareStatement(sql);
            for (Location location : locations) {
                pstmt.setString(1, location.getName());
                pstmt.setDouble(2, location.getLatitude());
                pstmt.setDouble(3, location.getLongitude());
                pstmt.executeUpdate();
            }
            System.out.println("Locations have been inserted into the database.");
        } catch (SQLException e) {
            System.out.println("Error inserting locations: " + e.getMessage());
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    public static void insertAirplanes(List<Airplane> airplanes) throws SQLException {
        String sql = "INSERT INTO airplanes (name, range, fuel_capacity, fuel_consumption, speed) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        try {
            conn = connect(); // Ensure connection is established
            pstmt = conn.prepareStatement(sql);
            for (Airplane airplane : airplanes) {
                pstmt.setString(1, airplane.getName());
                pstmt.setDouble(2, airplane.getRange());
                pstmt.setDouble(3, airplane.getFuelCapacity());
                pstmt.setDouble(4, airplane.getFuelConsumption());
                pstmt.setDouble(5, airplane.getSpeed());
                pstmt.executeUpdate();
            }
            System.out.println("Airplanes have been inserted into the database.");
        } catch (SQLException e) {
            System.out.println("Error inserting airplanes: " + e.getMessage());
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    public static void insertWeatherData(List<Weather> weatherList) throws SQLException {
        String sql = "INSERT INTO weather (season, wind_speed, temperature, humidity) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        try {
            conn = connect(); // Ensure connection is established
            pstmt = conn.prepareStatement(sql);
            for (Weather weather : weatherList) {
                pstmt.setString(1, weather.getSeason().toString()); // Assuming getSeason() returns a String or
                                                                    // toString() method is overridden
                pstmt.setDouble(2, weather.getWindSpeed());
                pstmt.setDouble(3, weather.getTemperature());
                pstmt.setDouble(4, weather.getHumidity());
                pstmt.executeUpdate();
            }
            System.out.println("Weather data have been inserted into the database.");
        } catch (SQLException e) {
            System.out.println("Error inserting weather data: " + e.getMessage());
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            // Do not close the conn here
        }
    }

}
