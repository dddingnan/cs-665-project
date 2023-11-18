/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/06/2023
 * File Name: Main.java
 * Description: This is the main entry point for a system designed to demonstrate
 * the integration of a legacy USB-based customer data retrieval system with a 
 * new HTTPS-based system.
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
import edu.bu.met.cs665.location.Location;
import edu.bu.met.cs665.season.Season;
import edu.bu.met.cs665.season.SeasonUtils;
import edu.bu.met.cs665.weather.Weather;

public class Main {

  /**
   * Entry point method for the application. This method initializes the system
   * by:
   * 1. Loading customer data from a CSV file.
   * 2. Interacting with a legacy USB-based customer data system.
   * 3. Interacting with a new HTTPS-based customer data system.
   * 4. Using an adapter to make the legacy system compatible with the
   * interface of the new system.
   * 
   * @param args The command line arguments.
   * @throws InvalidDataException If there's an issue loading data.
   * @throws InterruptedException If there's an interrupted exception.
   */
  public static void main(String[] args) throws InvalidDataException, InterruptedException {

    try {
      Database.createNewDatabase();
      Database.createAirplaneTable();
      Database.createLocationsTable();
      Database.createWeatherTable();
    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
    } finally {
      Database.close();
    }

    System.out.println("Hello! Welcome to the Airplane Destination Evaluation System!");
    System.out.println("--------------------------------------------------------");
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
      // TODO Save into database.
      // Get the user's current data and determine the season
      Weather currentWeather = weatherList.stream()
          .filter(weather -> weather.getSeason() == currentSeason)
          .findFirst()
          .orElse(null);
      System.out.println("currentWeather --- " + currentWeather.getSeason());
    } catch (FileNotFoundException e) {
      System.out.println("File not found. Please check the file name and try again.");
      e.printStackTrace();
    } catch (IOException e) {
      System.out.println("Error occurred while reading the file.");
      e.printStackTrace();
    }
  }
}
