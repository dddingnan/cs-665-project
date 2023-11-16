/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/06/2023
 * File Name: FileLoader.java
 * Description: The FileLoader class provides utility functions to read customer data from CSV files.
 * This method is loading data from a provided file and returning a list of corresponding objects.
 */

package edu.bu.met.cs665.loader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.bu.met.cs665.exception.InvalidDataException;
import edu.bu.met.cs665.common.CustomerData;

public class FileLoader {

    public String line = "";
    public String splitBy = ";";

    /**
     * Loads email templates data from a given CSV file.
     * The CSV file format: "<customer_id>;<customer_name>".
     *
     * @param fileName Name of the file to be read.
     * @return A list of Customer objects.
     * @throws FileNotFoundException If the file does not exist.
     * @throws IOException           If an error occurs while reading the file.
     * @throws InvalidDataException  If data in the file is invalid.
     */
    public List<CustomerData> loadCustomer(String fileName) throws InvalidDataException {
        List<CustomerData> customer = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(splitBy);

                if (data.length != 2) {
                    System.out.println("Invalid data format: " + line);
                    continue;
                }
                int customerId = Integer.parseInt(data[0].trim());
                String customerName = data[1].trim();

                customer.add(new CustomerData(customerId, customerName));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            return new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return new ArrayList<>();
        }

        return customer;
    }

}