/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/06/2023
 * File Name: CustomerData_USB.java
 * Description: This interface defines the operations for accessing customer
 * data over a USB.
 */
package edu.bu.met.cs665.legacy;

public interface CustomerData_USB {
    /**
     * Prints the customer data for a given customer ID to the standard output.
     *
     * @param customerId The unique identifier of the customer whose data is to be
     *                   printed.
     */
    void printCustomer(int customerId);

    /**
     * Retrieves the customer data for a given customer ID using USB.
     *
     * @param customerId The unique identifier of the customer whose data is to be
     *                   retrieved.
     */
    void getCustomer_USB(int customerId);
}
