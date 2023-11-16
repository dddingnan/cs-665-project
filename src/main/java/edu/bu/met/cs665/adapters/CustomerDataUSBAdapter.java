/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/06/2023
 * File Name: CustomerDataUSBAdapter.java
 * Description: This class allows the legacy CustomerData_USB interface to be used as
 * if it were the new CustomerData_HTTPS interface. This enables the old system
 * to be compatible with the new system without changing the legacy codebase.
 */
package edu.bu.met.cs665.adapters;

import edu.bu.met.cs665.new_system.CustomerData_HTTPS;
import edu.bu.met.cs665.legacy.LegacyCustomerData_USB;

/**
 * Adapter for the legacy CustomerData_USB interface, adapting it to the new
 * CustomerData_HTTPS interface.
 */
public class CustomerDataUSBAdapter implements CustomerData_HTTPS {

    private final LegacyCustomerData_USB legacyCustomerData;

    /**
     * Constructs a new adapter with a reference to the legacy customer data system.
     *
     * @param legacyCustomerData The legacy CustomerData_USB object.
     */
    public CustomerDataUSBAdapter(LegacyCustomerData_USB legacyCustomerData) {
        this.legacyCustomerData = legacyCustomerData;
    }

    /**
     * Prints customer data for a specified customer ID by calling to
     * the legacy system's print method. This allows the client code to use
     * the print functionality of the legacy system while conforming to the new
     * interface's expectations.
     *
     * @param customerId The ID of the customer to be printed.
     */
    @Override
    public void printCustomer(int customerId) {
        legacyCustomerData.printCustomer(customerId);
    }

    /**
     * By delegating the call to the legacy system's retrieval method. The method
     * adapts the legacy USB interface to the expected HTTPS interface method.
     * 
     * @param customerId The ID of the customer whose data is to be retrieved.
     */
    @Override
    public void getCustomer_HTTPS(int customerId) {
        legacyCustomerData.getCustomer_USB(customerId);
    }
}
