package edu.bu.met.cs665.flight;

public class FlightData {
    private final double duration;
    private final double fuelConsumption;
    private final double futureCO2Emissions;
    private final double futureFlightCost;

    public FlightData(double duration, double fuelConsumption, double futureCO2Emissions,
            double futureFlightCost) {
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

    public double getFutureCO2Emissions() {
        return this.futureCO2Emissions;
    }

    public double getFutureFlightCost() {
        return this.futureFlightCost;
    }
}
