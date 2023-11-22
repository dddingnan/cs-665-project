package edu.bu.met.cs665.calculator;

import java.util.concurrent.Callable;

import edu.bu.met.cs665.airplane.Airplane;

public class FlightDurationCalculator implements Callable<Double> {
    private final Airplane airplane;
    private final double distance;

    public FlightDurationCalculator(Airplane airplane, double distance) {
        this.airplane = airplane;
        this.distance = distance;
    }

    @Override
    public Double call() {
        double speed = airplane.getSpeed();
        return distance / speed; // Time in hours
    }
}
