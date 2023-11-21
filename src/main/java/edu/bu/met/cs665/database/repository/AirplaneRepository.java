package edu.bu.met.cs665.database.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.bu.met.cs665.airplane.Airplane;
import edu.bu.met.cs665.database.Database;

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

    @Override
    public List<Airplane> selectAll() throws SQLException {
        List<Airplane> airplanes = new ArrayList<>();
        return airplanes;
    }
}
