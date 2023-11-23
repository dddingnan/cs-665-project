/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/21/2023
 * File Name: Main.java
 * Description: This is the main entry point for a system
 */
package edu.bu.met.cs665;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
   * Entry point method for the application. This method initializes the system
   * by:
   * 1.
   * 
   * @param args The command line arguments.
   * @throws InvalidDataException If there's an issue loading data.
   * @throws InterruptedException If there's an interrupted exception.
   */
  public static void main(String[] args) throws InvalidDataException, InterruptedException, Exception {
    System.out.println("Hello! Welcome to the Airplane Destination Evaluation System!");
    System.out.println("--------------------------------------------------------");
    LocationRepository locationRepo = new LocationRepository();
    AirplaneRepository airplaneRepo = new AirplaneRepository();
    WeatherRepository weatherRepo = new WeatherRepository();
    List<Location> locations = new ArrayList<>();
    List<Airplane> airplanes = new ArrayList<>();
    List<Weather> weatherList = new ArrayList<>();
    FileLoader loader = new FileLoader();
    try {
      Season currentSeason = SeasonUtils.getCurrentSeason();
      System.out.println("User current season: " + currentSeason);
      System.out.println("--------------------------------------------------------");
      locations = loader.loadLocationsFromFile("src/data/locations.csv");
      airplanes = loader.loadAirplanesFromFile("src/data/airplanes.csv");
      weatherList = loader.loadWeatherFromFile("src/data/weather.csv");
      // Create database and tables
      DatabaseInitializer.initializeDatabase(locationRepo, airplaneRepo, weatherRepo);
      System.out.println("--------------------------------------------------------");
      locationRepo.insertData(locations);
      airplaneRepo.insertData(airplanes);
      weatherRepo.insertData(weatherList);
      // Get the user's current data and determine the season
      Weather currentWeather = weatherList.stream()
          .filter(weather -> weather.getSeason() == currentSeason)
          .findFirst()
          .orElse(null);
      System.out.println("--------------------------------------------------------");
      System.out.println("currentWeather --- " + currentWeather.getSeason());
      UserInterface ui = new UserInterface(currentWeather, locationRepo, airplaneRepo);
      ui.start();
    } catch (FileNotFoundException e) {
      System.out.println("File not found. Please check the file name and try again.");
      e.printStackTrace();
    } catch (IOException e) {
      System.out.println("Error occurred while reading the file.");
      e.printStackTrace();
    } catch (SQLException e) {
      System.out.println("Error inserting data into the database: " + e.getMessage());
      e.printStackTrace();
    } finally {
      Database.close(); // Close the connection here
    }
  }
}
