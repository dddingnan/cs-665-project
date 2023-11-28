/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/21/2023
 * File Name: FlightDurationCalculator .java
 * Description: 
 * This class calculates the duration of a flight for a given airplane and distance. 
 * It implements the Callable interface to facilitate concurrent execution. 
 * The class takes into account the speed of the airplane to compute the travel time.
 */
package edu.bu.met.cs665.calculator;

import java.util.concurrent.Callable;

import edu.bu.met.cs665.airplane.Airplane;

public class FlightDurationCalculator implements Callable<Double> {
    private final Airplane airplane;
    private final double distance;

    /**
     * Constructor for FlightDurationCalculator.
     * 
     * @param airplane The airplane model.
     * @param distance The distance number.
     */
    public FlightDurationCalculator(Airplane airplane, double distance) {
        this.airplane = airplane;
        this.distance = distance;
    }

    /**
     * Calculates the fuel cost for the flight.
     * 
     * @return The calculated flight duration.
     */
    @Override
    public Double call() {
        double speed = airplane.getSpeed();
        return distance / speed; // Time in hours
    }
}
