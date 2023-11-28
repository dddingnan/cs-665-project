/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/21/2023
 * File Name: FuelConsumptionCalculator.java
 * Description: 
 * The FuelConsumptionCalculator class implements Callable and calculates the fuel consumption for a given airplane,
 * considering the distance to be traveled and the weather factor.
 */
package edu.bu.met.cs665.calculator;

import java.util.concurrent.Callable;

import edu.bu.met.cs665.airplane.Airplane;

public class FuelConsumptionCalculator implements Callable<Double> {
    private final Airplane airplane;
    private final double distance;
    private final double weatherFactor;

    /**
     * Constructor for FuelConsumptionCalculator.
     * 
     * @param airplane      The airplane for which fuel consumption is calculated.
     * @param distance      The distance to be traveled.
     * @param weatherFactor The weather factor affecting fuel consumption.
     */
    public FuelConsumptionCalculator(Airplane airplane, double distance, double weatherFactor) {
        this.airplane = airplane;
        this.distance = distance;
        this.weatherFactor = weatherFactor;
    }

    /**
     * Calculates the fuel consumption.
     * 
     * @return The calculated fuel consumption.
     */
    @Override
    public Double call() {
        return distance / airplane.getFuelConsumption() * weatherFactor;
    }
}
