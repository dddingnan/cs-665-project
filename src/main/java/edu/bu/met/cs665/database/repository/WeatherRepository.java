package edu.bu.met.cs665.database.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.bu.met.cs665.database.Database;
import edu.bu.met.cs665.weather.Weather;

public class WeatherRepository implements IRepository<Weather> {

    @Override
    public void createTable() throws SQLException {
        try {
            Connection conn = Database.connect();
            String sql = "CREATE TABLE IF NOT EXISTS weather (\n"
                    + " id integer PRIMARY KEY,\n"
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

    @Override
    public void insertData(List<Weather> weatherList) throws SQLException {
        String sql = "INSERT INTO weather (season, wind_speed, temperature, humidity) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        try {
            Connection conn = Database.connect();
            pstmt = conn.prepareStatement(sql);
            for (Weather weather : weatherList) {
                pstmt.setString(1, weather.getSeason().toString());
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
        }
    }

    @Override
    public List<Weather> selectAll() throws SQLException {
        List<Weather> weathers = new ArrayList<>();
        return weathers;
    }
}
