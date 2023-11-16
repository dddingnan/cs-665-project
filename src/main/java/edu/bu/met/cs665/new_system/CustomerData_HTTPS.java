/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/06/2023
 * File Name: CustomerData_HTTPS.java
 * Description: This interface defines the operations for accessing customer
 * data over an HTTPS protocol.
 */
package edu.bu.met.cs665.new_system;

public interface CustomerData_HTTPS {
    /**
     * Prints the customer data for a given customer ID to the standard output.
     *
     * @param customerId The unique identifier of the customer whose data is to be
     *                   printed.
     */
    void printCustomer(int customerId);

    /**
     * Retrieves the customer data for a given customer ID using HTTPS protocol.
     *
     * @param customerId The unique identifier of the customer whose data is to be
     *                   retrieved.
     */
    void getCustomer_HTTPS(int customerId);
}
