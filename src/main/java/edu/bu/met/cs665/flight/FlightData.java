package edu.bu.met.cs665.flight;

import java.util.concurrent.Future;

public class FlightData {
    private final double duration;
    private final double fuelConsumption;
    private final Future<Double> futureCO2Emissions;
    private final Future<Double> futureFlightCost;

    public FlightData(double duration, double fuelConsumption, Future<Double> futureCO2Emissions,
            Future<Double> futureFlightCost) {
        this.duration = duration;
        this.fuelConsumption = fuelConsumption;
        this.futureCO2Emissions = futureCO2Emissions;
        this.futureFlightCost = futureFlightCost;
    }

    public double getDuration() {
        return this.duration;
    }

    public double getFuelConsumption() {
        return this.fuelConsumption;
    }

    public Future<Double> getFutureCO2Emissions() {
        return this.futureCO2Emissions;
    }

    public Future<Double> getFutureFlightCost() {
        return this.futureFlightCost;
    }
}
