/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/21/2023
 * File Name: FlightData.java
 * Description: Encapsulates information about a specific flight, including its
 * duration, fuel consumption, CO2 emissions, and cost. 
 * This class is designed to provide detailed data for analyzing 
 * the feasibility and environmental impact of airplane flights.
 */
package edu.bu.met.cs665.flight;

public class FlightData {
    private final double duration;
    private final double fuelConsumption;
    private final double futureCO2Emissions;
    private final double futureFlightCost;

    /**
     * Constructs a new FlightData object with specified parameters.
     * 
     * @param duration           The duration of the flight in hours.
     * @param fuelConsumption    The total fuel consumption for the flight in
     *                           gallons.
     * @param futureCO2Emissions The estimated CO2 emissions for the flight in
     *                           kilograms.
     * @param futureFlightCost   The estimated cost of the flight in currency units.
     */
    public FlightData(double duration, double fuelConsumption, double futureCO2Emissions,
            double futureFlightCost) {
        this.duration = duration;
        this.fuelConsumption = fuelConsumption;
        this.futureCO2Emissions = futureCO2Emissions;
        this.futureFlightCost = futureFlightCost;
    }

    /**
     * Retrieves the flight duration.
     * 
     * @return The duration of the flight in hours.
     */
    public double getDuration() {
        return this.duration;
    }

    /**
     * Retrieves the total fuel consumption for the flight.
     * 
     * @return The fuel consumption in gallons.
     */
    public double getFuelConsumption() {
        return this.fuelConsumption;
    }

    /**
     * Retrieves the estimated CO2 emissions for the flight.
     * 
     * @return The CO2 emissions in kilograms.
     */
    public double getFutureCO2Emissions() {
        return this.futureCO2Emissions;
    }

    /**
     * Retrieves the estimated cost of the flight.
     * 
     * @return The flight cost in currency units.
     */
    public double getFutureFlightCost() {
        return this.futureFlightCost;
    }
}
