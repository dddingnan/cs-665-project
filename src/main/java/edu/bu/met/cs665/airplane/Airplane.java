/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/21/2023
 * File Name: Airplane.java
 * Description: Defines the interface for an airplane. 
 * This interface includes methods to access
 * various airplane characteristics such as name, 
 * range, fuel capacity, burn rate, 
 * consumption, and speed.
 */
package edu.bu.met.cs665.airplane;

public interface Airplane {
    // Interface methods
    String getName();

    double getRange();

    double getFuelCapacity();

    double getFuelBurnRate();

    double getFuelConsumption();

    double getSpeed();
}
