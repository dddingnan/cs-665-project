package edu.bu.met.cs665.flight;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.bu.met.cs665.airplane.Airplane;
import edu.bu.met.cs665.calculator.CO2EmissionsCalculator;
import edu.bu.met.cs665.calculator.FlightDurationCalculator;
import edu.bu.met.cs665.calculator.FlightFuelCostCalculator;
import edu.bu.met.cs665.calculator.FuelConsumptionCalculator;

public class FlightDataBuilder {
    private double duration;
    private double fuelConsumption;
    private double co2Emissions;
    private double flightCost;
    private ExecutorService executorService;

    public FlightDataBuilder() {
        int numberOfThreads = Runtime.getRuntime().availableProcessors() / 2;
        this.executorService = Executors.newFixedThreadPool(numberOfThreads);
    }

    private <T> T executeTask(Callable<T> task) throws ExecutionException, InterruptedException {
        return executorService.submit(task).get();
    }

    public FlightDataBuilder withDurationCalculation(Airplane airplane, double distance)
            throws ExecutionException, InterruptedException {
        this.duration = executeTask(new FlightDurationCalculator(airplane, distance));
        return this;
    }

    public FlightDataBuilder withFuelConsumptionCalculation(Airplane airplane, double distance, double weatherFactor)
            throws ExecutionException, InterruptedException {
        this.fuelConsumption = executeTask(new FuelConsumptionCalculator(airplane, distance, weatherFactor));
        return this;
    }

    public FlightDataBuilder withCO2EmissionsCalculation() throws ExecutionException, InterruptedException {
        this.co2Emissions = executeTask(new CO2EmissionsCalculator(this.fuelConsumption));
        return this;
    }

    public FlightDataBuilder withFlightCostCalculation() throws ExecutionException, InterruptedException {
        this.flightCost = executeTask(new FlightFuelCostCalculator(this.fuelConsumption));
        return this;
    }

    public FlightData build() {
        executorService.shutdown();
        return new FlightData(duration, fuelConsumption, co2Emissions, flightCost);
    }
}
