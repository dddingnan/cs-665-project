package edu.bu.met.cs665.legacy;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.bu.met.cs665.common.CustomerData;

public class LegacyCustomerData_USBTest {

    private LegacyCustomerData_USB usbSystem;
    private List<CustomerData> customerDataList;

    @Before
    public void setUp() {
        customerDataList = new ArrayList<>();
        customerDataList.add(new CustomerData(1, "John Doe"));
        customerDataList.add(new CustomerData(2, "Jane Smith"));
        usbSystem = new LegacyCustomerData_USB(customerDataList);
    }

    @Test
    public void testFindCustomerById_ExistingCustomer() {
        // Execute the method on the class under test
        Optional<CustomerData> result = usbSystem.findCustomerById(1);

        // Assertions
        Assert.assertTrue("Expected customer data to be present", result.isPresent());
        Assert.assertEquals("Expected the name to match", "John Doe", result.get().getCustomerName());
    }

    @Test
    public void testFindCustomerById_NonExistingCustomer() {
        // Execute the method on the class under test
        Optional<CustomerData> result = usbSystem.findCustomerById(999);

        // Assertions
        Assert.assertFalse("Expected customer data to be absent", result.isPresent());
    }
}
