/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/21/2023
 * File Name: UserInterface.java
 * Description: This class represents the user interface for the Airplane Destination Evaluation System.
 * It handles user interactions, allowing them to select their current location, airplane type,
 * and view reachable destinations based on the airplane's capabilities and current weather conditions.
 */
package edu.bu.met.cs665.user;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import edu.bu.met.cs665.location.Location;
import edu.bu.met.cs665.weather.Weather;
import edu.bu.met.cs665.airplane.Airplane;
import edu.bu.met.cs665.calculator.TravelCalculator;
import edu.bu.met.cs665.database.repository.AirplaneRepository;
import edu.bu.met.cs665.database.repository.LocationRepository;
import edu.bu.met.cs665.exception.InvalidDataException;
import edu.bu.met.cs665.flight.FlightData;

public class UserInterface {
    private List<Location> locations;
    private List<Airplane> airplanes;
    private TravelCalculator travelCalculator;
    private Scanner scanner;

    /**
     * Constructs a UserInterface with specified current weather, location
     * repository, and airplane repository.
     * 
     * @param currentWeather The current weather conditions.
     * @param locationRepo   The repository for locations.
     * @param airplaneRepo   The repository for airplanes.
     * @throws SQLException If a database access error occurs.
     */
    public UserInterface(Weather currentWeather, LocationRepository locationRepo, AirplaneRepository airplaneRepo)
            throws SQLException {
        this.locations = locationRepo.selectAll();
        this.airplanes = airplaneRepo.selectAll();
        this.travelCalculator = new TravelCalculator(locations, currentWeather);
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the user interface interaction process.
     * 
     * @throws InvalidDataException If invalid data is provided by the user.
     * @throws Exception            If an unexpected error occurs.
     */
    public void start() throws InvalidDataException, Exception {
        while (true) {
            Location currentLocation = selectCurrentLocation();
            Airplane selectedAirplane = selectAirplane();
            displayReachableLocations(currentLocation, selectedAirplane);

            if (!promptForContinue()) {
                System.out.println("--------------------------------------------------------");
                System.out.println("See you next time~!");
                break;
            }
        }
        scanner.close();
    }

    /**
     * Prompts the user to select their current location from a list of available
     * locations.
     * 
     * @return The selected Location object.
     * @throws InvalidDataException If the user input is invalid.
     */
    private Location selectCurrentLocation() throws InvalidDataException {
        System.out.println("Please select your current location:");
        displayOptions(locations, "getName");

        int locationIndex = getValidUserInput(locations.size());
        return locations.get(locationIndex);
    }

    /**
     * Prompts the user to select an airplane from a list of available airplanes.
     * 
     * @return The selected Airplane object.
     * @throws InvalidDataException If the user input is invalid.
     */
    private Airplane selectAirplane() throws InvalidDataException {
        System.out.println("Please select your airplane type:");
        displayOptions(airplanes, "getName");

        int airplaneIndex = getValidUserInput(airplanes.size());
        return airplanes.get(airplaneIndex);
    }

    /**
     * Displays the reachable locations based on the current location and selected
     * airplane.
     * 
     * @param currentLocation The user's current location.
     * @param airplane        The selected airplane.
     * @throws Exception If an error occurs in calculating reachable locations.
     */
    private void displayReachableLocations(Location currentLocation, Airplane airplane) throws Exception {
        Map<Location, FlightData> reachableLocations = travelCalculator.calculateReachableLocations(airplane,
                currentLocation);
        System.out.println("Potential city destinations on a single tank of fuel:");
        int index = 1;
        for (Map.Entry<Location, FlightData> entry : reachableLocations.entrySet()) {
            printFlightData(index++, entry.getKey(), entry.getValue());
        }
    }

    /**
     * Prints flight data for a specific destination.
     * 
     * @param index      The index of the destination in the list.
     * @param location   The destination location.
     * @param flightData The flight data associated with traveling to the
     *                   destination.
     */
    private void printFlightData(int index, Location location, FlightData flightData) {
        System.out.println("\n" + index + ". Destination: " + location.getName());
        System.out
                .println("   Estimated flight duration: " + String.format("%.2f", flightData.getDuration()) + " hours");
        System.out
                .println("   Fuel consumption: " + String.format("%.2f", flightData.getFuelConsumption()) + " gallons");
        System.out.println("   CO2 Emissions: " + String.format("%.2f", flightData.getFutureCO2Emissions()) + " kg");
        System.out.println("   Estimated Flight Cost: $" + String.format("%.2f", flightData.getFutureFlightCost()));
    }

    /**
     * Gets valid user input within a specified range.
     * 
     * @param size The size of the list from which the user selects an option.
     * @return The index selected by the user.
     * @throws InvalidDataException If the user input is invalid.
     */
    private int getValidUserInput(int size) throws InvalidDataException {
        int index = -1;
        while (index < 0 || index >= size) {
            System.out.print("Enter a number between 1 and " + size + ": ");
            index = validateUserInput(scanner.next(), size) - 1;
        }
        return index;
    }

    /**
     * Validates user input to ensure it is an integer within a specified range.
     * 
     * @param input The user input to validate.
     * @param size  The upper limit of the valid range.
     * @return The validated integer input.
     * @throws InvalidDataException If the input is invalid.
     */
    private int validateUserInput(String input, int size) throws InvalidDataException {
        if (!input.matches("\\d+")) {
            throw new InvalidDataException("Invalid data input. Please enter an integer number.",
                    "UserInterface - Not integer", input);
        }
        int parsedInput = Integer.parseInt(input);
        if (parsedInput < 1 || parsedInput > size) {
            throw new InvalidDataException("Invalid input range. Please enter a number between 1 and " + size,
                    "UserInterface - Out of range", input);
        }
        return parsedInput;
    }

    /**
     * Displays a list of options to the user. Each option is retrieved using
     * reflection based on the provided method name.
     * 
     * @param <T>        The type of objects in the options list.
     * @param options    The list of options to display.
     * @param methodName The name of the method to invoke on each object to retrieve
     *                   its display string.
     */
    private <T> void displayOptions(List<T> options, String methodName) {
        for (int i = 0; i < options.size(); i++) {
            try {
                Method method = options.get(i).getClass().getMethod(methodName);
                String name = (String) method.invoke(options.get(i));
                System.out.println((i + 1) + ". " + name);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Prompts the user to decide whether to continue or terminate the program.
     * 
     * @return True if the user wants to continue, false otherwise.
     */
    private boolean promptForContinue() {
        while (true) {
            System.out.print("Do you want to make another calculation? (yes/no): ");
            String answer = scanner.next().trim().toLowerCase();
            if (answer.equals("yes")) {
                return true;
            } else if (answer.equals("no")) {
                return false;
            } else {
                System.out.println("Please type 'yes' or 'no'.");
            }
        }
    }
}
