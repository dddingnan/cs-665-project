/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/21/2023
 * File Name: AbstractAirplane.java
 * Description: The AbstractAirplane class serves as a base for 
 * different airplane types in the flight
 * simulation system.
 */
package edu.bu.met.cs665.airplane;

import edu.bu.met.cs665.exception.InvalidDataException;

public abstract class AbstractAirplane implements Airplane {
    protected String name;
    protected double range;
    protected double fuelCapacity; // in gallons
    protected double fuelBurnRate; // in gallons per hour
    protected double speed; // added speed attribute

    /**
     * Initializes a new airplane instance with specified attributes. Validates the
     * input
     * data for fuel capacity and fuel burn rate to ensure they are within
     * acceptable ranges.
     * 
     * @param name         The name of the airplane.
     * @param range        The flying range of the airplane.
     * @param fuelCapacity The fuel capacity of the airplane in gallons.
     * @param fuelBurnRate The fuel burn rate of the airplane in gallons per hour.
     * @param speed        The speed of the airplane.
     * @throws InvalidDataException If fuel capacity or fuel burn rate is invalid.
     */
    public AbstractAirplane(String name, double range, double fuelCapacity, double fuelBurnRate, double speed)
            throws InvalidDataException {
        if (fuelCapacity < 0 || fuelBurnRate < 0) {
            throw new InvalidDataException("Invalid airplane data for " + name, "Airplane",
                    "fuelCapacity: " + fuelCapacity + ", fuelBurnRate:" + fuelBurnRate);
        }
        this.name = name;
        this.range = range;
        this.fuelCapacity = fuelCapacity;
        this.fuelBurnRate = fuelBurnRate;
        this.speed = speed;
    }

    /**
     * Gets the name of the airplane.
     * 
     * @return The name of the airplane.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Gets the flying range of the airplane.
     * 
     * @return The range of the airplane.
     */
    @Override
    public double getRange() {
        return this.range;
    }

    /**
     * Gets the fuel capacity of the airplane.
     * 
     * @return The fuel capacity in gallons.
     */
    @Override
    public double getFuelCapacity() {
        return this.fuelCapacity;
    }

    /**
     * Gets the fuel burn rate of the airplane.
     * 
     * @return The fuel burn rate in gallons per hour.
     */
    @Override
    public double getFuelBurnRate() {
        return this.fuelBurnRate;
    }

    /**
     * Gets the speed of the airplane.
     * 
     * @return The speed of the airplane.
     */
    @Override
    public double getSpeed() {
        return this.speed;
    }
}
