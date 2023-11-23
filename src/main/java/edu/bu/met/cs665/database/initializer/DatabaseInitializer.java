package edu.bu.met.cs665.database.initializer;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import edu.bu.met.cs665.airplane.Airplane;
import edu.bu.met.cs665.database.Database;
import edu.bu.met.cs665.location.Location;
import edu.bu.met.cs665.database.repository.IRepository;
import edu.bu.met.cs665.weather.Weather;

public class DatabaseInitializer {

    public static void initializeDatabase(IRepository<Location> locationRepo,
            IRepository<Airplane> airplaneRepo,
            IRepository<Weather> weatherRepo) throws SQLException {

        if (!Database.databaseExists()) {
            System.out.println("Database does not exist. Creating a new database.");
        }
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
        }
    }
}
