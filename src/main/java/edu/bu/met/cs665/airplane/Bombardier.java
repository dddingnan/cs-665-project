package edu.bu.met.cs665.airplane;

import edu.bu.met.cs665.exception.InvalidDataException;

public class Bombardier extends AbstractAirplane {
    public Bombardier(String name, double range, double fuelCapacity, double fuelBurnRate, double speed)
            throws InvalidDataException {
        super(name, range, fuelCapacity, fuelBurnRate, speed);
    }

    @Override
    public double getFuelConsumption() {
        // Polymorphism
        // Bombardier has a special engine technology that allows it to save 5% of fuel
        // per hour of flight
        double adjustedFuelBurnRate = this.getFuelBurnRate() * 0.35;
        double hoursOfFlight = this.getFuelCapacity() / adjustedFuelBurnRate;
        return this.getRange() / hoursOfFlight;
    }
}
