/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/21/2023
 * File Name: WeatherRepository.java
 * Description: It manages the CRUD operations for 
 * the 'weather' table in the database. 
 * This class implements the IRepository interface for 
 * the Weather data model.
 */
package edu.bu.met.cs665.database.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.bu.met.cs665.database.Database;
import edu.bu.met.cs665.weather.Weather;

public class WeatherRepository implements IRepository<Weather> {
    /**
     * Creates the 'weather' table in the database if it doesn't exist.
     * Defines the structure of the table, including fields for ID, season,
     * wind speed, temperature, and humidity.
     *
     * @throws SQLException If an error occurs during SQL execution or database
     *                      connection.
     */
    @Override
    public void createTable() throws SQLException {
        try {
            Connection conn = Database.connect();
            String sql = "CREATE TABLE IF NOT EXISTS weather (\n"
                    + " id integer PRIMARY KEY,\n"
                    + " season text UNIQUE NOT NULL,\n"
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

    /**
     * Inserts a list of Weather objects into the 'weather' table.
     * It checks for existing entries to avoid duplicates.
     *
     * @param weatherList A list of Weather objects to insert into the database.
     * @throws SQLException If an error occurs during SQL execution or database
     *                      connection.
     */
    @Override
    public void insertData(List<Weather> weatherList) throws SQLException {
        String sql = "INSERT INTO weather (season, wind_speed, temperature, humidity) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        Connection conn = Database.connect();
        try {
            pstmt = conn.prepareStatement(sql);
            for (Weather weather : weatherList) {
                if (!weatherExists(weather.getSeason().toString(), conn)) {
                    pstmt.setString(1, weather.getSeason().toString());
                    pstmt.setDouble(2, weather.getWindSpeed());
                    pstmt.setDouble(3, weather.getTemperature());
                    pstmt.setDouble(4, weather.getHumidity());
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error inserting weather: " + e.getMessage());
        }
    }

    /**
     * Retrieves all weather records from the database and converts them into a list
     * of Weather objects.
     *
     * @return A list of Weather objects, each representing a record from the
     *         'weather' table.
     * @throws SQLException If an error occurs during SQL execution or database
     *                      connection.
     */
    @Override
    public List<Weather> selectAll() throws SQLException {
        List<Weather> weathers = new ArrayList<>();
        return weathers;
    }

    /**
     * Checks if weather data for a given season already exists in the database.
     *
     * @param season Season name to check for existence in the database.
     * @param conn   Database connection object.
     * @return true if the weather data for the season exists, false otherwise.
     * @throws SQLException If an error occurs during SQL execution or database
     *                      connection.
     */
    private boolean weatherExists(String season, Connection conn) throws SQLException {
        String sql = "SELECT COUNT(*) FROM weather WHERE season = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, season);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }
}
