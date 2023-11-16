package edu.bu.met.cs665.loader;

import edu.bu.met.cs665.common.CustomerData;
import edu.bu.met.cs665.exception.InvalidDataException;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
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
    public void testLoadDriverFileSuccess() throws FileNotFoundException, InvalidDataException, IOException {
        // Provide a path to a known existing file for this test
        String filePath = "src/data/customer.csv";

        List<CustomerData> templates = fileLoader.loadCustomer(filePath);

        // Assert that the list is not null and not empty
        assertNotNull(templates);
        assertFalse(templates.isEmpty());
    }

    @Test
    public void testLoadDriverFileFailure() throws FileNotFoundException,
            InvalidDataException, IOException {
        // Provide a path to a non-existing file for this test
        String filePath = "src/data/abc.csv";

        List<CustomerData> drivers = fileLoader.loadCustomer(filePath);

        // Assert that the list is either null or empty
        assertTrue(drivers == null || drivers.isEmpty());
    }
}