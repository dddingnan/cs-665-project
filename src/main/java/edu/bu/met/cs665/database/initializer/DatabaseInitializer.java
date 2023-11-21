package edu.bu.met.cs665.database.initializer;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import edu.bu.met.cs665.database.Database;
import edu.bu.met.cs665.database.repository.AirplaneRepository;
import edu.bu.met.cs665.database.repository.LocationRepository;
import edu.bu.met.cs665.database.repository.WeatherRepository;

public class DatabaseInitializer {

    public static void initializeDatabase() throws SQLException {
        LocationRepository locationRepo = new LocationRepository();
        AirplaneRepository airplaneRepo = new AirplaneRepository();
        WeatherRepository weatherRepo = new WeatherRepository();
        try {
            Connection conn = Database.connect();
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
                locationRepo.createTable();
                airplaneRepo.createTable();
                weatherRepo.createTable();
            }
        } catch (SQLException e) {
            System.out.println("Error initializing database: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
