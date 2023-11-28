/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/21/2023
 * File Name: AirbusA320.java
 * Description: Represents the Airbus A320 airplane model.
 */
package edu.bu.met.cs665.airplane;

import edu.bu.met.cs665.exception.InvalidDataException;

public class AirbusA320 extends AbstractAirplane {
    /**
     * Constructs an instance of the AirbusA320 with the specified parameters.
     * 
     * @param name         The name of the airplane.
     * @param range        The flying range of the airplane.
     * @param fuelCapacity The fuel capacity of the airplane in gallons.
     * @param fuelBurnRate The fuel burn rate of the airplane in gallons per hour.
     * @param speed        The speed of the airplane.
     * @throws InvalidDataException If any input parameter is invalid.
     */
    public AirbusA320(String name, double range, double fuelCapacity, double fuelBurnRate, double speed)
            throws InvalidDataException {
        super(name, range, fuelCapacity, fuelBurnRate, speed);
    }

    /**
     * Calculates the fuel consumption of the Airbus A320. This method considers the
     * airplane's aerodynamics feature that effectively increases its range.
     * 
     * @return The calculated fuel consumption based on adjusted range
     *         and hours of flight.
     */
    @Override
    public double getFuelConsumption() {
        // Polymorphism
        // AirbusA320 has an aerodynamics feature that increases the effective range by
        // 10%
        double adjustedRange = this.getRange() * 1.08;
        double hoursOfFlight = this.getFuelCapacity() / this.getFuelBurnRate();
        return adjustedRange / hoursOfFlight;
    }
}
