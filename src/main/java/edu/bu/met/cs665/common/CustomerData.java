/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/06/2023
 * File Name: CustomerData.java
 * Description: This interface declares the operations that can be performed on customer
 * data.
 */
package edu.bu.met.cs665.common;

public class CustomerData {
    private int customerId;
    private String customerName;

    /**
     * Constructs an CustomerData with a specified customer id and name.
     * 
     * @param customerId
     * @param customerName
     */
    public CustomerData(int customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
    }

    /**
     * Retrieves the customer id.
     * 
     * @return customerId type as a int.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Retrieves the name of this customer.
     * 
     * @return customerName as a string.
     */
    public String getCustomerName() {
        return customerName;
    }
}
