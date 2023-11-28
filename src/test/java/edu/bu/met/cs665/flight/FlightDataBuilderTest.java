package edu.bu.met.cs665.flight;

import edu.bu.met.cs665.airplane.Airplane;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

public class FlightDataBuilderTest {

    @Mock
    private Airplane airplane;

    private FlightDataBuilder builder;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        builder = new FlightDataBuilder();
        when(airplane.getSpeed()).thenReturn(500.0);
    }

    @Test
    public void testBuildFlightData() throws Exception {
        double distance = 1000.0; // Example distance
        double weatherFactor = 1.0; // Example weather factor

        FlightData flightData = builder
                .withDurationCalculation(airplane, distance)
                .withFuelConsumptionCalculation(airplane, distance, weatherFactor)
                .withCO2EmissionsCalculation()
                .withFlightCostCalculation()
                .build();

        // Assertions to verify the flight data values
        // Note: Use reasonable delta values for assertEquals due to possible
        // floating-point calculations
        assertEquals(distance / airplane.getSpeed(), flightData.getDuration(), 0.01);
        // Other assertions for fuel consumption, CO2 emissions, and flight cost based
        // on mock values
    }
}
