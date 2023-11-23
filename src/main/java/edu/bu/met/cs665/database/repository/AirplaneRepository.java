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

    @Override
    public void insertData(List<Airplane> airplanes) throws SQLException {
        String sql = "INSERT INTO airplanes (name, range, fuel_capacity, fuel_consumption, speed) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        try {
            Connection conn = Database.connect();
            pstmt = conn.prepareStatement(sql);
            for (Airplane airplane : airplanes) {
                if (!airplaneExists(airplane.getName())) {
                    pstmt.setString(1, airplane.getName());
                    pstmt.setDouble(2, airplane.getRange());
                    pstmt.setDouble(3, airplane.getFuelCapacity());
                    pstmt.setDouble(4, airplane.getFuelConsumption());
                    pstmt.setDouble(5, airplane.getSpeed());
                    pstmt.executeUpdate();
                    System.out.println("Inserted airplane: " + airplane.getName());
                } else {
                    System.out.println("Airplane already exists, skipping insertion: " + airplane.getName());
                }
            }
        } catch (SQLException e) {
            System.out.println("Error inserting airplanes: " + e.getMessage());
        }
    }

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

    private boolean airplaneExists(String name) throws SQLException {
        String checkSql = "SELECT COUNT(*) FROM airplanes WHERE name = ?";
        try (Connection conn = Database.connect();
                PreparedStatement pstmt = conn.prepareStatement(checkSql)) {
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
