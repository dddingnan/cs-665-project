/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/21/2023
 * File Name: DatabaseInitializer.java
 * Description: Setting up the initial database structure.
 * It ensures that all necessary tablesare created 
 * in the database before the application starts using it. 
 */
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
    /**
     * Initializes the database by creating tables for locations, airplanes, and
     * weather.
     * It checks if the database already exists before attempting to create a new
     * one.
     * If the database does not exist, it creates the database and then proceeds to
     * create the tables.
     * 
     * @param locationRepo A repository instance for handling operations related to
     *                     the 'locations' table.
     * @param airplaneRepo A repository instance for handling operations related to
     *                     the 'airplanes' table.
     * @param weatherRepo  A repository instance for handling operations related to
     *                     the 'weather' table.
     * @throws SQLException If an error occurs during SQL execution or database
     *                      connection.
     */
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
