/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/06/2023
 * File Name: Main.java
 * Description: This is the main entry point for a system designed to demonstrate
 * the integration of a legacy USB-based customer data retrieval system with a 
 * new HTTPS-based system.
 */
package edu.bu.met.cs665;

import java.util.List;

import edu.bu.met.cs665.exception.InvalidDataException;
import edu.bu.met.cs665.legacy.LegacyCustomerData_USB;
import edu.bu.met.cs665.loader.FileLoader;
import edu.bu.met.cs665.new_system.CustomerData_HTTPS;
import edu.bu.met.cs665.new_system.NewCustomerData_HTTPS;
import edu.bu.met.cs665.adapters.CustomerDataUSBAdapter;
import edu.bu.met.cs665.common.CustomerData;

public class Main {

  /**
   * Entry point method for the application. This method initializes the system
   * by:
   * 1. Loading customer data from a CSV file.
   * 2. Interacting with a legacy USB-based customer data system.
   * 3. Interacting with a new HTTPS-based customer data system.
   * 4. Using an adapter to make the legacy system compatible with the
   * interface of the new system.
   * 
   * @param args The command line arguments.
   * @throws InvalidDataException If there's an issue loading data.
   * @throws InterruptedException If there's an interrupted exception.
   */
  public static void main(String[] args) throws InvalidDataException, InterruptedException {

    FileLoader loader = new FileLoader();
    List<CustomerData> customers = loader.loadCustomer("src/data/customer.csv");
    System.out.println("---------------------------");

    LegacyCustomerData_USB legacySystem = new LegacyCustomerData_USB(customers);
    NewCustomerData_HTTPS newSystem = new NewCustomerData_HTTPS(customers);

    int customerId = 1; // Example customer ID.
    legacySystem.printCustomer(customerId);
    legacySystem.getCustomer_USB(customerId);

    System.out.println("---------------------------");

    newSystem.printCustomer(customerId);
    newSystem.getCustomer_HTTPS(customerId);

    System.out.println("---------------------------");
    // Use the adapter to interact with the legacy system as if it were the new
    // HTTPS system
    CustomerData_HTTPS adapter = new CustomerDataUSBAdapter(legacySystem);
    adapter.printCustomer(customerId);
    adapter.getCustomer_HTTPS(customerId);
  }
}
