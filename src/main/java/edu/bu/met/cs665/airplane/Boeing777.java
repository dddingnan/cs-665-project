/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/21/2023
 * File Name: Boeing777.java
 * Description: Represents the Boeing777 airplane model.
 */
package edu.bu.met.cs665.airplane;

import edu.bu.met.cs665.exception.InvalidDataException;

public class Boeing777 extends AbstractAirplane {
    /**
     * Constructs an instance of the Boeing777 with the specified parameters.
     * 
     * @param name         The name of the airplane.
     * @param range        The flying range of the airplane.
     * @param fuelCapacity The fuel capacity of the airplane in gallons.
     * @param fuelBurnRate The fuel burn rate of the airplane in gallons per hour.
     * @param speed        The speed of the airplane.
     * @throws InvalidDataException If any input parameter is invalid.
     */
    public Boeing777(String name, double range, double fuelCapacity, double fuelBurnRate, double speed)
            throws InvalidDataException {
        super(name, range, fuelCapacity, fuelBurnRate, speed);
    }

    /**
     * Calculates the fuel consumption of the Boeing777. This method considers the
     * airplane's aerodynamics feature that effectively increases its range.
     * 
     * @return The calculated fuel consumption based on adjusted range
     *         and hours of flight.
     */
    @Override
    public double getFuelConsumption() {
        // Polymorphism
        // Boeing777 has a special engine technology that allows it to save 5% of fuel
        // per hour of flight
        double adjustedFuelBurnRate = this.getFuelBurnRate() * 0.98;
        double hoursOfFlight = this.getFuelCapacity() / adjustedFuelBurnRate;
        return this.getRange() / hoursOfFlight;
    }
}
