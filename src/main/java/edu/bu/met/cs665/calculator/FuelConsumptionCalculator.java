package edu.bu.met.cs665.calculator;

import java.util.concurrent.Callable;

import edu.bu.met.cs665.airplane.Airplane;

public class FuelConsumptionCalculator implements Callable<Double> {
    private final Airplane airplane;
    private final double distance;
    private final double weatherFactor;

    public FuelConsumptionCalculator(Airplane airplane, double distance, double weatherFactor) {
        this.airplane = airplane;
        this.distance = distance;
        this.weatherFactor = weatherFactor;
    }

    @Override
    public Double call() {
        return distance / airplane.getFuelConsumption() * weatherFactor;
    }
}
