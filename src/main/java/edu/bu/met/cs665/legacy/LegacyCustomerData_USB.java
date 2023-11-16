/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/06/2023
 * File Name: LegacyCustomerData_USB.java
 * Description: This interface defines the operations for accessing customer
 * data over a USB.
 */
package edu.bu.met.cs665.legacy;

import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.Optional;

import edu.bu.met.cs665.common.CustomerData;

public class LegacyCustomerData_USB implements CustomerData_USB {
    private List<CustomerData> customerData;

    public LegacyCustomerData_USB(List<CustomerData> customerData) {
        this.customerData = customerData;
    }

    /**
     * Prints the customer data for a given customer ID to the standard output.
     *
     * @param customerId The unique identifier of the customer whose data is to be
     *                   printed.
     */
    @Override
    public void printCustomer(int customerId) {
        Optional<CustomerData> customerOpt = findCustomerById(customerId);
        if (customerOpt.isPresent()) {
            CustomerData customer = customerOpt.get();
            System.out.println(
                    "LegacyCustomerData_USB - printCustomer - Customer ID: " + customer.getCustomerId() + ", Name: "
                            + customer.getCustomerName());
        } else {
            System.out.println("Customer data for ID: " + customerId + " not found.");
        }
    }

    /**
     * Retrieves the customer data for a given customer ID using USB.
     *
     * @param customerId The unique identifier of the customer whose data is to be
     *                   retrieved.
     */
    @Override
    public void getCustomer_USB(int customerId) {
        try {
            // Simulate a longer sleep time to represent a slower response time.
            TimeUnit.SECONDS.sleep(2);
            Optional<CustomerData> customerOpt = findCustomerById(customerId);
            if (customerOpt.isPresent()) {
                CustomerData customer = customerOpt.get();
                System.out
                        .println("LegacyCustomerData_USB - getCustomer_USB - Customer ID: " + customer.getCustomerId() +
                                ", Name: " + customer.getCustomerName());
            } else {
                System.out.println("LegacyCustomerData_USB - Customer data for ID: " + customerId + " not found.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("LegacyCustomerData_USB - The sleep operation was interrupted.");
        }
    }

    /**
     * Helper method to find a CustomerData object by customer ID.
     *
     * @param customerId The ID of the customer to find.
     * @return An Optional containing the found CustomerData or empty if not found.
     */
    public Optional<CustomerData> findCustomerById(int customerId) {
        return customerData.stream()
                .filter(c -> c.getCustomerId() == customerId)
                .findFirst();
    }
}
