package edu.bu.met.cs665.airplane;

import edu.bu.met.cs665.exception.InvalidDataException;

public abstract class AbstractAirplane implements Airplane {
    protected String name;
    protected double range;
    protected double fuelCapacity; // in gallons
    protected double fuelBurnRate; // in gallons per hour
    protected double speed; // added speed attribute

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

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getRange() {
        return this.range;
    }

    @Override
    public double getFuelCapacity() {
        return this.fuelCapacity;
    }

    @Override
    public double getFuelBurnRate() {
        return this.fuelBurnRate;
    }

    @Override
    public double getSpeed() {
        return this.speed;
    }
}
