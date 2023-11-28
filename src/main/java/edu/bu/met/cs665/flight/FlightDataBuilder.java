/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/21/2023
 * File Name: FlightDataBuilder.java
 * Description: Using the Builder design pattern. 
 * This class simplifies the creation of FlightData objects
 * by allowing for the step-by-step calculation and setting 
 * of various flight parameters such as duration, fuel consumption,
 * CO2 emissions, and flight cost. The class leverages concurrent 
 * execution to perform calculations efficiently.
 */
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

    /**
     * Constructs a new FlightDataBuilder and initializes the ExecutorService for
     * concurrent task execution.
     */
    public FlightDataBuilder() {
        int numberOfThreads = Runtime.getRuntime().availableProcessors() / 2;
        this.executorService = Executors.newFixedThreadPool(numberOfThreads);
    }

    /**
     * Executes a given task and returns its result.
     * 
     * @param <T>  The type of the result produced by the task.
     * @param task The task to be executed.
     * @return The result of the task execution.
     * @throws ExecutionException   If the computation threw an exception.
     * @throws InterruptedException If the current thread was interrupted while
     *                              waiting.
     */
    private <T> T executeTask(Callable<T> task) throws ExecutionException, InterruptedException {
        return executorService.submit(task).get();
    }

    /**
     * Sets the flight duration based on the given airplane and distance.
     * 
     * @param airplane The airplane for which to calculate the duration.
     * @param distance The distance of the flight.
     * @return The current FlightDataBuilder instance for chaining.
     * @throws ExecutionException   If the computation threw an exception.
     * @throws InterruptedException If the current thread was interrupted while
     *                              waiting.
     */
    public FlightDataBuilder withDurationCalculation(Airplane airplane, double distance)
            throws ExecutionException, InterruptedException {
        this.duration = executeTask(new FlightDurationCalculator(airplane, distance));
        return this;
    }

    /**
     * Sets the fuel consumption based on the given airplane, distance, and weather
     * factor.
     * 
     * @param airplane      The airplane for which to calculate fuel consumption.
     * @param distance      The distance of the flight.
     * @param weatherFactor The weather factor affecting fuel consumption.
     * @return The current FlightDataBuilder instance for chaining.
     * @throws ExecutionException   If the computation threw an exception.
     * @throws InterruptedException If the current thread was interrupted while
     *                              waiting.
     */
    public FlightDataBuilder withFuelConsumptionCalculation(Airplane airplane, double distance, double weatherFactor)
            throws ExecutionException, InterruptedException {
        this.fuelConsumption = executeTask(new FuelConsumptionCalculator(airplane, distance, weatherFactor));
        return this;
    }

    /**
     * Sets the CO2 emissions based on the previously calculated fuel consumption.
     * 
     * @return The current FlightDataBuilder instance for chaining.
     * @throws ExecutionException   If the computation threw an exception.
     * @throws InterruptedException If the current thread was interrupted while
     *                              waiting.
     */
    public FlightDataBuilder withCO2EmissionsCalculation() throws ExecutionException, InterruptedException {
        this.co2Emissions = executeTask(new CO2EmissionsCalculator(this.fuelConsumption));
        return this;
    }

    /**
     * Sets the flight cost based on the previously calculated fuel consumption.
     * 
     * @return The current FlightDataBuilder instance for chaining.
     * @throws ExecutionException   If the computation threw an exception.
     * @throws InterruptedException If the current thread was interrupted while
     *                              waiting.
     */
    public FlightDataBuilder withFlightCostCalculation() throws ExecutionException, InterruptedException {
        this.flightCost = executeTask(new FlightFuelCostCalculator(this.fuelConsumption));
        return this;
    }

    /**
     * Builds the FlightData object with the calculated parameters and shuts down
     * the ExecutorService.
     * 
     * @return A new FlightData object containing the calculated flight details.
     */
    public FlightData build() {
        executorService.shutdown();
        return new FlightData(duration, fuelConsumption, co2Emissions, flightCost);
    }
}
