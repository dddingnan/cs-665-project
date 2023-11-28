/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/21/2023
 * File Name: FlightFuelCostCalculator.java
 * Description: 
 * The FlightFuelCostCalculator class implements Callable and calculates the fuel cost for a flight,
 * based on the fuel consumption and the cost per gallon.
 */
package edu.bu.met.cs665.calculator;

import java.util.concurrent.Callable;

public class FlightFuelCostCalculator implements Callable<Double> {
    private final double fuelConsumption;
    private static final double FUEL_COST_PER_GALLON = 4.15;

    /**
     * Constructor for FlightFuelCostCalculator.
     * 
     * @param fuelConsumption The fuel consumption for the flight.
     */
    public FlightFuelCostCalculator(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    /**
     * Calculates the fuel cost for the flight.
     * 
     * @return The calculated fuel cost.
     */
    @Override
    public Double call() {
        return fuelConsumption * FUEL_COST_PER_GALLON;
    }
}
