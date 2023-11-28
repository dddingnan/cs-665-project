package edu.bu.met.cs665.flight;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FlightDataTest {

    private FlightData flightData;
    private final double duration = 5.0; // 5 hours
    private final double fuelConsumption = 500.0; // 500 gallons
    private final double co2Emissions = 5050.0; // 5050 kg
    private final double flightCost = 2500.0; // $2500

    @Before
    public void setUp() {
        flightData = new FlightData(duration, fuelConsumption, co2Emissions, flightCost);
    }

    @Test
    public void testDuration() {
        assertEquals(duration, flightData.getDuration(), 0.0);
    }

    @Test
    public void testFuelConsumption() {
        assertEquals(fuelConsumption, flightData.getFuelConsumption(), 0.0);
    }

    @Test
    public void testCO2Emissions() {
        assertEquals(co2Emissions, flightData.getFutureCO2Emissions(), 0.0);
    }

    @Test
    public void testFlightCost() {
        assertEquals(flightCost, flightData.getFutureFlightCost(), 0.0);
    }
}
