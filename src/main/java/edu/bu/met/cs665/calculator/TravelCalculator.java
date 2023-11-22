package edu.bu.met.cs665.calculator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.bu.met.cs665.weather.Weather;
import edu.bu.met.cs665.airplane.Airplane;
import edu.bu.met.cs665.flight.FlightData;
import edu.bu.met.cs665.location.Location;

public class TravelCalculator {
        private List<Location> locations;
        private Weather weather;

        public TravelCalculator(List<Location> locations, Weather weather) {
                this.locations = locations;
                this.weather = weather;
        }

        public Map<Location, FlightData> calculateReachableLocations(Airplane airplane,
                        Location currentLocation) throws InterruptedException, ExecutionException {
                Map<Location, FlightData> reachableLocations = new HashMap<>();
                // Concurrency
                // Get the number of available processor cores
                int processors = Runtime.getRuntime().availableProcessors();
                // Create a thread pool with a number of threads equal to half of the available
                // processor cores
                ExecutorService executorService = Executors.newFixedThreadPool(processors / 2);

                for (Location location : locations) {
                        if (!location.equals(currentLocation)) {
                                // Create a new task, submit it to the executor
                                // service and
                                // store the reference to the Future object. The Future object will be used to
                                // retrieve
                                // the result of the calculation when it is ready.
                                double distance = executorService
                                                .submit(new DistanceCalculator(currentLocation.getLatitude(),
                                                                currentLocation.getLongitude(),
                                                                location.getLatitude(), location.getLongitude()))
                                                .get();

                                double weatherFactor = executorService
                                                .submit(new WeatherCalculator(weather)).get();

                                double finalAirplaneRange = airplane.getRange() * weatherFactor;

                                if (distance <= finalAirplaneRange) {
                                        double duration = executorService
                                                        .submit(new FlightDurationCalculator(airplane, distance)).get();
                                        double fuelConsumption = executorService
                                                        .submit(new FuelConsumptionCalculator(airplane, distance,
                                                                        weatherFactor))
                                                        .get();
                                        double futureCO2Emissions = executorService
                                                        .submit(new CO2EmissionsCalculator(fuelConsumption)).get();
                                        double futureFlightCost = executorService
                                                        .submit(new FlightFuelCostCalculator(fuelConsumption)).get();
                                        FlightData flightData = new FlightData(duration, fuelConsumption,
                                                        futureCO2Emissions,
                                                        futureFlightCost);
                                        reachableLocations.put(location, flightData);
                                }
                        }
                }
                // Shutdown the executor service. No new tasks will be accepted. This does not
                // interrupt previously submitted tasks and they will continue to run to
                // completion.
                executorService.shutdown();
                return reachableLocations;
        }
}
