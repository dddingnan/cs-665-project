package edu.bu.met.cs665.calculator;

import java.util.concurrent.Callable;

public class FlightFuelCostCalculator implements Callable<Double> {
    private final double fuelConsumption;
    private static final double FUEL_COST_PER_GALLON = 4.15;

    public FlightFuelCostCalculator(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public Double call() {
        return fuelConsumption * FUEL_COST_PER_GALLON;
    }
}
