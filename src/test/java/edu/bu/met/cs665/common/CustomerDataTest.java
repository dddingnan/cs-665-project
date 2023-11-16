package edu.bu.met.cs665.common;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CustomerDataTest {

    private CustomerData customerData;

    @Before
    public void setUp() {
        customerData = new CustomerData(123, "Donald Duck");
    }

    @Test
    public void testGetCustomerId() {
        // Test to ensure the customerId is returned correctly
        assertEquals("Customer ID should be 123", 123, customerData.getCustomerId());
    }

    @Test
    public void testGetCustomerName() {
        // Test to ensure the customerName is returned correctly
        assertEquals("Customer name should be Donald Duck", "Donald Duck", customerData.getCustomerName());
    }
}
