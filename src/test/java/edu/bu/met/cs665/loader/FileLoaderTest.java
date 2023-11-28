package edu.bu.met.cs665.loader;

import edu.bu.met.cs665.location.Location;
import edu.bu.met.cs665.airplane.Airplane;
import edu.bu.met.cs665.weather.Weather;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.List;
import static org.junit.Assert.*;

public class FileLoaderTest {
    private FileLoader fileLoader;

    @Before
    public void setUp() {
        fileLoader = new FileLoader();
    }

    @Test
    public void testLoadLocationsFromFile() throws Exception {
        List<Location> locations = fileLoader.loadLocationsFromFile("src/data/locations.csv");
        assertNotNull(locations);
        assertFalse(locations.isEmpty());
    }

    @Test
    public void testLoadAirplanesFromFile() throws Exception {
        List<Airplane> airplanes = fileLoader.loadAirplanesFromFile("src/data/airplanes.csv");
        assertNotNull(airplanes);
        assertFalse(airplanes.isEmpty());
    }

    @Test
    public void testLoadWeatherFromFile() throws Exception {
        List<Weather> weatherList = fileLoader.loadWeatherFromFile("src/data/weather.csv");
        assertNotNull(weatherList);
        assertFalse(weatherList.isEmpty());
    }

    @Test(expected = IOException.class)
    public void testLoadLocationsFromNonexistentFile() throws Exception {
        fileLoader.loadLocationsFromFile("nonexistent.csv");
    }
}
