/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/21/2023
 * File Name: CO2EmissionsCalculator .java
 * Description: 
 * This class computes the CO2 emissions for a given amount of fuel consumption.
 * By implementing the Callable interface, it allows for concurrent execution 
 * in multi-threaded environments.
 */
package edu.bu.met.cs665.calculator;

import java.util.concurrent.Callable;

public class CO2EmissionsCalculator implements Callable<Double> {
    // Kilogram
    private static final double CO2_EMISSIONS_PER_GALLON = 10.1;

    private final double fuelConsumption;

    /**
     * Constructor for FlightFuelCostCalculator.
     * 
     * @param fuelConsumption The fuel consumption for the flight.
     */
    public CO2EmissionsCalculator(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    /**
     * Calculates the CO2 emissions.
     * 
     * @return The calculated CO2 emissions.
     */
    @Override
    public Double call() {
        return fuelConsumption * CO2_EMISSIONS_PER_GALLON;
    }
}
