package edu.bu.met.cs665.user;

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
            System.out.println("Please select your current location:");
            for (int i = 0; i < locations.size(); i++) {
                System.out.println((i + 1) + ". " + locations.get(i).getName());
            }

            int locationIndex = -1;
            while (locationIndex < 0 || locationIndex >= locations.size()) {
                System.out.print("Enter a number between 1 and " + locations.size() + ": ");
                try {
                    String input = scanner.next();
                    if (!input.matches("\\d+")) { // Check if input not an integer number
                        throw new InvalidDataException("Invalid data input. Please enter an integer number.",
                                "UserInterface - Location - Not integer", input);
                    }

                    locationIndex = Integer.parseInt(input) - 1;

                    if (locationIndex < 0 || locationIndex >= locations.size()) { // Check if input is within the valid
                        throw new InvalidDataException(
                                "Invalid input range. Please enter a number between 1 and " + locations.size(),
                                "UserInterface - Not within the valid range", input);
                    }

                } catch (InvalidDataException e) {
                    System.out.println(e.getMessage());
                    scanner.nextLine(); // discard the invalid input
                    locationIndex = -1; // Reset the index, forcing the loop to repeat
                    continue;
                }
            }
            Location currentLocation = locations.get(locationIndex);

            System.out.println("Please select your airplane type:");
            for (int i = 0; i < airplanes.size(); i++) {
                System.out.println((i + 1) + ". " + airplanes.get(i).getName());
            }

            int airplaneIndex = -1;
            while (airplaneIndex < 0 || airplaneIndex >= airplanes.size()) {
                System.out.print("Enter a number between 1 and " + airplanes.size() + ": ");
                try {
                    String input = scanner.next();
                    if (!input.matches("\\d+")) { // Check if input not an integer number
                        throw new InvalidDataException("Invalid data input. Please enter an integer number.",
                                "UserInterface - Airplane - Not integer", input);
                    }

                    airplaneIndex = Integer.parseInt(input) - 1;

                    if (airplaneIndex < 0 || airplaneIndex >= locations.size()) { // Check if input is within the valid
                        // range
                        throw new InvalidDataException(
                                "Invalid input range. Please enter a number between 1 and " + airplanes.size(),
                                "UserInterface - Not within the valid range", input);
                    }

                } catch (InvalidDataException e) {
                    System.out.println(e.getMessage());
                    scanner.nextLine(); // discard the invalid input
                    airplaneIndex = -1; // Reset the index, forcing the loop to repeat
                    continue;
                }
            }
            Airplane airplane = airplanes.get(airplaneIndex);

            Map<Location, FlightData> reachableLocations = travelCalculator.calculateReachableLocations(airplane,
                    currentLocation);
            System.out.println("Potential city destinations on a single tank of fuel:");
            int index = 1;
            for (Map.Entry<Location, FlightData> entry : reachableLocations.entrySet()) {
                Location location = entry.getKey();
                FlightData flightData = entry.getValue();
                double duration = flightData.getDuration();
                double fuelConsumption = flightData.getFuelConsumption();
                double CO2Emissions = flightData.getFutureCO2Emissions(); // Here we retrieve the result
                double flightCost = flightData.getFutureFlightCost(); // Here we retrieve the result

                System.out.println("\n" + index++ + ". Destination: " + location.getName());
                System.out.println("   Estimated flight duration: " + String.format("%.2f", duration) + " hours");
                System.out.println("   Fuel consumption: " + String.format("%.2f", fuelConsumption) + " gallons");
                System.out.println("   CO2 Emissions: " + String.format("%.2f", CO2Emissions) + " kg");
                System.out.println("   Estimated Flight Cost: $" + String.format("%.2f", flightCost));
            }

            System.out.println("--------------------------------------------------------");
            System.out.println("Do you want to make another calculation? (yes/no)");
            String answer = scanner.next();
            if (answer.equalsIgnoreCase("no")) {
                System.out.println("See you next time~!");
                break;
            }
        }
        scanner.close();
    }
}
