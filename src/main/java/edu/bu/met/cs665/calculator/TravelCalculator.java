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

        public TravelCalculator(List<Location> locations, Weather weather) {
                this.locations = locations;
                this.weather = weather;
        }

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
