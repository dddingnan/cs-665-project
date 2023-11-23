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

    public UserInterface(Weather currentWeather, LocationRepository locationRepo, AirplaneRepository airplaneRepo)
            throws SQLException {
        this.locations = locationRepo.selectAll();
        this.airplanes = airplaneRepo.selectAll();
        this.travelCalculator = new TravelCalculator(locations, currentWeather);
        this.scanner = new Scanner(System.in);
    }

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

    private Location selectCurrentLocation() throws InvalidDataException {
        System.out.println("Please select your current location:");
        displayOptions(locations, "getName");

        int locationIndex = getValidUserInput(locations.size());
        return locations.get(locationIndex);
    }

    private Airplane selectAirplane() throws InvalidDataException {
        System.out.println("Please select your airplane type:");
        displayOptions(airplanes, "getName");

        int airplaneIndex = getValidUserInput(airplanes.size());
        return airplanes.get(airplaneIndex);
    }

    private void displayReachableLocations(Location currentLocation, Airplane airplane) throws Exception {
        Map<Location, FlightData> reachableLocations = travelCalculator.calculateReachableLocations(airplane,
                currentLocation);
        System.out.println("Potential city destinations on a single tank of fuel:");
        int index = 1;
        for (Map.Entry<Location, FlightData> entry : reachableLocations.entrySet()) {
            printFlightData(index++, entry.getKey(), entry.getValue());
        }
    }

    private void printFlightData(int index, Location location, FlightData flightData) {
        System.out.println("\n" + index + ". Destination: " + location.getName());
        System.out
                .println("   Estimated flight duration: " + String.format("%.2f", flightData.getDuration()) + " hours");
        System.out
                .println("   Fuel consumption: " + String.format("%.2f", flightData.getFuelConsumption()) + " gallons");
        System.out.println("   CO2 Emissions: " + String.format("%.2f", flightData.getFutureCO2Emissions()) + " kg");
        System.out.println("   Estimated Flight Cost: $" + String.format("%.2f", flightData.getFutureFlightCost()));
    }

    private int getValidUserInput(int size) throws InvalidDataException {
        int index = -1;
        while (index < 0 || index >= size) {
            System.out.print("Enter a number between 1 and " + size + ": ");
            index = validateUserInput(scanner.next(), size) - 1;
        }
        return index;
    }

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
