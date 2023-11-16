package edu.bu.met.cs665.adapters;

import edu.bu.met.cs665.legacy.LegacyCustomerData_USB;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

public class CustomerDataUSBAdapterTest {

    private LegacyCustomerData_USB mockLegacySystem;
    private CustomerDataUSBAdapter adapter;

    @Before
    public void setUp() {
        // Create a mock instance of LegacyCustomerData_USB
        mockLegacySystem = Mockito.mock(LegacyCustomerData_USB.class);
        // Initialize the adapter with the mocked legacy system
        adapter = new CustomerDataUSBAdapter(mockLegacySystem);
    }

    @Test
    public void testPrintCustomer() {
        int customerId = 123;
        adapter.printCustomer(customerId);
        verify(mockLegacySystem, times(1)).printCustomer(customerId);
    }

    @Test
    public void testGetCustomer_HTTPS() {
        int customerId = 456;
        adapter.getCustomer_HTTPS(customerId);
        verify(mockLegacySystem, times(1)).getCustomer_USB(customerId);
    }
}
