package edu.bu.met.cs665.database.repository;

import edu.bu.met.cs665.database.Database;
import edu.bu.met.cs665.location.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LocationRepository implements IRepository<Location> {

    @Override
    public void createTable() throws SQLException {
        try {
            Connection conn = Database.connect();
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

    @Override
    public void insertData(List<Location> locations) throws SQLException {
        String sql = "INSERT INTO locations (name, latitude, longitude) VALUES (?, ?, ?)";
        PreparedStatement pstmt = null;
        try {
            Connection conn = Database.connect();
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

    @Override
    public List<Location> selectAll() throws SQLException {
        List<Location> locations = new ArrayList<>();
        return locations;
    }
}
