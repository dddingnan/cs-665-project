/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/21/2023
 * File Name: Main.java
 * Description: This class serves as the entry point for the Airplane Destination Evaluation System. 
 * It handles the initial setup and launch of the application, including loading data, initializing the database, 
 * and starting the user interface.
 */
package edu.bu.met.cs665;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import edu.bu.met.cs665.exception.InvalidDataException;
import edu.bu.met.cs665.loader.FileLoader;
import edu.bu.met.cs665.airplane.Airplane;
import edu.bu.met.cs665.database.Database;
import edu.bu.met.cs665.database.initializer.DatabaseInitializer;
import edu.bu.met.cs665.database.repository.AirplaneRepository;
import edu.bu.met.cs665.database.repository.LocationRepository;
import edu.bu.met.cs665.database.repository.WeatherRepository;
import edu.bu.met.cs665.location.Location;
import edu.bu.met.cs665.season.Season;
import edu.bu.met.cs665.season.SeasonUtils;
import edu.bu.met.cs665.user.UserInterface;
import edu.bu.met.cs665.weather.Weather;

public class Main {

  /**
   * The main method that serves as the entry point for the application.
   * 
   * @param args Command line arguments (not used in this application).
   */
  public static void main(String[] args) {
    try {
      startApplication();
    } catch (Exception e) {
      handleExceptions(e);
    } finally {
      Database.close(); // Ensure the database connection is always closed
    }
  }

  /**
   * Starts the application by loading data, initializing the database,
   * and launching the user interface.
   * 
   * @throws Exception Throws if any error occurs during the application startup
   *                   process.
   */
  private static void startApplication() throws Exception {
    System.out.println("--------------------------------------------------------");
    System.out.println("Hello! Welcome to the Airplane Destination Evaluation System!");
    System.out.println("--------------------------------------------------------");

    loadAndInitializeData();

    Weather currentWeather = getCurrentSeasonWeather();
    UserInterface ui = new UserInterface(currentWeather, new LocationRepository(), new AirplaneRepository());
    ui.start();
  }

  /**
   * Loads necessary data and initializes the database.
   * 
   * @throws SQLException         If a database error occurs.
   * @throws IOException          If an error occurs during data loading.
   * @throws InvalidDataException If data validation fails.
   */
  private static void loadAndInitializeData() throws SQLException, IOException, InvalidDataException {
    List<Location> locations = loadLocations();
    List<Airplane> airplanes = loadAirplanes();
    List<Weather> weatherList = loadWeather();

    initializeDatabase();

    insertDataToDatabase(locations, airplanes, weatherList);
  }

  /**
   * Initializes the database with necessary tables.
   * 
   * @throws SQLException If a database error occurs.
   */
  private static void initializeDatabase() throws SQLException {
    DatabaseInitializer.initializeDatabase(new LocationRepository(), new AirplaneRepository(), new WeatherRepository());
    System.out.println("--------------------------------------------------------");
  }

  /**
   * Inserts data into the database.
   * 
   * @param locations   List of locations to insert.
   * @param airplanes   List of airplanes to insert.
   * @param weatherList List of weather data to insert.
   * @throws SQLException If a database error occurs.
   */
  private static void insertDataToDatabase(List<Location> locations, List<Airplane> airplanes,
      List<Weather> weatherList) throws SQLException {
    new LocationRepository().insertData(locations);
    new AirplaneRepository().insertData(airplanes);
    new WeatherRepository().insertData(weatherList);
  }

  private static List<Location> loadLocations() throws FileNotFoundException, IOException, InvalidDataException {
    return new FileLoader().loadLocationsFromFile("src/data/locations.csv");
  }

  private static List<Airplane> loadAirplanes() throws FileNotFoundException, IOException {
    return new FileLoader().loadAirplanesFromFile("src/data/airplanes.csv");
  }

  private static List<Weather> loadWeather() throws IOException {
    return new FileLoader().loadWeatherFromFile("src/data/weather.csv");
  }

  /**
   * Retrieves the weather data for the current season.
   * 
   * @return Weather data for the current season, or null if not available.
   * @throws SQLException If a database error occurs.
   * @throws IOException  If an error occurs during data loading.
   */
  private static Weather getCurrentSeasonWeather() throws SQLException, IOException {
    Season currentSeason = SeasonUtils.getCurrentSeason();
    System.out.println("User current season: " + currentSeason);
    return loadWeather().stream()
        .filter(weather -> weather.getSeason() == currentSeason)
        .findFirst()
        .orElse(null);
  }

  /**
   * Handles various exceptions that might occur during application startup.
   * 
   * @param e The exception to handle.
   */
  private static void handleExceptions(Exception e) {
    if (e instanceof FileNotFoundException) {
      System.out.println("File not found. Please check the file name and try again.");
    } else if (e instanceof IOException) {
      System.out.println("Error occurred while reading the file.");
    } else if (e instanceof SQLException) {
      System.out.println("Error inserting data into the database: " + e.getMessage());
    } else {
      System.out.println("An unexpected error occurred: " + e.getMessage());
    }
    e.printStackTrace();
  }
}
