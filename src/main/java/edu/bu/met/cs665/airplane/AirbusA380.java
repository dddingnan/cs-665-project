package edu.bu.met.cs665.airplane;

import edu.bu.met.cs665.exception.InvalidDataException;

public class AirbusA380 extends AbstractAirplane {
    public AirbusA380(String name, double range, double fuelCapacity, double fuelBurnRate, double speed)
            throws InvalidDataException {
        super(name, range, fuelCapacity, fuelBurnRate, speed);
    }

    @Override
    public double getFuelConsumption() {
        // Polymorphism
        // AirbusA380 has an aerodynamics feature that increases the effective range by
        // 10%
        double adjustedRange = this.getRange() * 1.1;
        double hoursOfFlight = this.getFuelCapacity() / this.getFuelBurnRate();
        return adjustedRange / hoursOfFlight;
    }
}
