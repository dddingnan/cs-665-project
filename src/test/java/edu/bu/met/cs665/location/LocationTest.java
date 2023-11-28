package edu.bu.met.cs665.location;

import edu.bu.met.cs665.exception.InvalidDataException;
import org.junit.Test;
import static org.junit.Assert.*;

public class LocationTest {

    @Test
    public void testValidLocation() {
        String name = "Boston";
        double latitude = 42.3601;
        double longitude = -71.0589;
        Location location = null;

        try {
            location = new Location(name, latitude, longitude);
        } catch (InvalidDataException e) {
            fail("Should not have thrown an exception for valid latitude and longitude");
        }

        assertNotNull(location);
        assertEquals(name, location.getName());
        assertEquals(latitude, location.getLatitude(), 0.001);
        assertEquals(longitude, location.getLongitude(), 0.001);
    }

    @Test(expected = InvalidDataException.class)
    public void testInvalidLatitude() throws InvalidDataException {
        String name = "InvalidLocation";
        double invalidLatitude = 100.0; // Invalid latitude
        double longitude = -71.0589;
        new Location(name, invalidLatitude, longitude);
    }

    @Test(expected = InvalidDataException.class)
    public void testInvalidLongitude() throws InvalidDataException {
        String name = "InvalidLocation";
        double latitude = 42.3601;
        double invalidLongitude = 200.0; // Invalid longitude
        new Location(name, latitude, invalidLongitude);
    }
}
