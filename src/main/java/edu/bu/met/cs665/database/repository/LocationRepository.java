/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/21/2023
 * File Name: LocationRepository.java
 * Description: It manages the CRUD operations for 
 * the 'locations' table in the database. 
 * This class implements the IRepository interface for 
 * the Location data model.
 */

package edu.bu.met.cs665.database.repository;

import edu.bu.met.cs665.database.Database;
import edu.bu.met.cs665.exception.InvalidDataException;
import edu.bu.met.cs665.location.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LocationRepository implements IRepository<Location> {
    /**
     * Creates the 'locations' table in the database if it doesn't already exist.
     * This method uses SQL commands to define the structure of the table including
     * fields for ID, name, latitude, and longitude.
     *
     * @throws SQLException If there's an error in executing the SQL command or
     *                      connecting to the database.
     */
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

    /**
     * Inserts a list of Location objects into the 'locations' table. Before
     * inserting,
     * it checks if a location with the same name already exists in the table to
     * avoid
     * duplicates.
     *
     * @param locations A list of Location objects to be inserted into the database.
     * @throws SQLException If there's an error in executing the SQL command or
     *                      connecting to the database.
     */
    @Override
    public void insertData(List<Location> locations) throws SQLException {
        String sql = "INSERT INTO locations (name, latitude, longitude) VALUES (?, ?, ?)";
        PreparedStatement pstmt = null;
        Connection conn = Database.connect();
        try {
            pstmt = conn.prepareStatement(sql);
            for (Location location : locations) {
                if (!locationExists(location.getName(), conn)) {
                    pstmt.setString(1, location.getName());
                    pstmt.setDouble(2, location.getLatitude());
                    pstmt.setDouble(3, location.getLongitude());
                    pstmt.executeUpdate(); // Execute update inside the loop for each location
                }
            }
        } catch (SQLException e) {
            System.out.println("Error inserting locations: " + e.getMessage());
        }
    }

    /**
     * Retrieves all location records from the database and converts them into a
     * list
     * of Location objects.
     *
     * @return A list of Location objects, each representing a record in the
     *         'locations' table.
     * @throws SQLException If there's an error in executing the SQL command or
     *                      connecting to the database.
     */
    @Override
    public List<Location> selectAll() throws SQLException {
        List<Location> locations = new ArrayList<>();
        String sql = "SELECT * FROM locations";
        try (Connection conn = Database.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String name = rs.getString("name");
                double latitude = rs.getDouble("latitude");
                double longitude = rs.getDouble("longitude");

                Location location = new Location(name, latitude, longitude);
                locations.add(location);
            }
        } catch (SQLException e) {
            System.out.println("SQLException selecting locations: " + e.getMessage());
        } catch (InvalidDataException e) {
            System.out.println("InvalidDataException selecting locations: " + e.getMessage());
        }
        return locations;
    }

    /**
     * Checks if a location with the given name already exists in the database.
     *
     * @param name The name of the location to check.
     * @param conn A Connection object to the database.
     * @return true if the location exists, false otherwise.
     * @throws SQLException If there's an error in executing the SQL command or
     *                      connecting to the database.
     */
    private boolean locationExists(String name, Connection conn) throws SQLException {
        String sql = "SELECT COUNT(*) FROM locations WHERE name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }
}
