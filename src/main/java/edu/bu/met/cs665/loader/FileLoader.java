/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/06/2023
 * File Name: FileLoader.java
 * Description: The FileLoader class provides utility functions to read customer data from CSV files.
 * This method is loading data from a provided file and returning a list of corresponding objects.
 */

package edu.bu.met.cs665.loader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
import edu.bu.met.cs665.exception.InvalidDataException;
import edu.bu.met.cs665.weather.Weather;
import edu.bu.met.cs665.season.Season;
import edu.bu.met.cs665.location.Location;

public class FileLoader {
    public String line = "";
    public String splitBy = ",";

    /*
     * Method: loadLocationsFromFile
     * Precondition: The file with the given fileName must exist and be readable.
     * The file should be in CSV format with each line representing one location,
     * and each line should be in the format: "<city_name>,<latitude>,<longitude>".
     * Postcondition: Returns a list of Location objects loaded from the file.
     * Each Location object corresponds to one line in the file.
     * Throws: FileNotFoundException if the file with the given fileName does not
     * exist.
     * IOException if an error occurs while reading the file.
     */
    public List<Location> loadLocationsFromFile(String fileName)
            throws FileNotFoundException, IOException, InvalidDataException {
        List<Location> locations = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        while ((line = br.readLine()) != null) {
            String[] locationData = line.split(splitBy); // Split the line by commas
            String name = locationData[0];
            double latitude = Double.parseDouble(locationData[1]);
            double longitude = Double.parseDouble(locationData[2]);
            try {
                locations.add(new Location(name, latitude, longitude));
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }
        }
        br.close();

        return locations;
    }

    /*
     * Method: loadAirplanesFromFile
     * Precondition: The file with the given fileName must exist and be readable.
     * The file should be in CSV format with each line representing one airplane,
     * and each line should be in the format:
     * "<airplane_name>,<range>,<fuelCapacity>,<fuelBurnRate>".
     * Postcondition: Returns a list of Airplane objects loaded from the file.
     * Each Airplane object corresponds to one line in the file.
     * Throws: FileNotFoundException if the file with the given fileName does not
     * exist.
     * IOException if an error occurs while reading the file.
     */
    public List<Airplane> loadAirplanesFromFile(String fileName) throws FileNotFoundException, IOException {
        List<Airplane> airplanes = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        while ((line = br.readLine()) != null) {
            String[] airplaneData = line.split(splitBy);
            String name = airplaneData[0];
            double range = Double.parseDouble(airplaneData[1]);
            double fuelCapacity = Double.parseDouble(airplaneData[2]);
            double fuelBurnRate = Double.parseDouble(airplaneData[3]);
            double speed = Double.parseDouble(airplaneData[4]);
            try {
                if (name.equals("Boeing 747")) {
                    airplanes.add(new Boeing747(name, range, fuelCapacity, fuelBurnRate, speed));
                } else if (name.equals("Airbus A380")) {
                    airplanes.add(new AirbusA380(name, range, fuelCapacity, fuelBurnRate, speed));
                } else if (name.equals("Airbus A320")) {
                    airplanes.add(new AirbusA320(name, range, fuelCapacity, fuelBurnRate, speed));
                } else if (name.equals("Boeing 737")) {
                    airplanes.add(new Boeing737(name, range, fuelCapacity, fuelBurnRate, speed));
                } else if (name.equals("Bombardier CRJ200")) {
                    airplanes.add(new Bombardier(name, range, fuelCapacity, fuelBurnRate, speed));
                } else if (name.equals("Embraer E190")) {
                    airplanes.add(new Embraer(name, range, fuelCapacity, fuelBurnRate, speed));
                } else if (name.equals("Boeing 777")) {
                    airplanes.add(new Boeing777(name, range, fuelCapacity, fuelBurnRate, speed));
                } else {
                    System.out.println("Unknown airplane type: " + name);
                }
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }
        }
        br.close();

        return airplanes;
    }

    /*
     * Method: loadWeatherFromFile
     * Precondition: The file with the given fileName must exist and be readable.
     * The file should be in CSV format with each line representing one weather
     * data,
     * and each line should be in the format:
     * {
     * season: string;
     * winSpeed: number;
     * temperature: number;
     * humidity: number;
     * }[]
     * Postcondition: Returns a list of Weather objects loaded from the file.
     * Each Weather object corresponds to one line in the file.
     * Throws: IOException if an error occurs while reading the file.
     */
    public List<Weather> loadWeatherFromFile(String fileName) throws IOException {
        List<Weather> weatherList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        while ((line = br.readLine()) != null) {
            String[] weatherData = line.split(splitBy);
            Season season = Season.valueOf(weatherData[0].toUpperCase());
            double windSpeed = Double.parseDouble(weatherData[1]);
            double temperature = Double.parseDouble(weatherData[2]);
            double humidity = Double.parseDouble(weatherData[3]);
            weatherList.add(new Weather(season, windSpeed, temperature, humidity));
        }
        br.close();
        return weatherList;
    }

}