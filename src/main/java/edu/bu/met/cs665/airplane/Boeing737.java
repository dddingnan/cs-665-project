package edu.bu.met.cs665.airplane;

import edu.bu.met.cs665.exception.InvalidDataException;

public class Boeing737 extends AbstractAirplane {
    public Boeing737(String name, double range, double fuelCapacity, double fuelBurnRate, double speed)
            throws InvalidDataException {
        super(name, range, fuelCapacity, fuelBurnRate, speed);
    }

    @Override
    public double getFuelConsumption() {
        // Polymorphism
        // Boeing747 has a special engine technology that allows it to save 5% of fuel
        // per hour of flight
        double adjustedFuelBurnRate = this.getFuelBurnRate() * 0.87;
        double hoursOfFlight = this.getFuelCapacity() / adjustedFuelBurnRate;
        return this.getRange() / hoursOfFlight;
    }
}
