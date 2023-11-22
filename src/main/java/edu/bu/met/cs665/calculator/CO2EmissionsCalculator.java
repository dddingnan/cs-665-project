package edu.bu.met.cs665.calculator;

import java.util.concurrent.Callable;

public class CO2EmissionsCalculator implements Callable<Double> {
    // Kilogram
    private static final double CO2_EMISSIONS_PER_GALLON = 10.1;

    private final double fuelConsumption;

    public CO2EmissionsCalculator(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public Double call() {
        // Calculate and return the CO2 emissions.
        return fuelConsumption * CO2_EMISSIONS_PER_GALLON;
    }
}
