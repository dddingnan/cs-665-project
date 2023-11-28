/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/21/2023
 * File Name: TravelCalculator.java
 * Description: 
 * The TravelCalculator class is responsible for calculating reachable locations for a given airplane
 * from a current location, considering the weather conditions and airplane's capabilities.
 * It uses various calculators to determine the feasibility of flights to different locations.
 */
package edu.bu.met.cs665.calculator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import edu.bu.met.cs665.weather.Weather;
import edu.bu.met.cs665.airplane.Airplane;
import edu.bu.met.cs665.flight.FlightData;
import edu.bu.met.cs665.flight.FlightDataBuilder;
import edu.bu.met.cs665.location.Location;

public class TravelCalculator {
        private List<Location> locations;
        private Weather weather;

        /**
         * Constructor for TravelCalculator.
         * 
         * @param locations List of possible destinations.
         * @param weather   Current weather conditions.
         */
        public TravelCalculator(List<Location> locations, Weather weather) {
                this.locations = locations;
                this.weather = weather;
        }

        /**
         * Calculates reachable locations for a given airplane and current location.
         * 
         * @param airplane        The airplane to be used for calculation.
         * @param currentLocation The current location of the airplane.
         * @return Map of Location and corresponding FlightData for reachable locations.
         * @throws Exception If an error occurs during calculation.
         */
        public Map<Location, FlightData> calculateReachableLocations(Airplane airplane, Location currentLocation)
                        throws Exception {
                Map<Location, FlightData> reachableLocations = new HashMap<>();
                for (Location location : locations) {
                        if (!location.equals(currentLocation)) {
                                Callable<Double> distanceTask = new DistanceCalculator(currentLocation.getLatitude(),
                                                currentLocation.getLongitude(), location.getLatitude(),
                                                location.getLongitude());
                                Callable<Double> weatherFactorTask = new WeatherCalculator(weather);

                                double distance = distanceTask.call();
                                double weatherFactor = weatherFactorTask.call();
                                double finalAirplaneRange = airplane.getRange() * weatherFactor;

                                if (distance <= finalAirplaneRange) {
                                        FlightDataBuilder builder = new FlightDataBuilder()
                                                        .withDurationCalculation(airplane, distance)
                                                        .withFuelConsumptionCalculation(airplane, distance,
                                                                        weatherFactor)
                                                        .withCO2EmissionsCalculation()
                                                        .withFlightCostCalculation();
                                        FlightData flightData = builder.build();
                                        reachableLocations.put(location, flightData);
                                }
                        }
                }

                return reachableLocations;
        }
}
