/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/21/2023
 * File Name: AirplaneRepository.java
 * Description: It manages the CRUD operations for 
 * the 'airplane' table in the database. 
 * This class implements the IRepository interface for 
 * the Airplane data model.
 */
package edu.bu.met.cs665.database.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.bu.met.cs665.airplane.AirbusA320;
import edu.bu.met.cs665.airplane.AirbusA380;
import edu.bu.met.cs665.airplane.Airplane;
import edu.bu.met.cs665.airplane.Boeing737;
import edu.bu.met.cs665.airplane.Boeing747;
import edu.bu.met.cs665.airplane.Boeing777;
import edu.bu.met.cs665.airplane.Bombardier;
import edu.bu.met.cs665.airplane.Embraer;
import edu.bu.met.cs665.database.Database;
import edu.bu.met.cs665.exception.InvalidDataException;

public class AirplaneRepository implements IRepository<Airplane> {
    /**
     * Creates the 'airplanes' table in the database if it doesn't exist.
     * Defines the structure of the table, including fields for ID, name,
     * range, fuel capacity, fuel consumption, and speed.
     *
     * @throws SQLException If an error occurs during SQL execution or database
     *                      connection.
     */
    @Override
    public void createTable() throws SQLException {
        try {
            Connection conn = Database.connect();
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

    /**
     * Inserts a list of Airplane objects into the 'airplanes' table.
     * It checks for existing entries to avoid duplicates.
     *
     * @param airplanes A list of Airplane objects to insert into the database.
     * @throws SQLException If an error occurs during SQL execution or database
     *                      connection.
     */
    @Override
    public void insertData(List<Airplane> airplanes) throws SQLException {
        String sql = "INSERT INTO airplanes (name, range, fuel_capacity, fuel_consumption, speed) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        Connection conn = Database.connect();
        try {
            pstmt = conn.prepareStatement(sql);
            for (Airplane airplane : airplanes) {
                if (!airplaneExists(airplane.getName(), conn)) {
                    pstmt.setString(1, airplane.getName());
                    pstmt.setDouble(2, airplane.getRange());
                    pstmt.setDouble(3, airplane.getFuelCapacity());
                    pstmt.setDouble(4, airplane.getFuelConsumption());
                    pstmt.setDouble(5, airplane.getSpeed());
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error inserting airplanes: " + e.getMessage());
        }
    }

    /**
     * Retrieves all airplane records from the database and converts them into a
     * list
     * of Airplane objects.
     *
     * @return A list of Airplane objects, each representing a record from the
     *         'airplanes' table.
     * @throws SQLException If an error occurs during SQL execution or database
     *                      connection.
     */
    @Override
    public List<Airplane> selectAll() throws SQLException {
        List<Airplane> airplanes = new ArrayList<>();
        String sql = "SELECT * FROM airplanes";

        try (Connection conn = Database.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String name = rs.getString("name");
                int range = rs.getInt("range");
                int fuelCapacity = rs.getInt("fuel_capacity");
                int fuelConsumption = rs.getInt("fuel_consumption");
                int speed = rs.getInt("speed");
                Airplane airplane = createAirplane(name, range, fuelCapacity, fuelConsumption, speed);
                if (airplane != null) {
                    airplanes.add(airplane);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error selecting airplanes: " + e.getMessage());
        } catch (InvalidDataException e) {
            System.out.println("InvalidDataException selecting locations: " + e.getMessage());
        }
        return airplanes;
    }

    /**
     * Creates an Airplane object based on the provided parameters.
     *
     * @param name            Name of the airplane.
     * @param range           Range of the airplane.
     * @param fuelCapacity    Fuel capacity of the airplane.
     * @param fuelConsumption Fuel consumption rate of the airplane.
     * @param speed           Speed of the airplane.
     * @return An instance of Airplane or null if the airplane type is unknown.
     * @throws InvalidDataException If airplane data is invalid.
     */
    private Airplane createAirplane(String name, double range, double fuelCapacity, double fuelConsumption,
            double speed) throws InvalidDataException {
        switch (name) {
            case "Boeing 737":
                return new Boeing737(name, range, fuelCapacity, fuelConsumption, speed);
            case "Boeing 747":
                return new Boeing747(name, range, fuelCapacity, fuelConsumption, speed);
            case "Boeing 777":
                return new Boeing777(name, range, fuelCapacity, fuelConsumption, speed);
            case "Airbus A380":
                return new AirbusA380(name, range, fuelCapacity, fuelConsumption, speed);
            case "Airbus A320":
                return new AirbusA320(name, range, fuelCapacity, fuelConsumption, speed);
            case "Bombardier CRJ200":
                return new Bombardier(name, range, fuelCapacity, fuelConsumption, speed);
            case "Embraer E1900":
                return new Embraer(name, range, fuelCapacity, fuelConsumption, speed);
            default:
                return null;
        }
    }

    /**
     * Checks if an airplane with the given name already exists in the database.
     *
     * @param name Name of the airplane to check.
     * @param conn Database connection object.
     * @return true if the airplane exists, false otherwise.
     * @throws SQLException If an error occurs during SQL execution or database
     *                      connection.
     */
    private boolean airplaneExists(String name, Connection conn) throws SQLException {
        String checkSql = "SELECT COUNT(*) FROM airplanes WHERE name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(checkSql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        }
        return false;
    }
}
