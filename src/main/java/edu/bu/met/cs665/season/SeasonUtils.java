/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/21/2023
 * File Name: SeasonUtils.java
 * Description: Provides utility functions related to seasons.
 * This class contains methods to determine the current season based on the date.
 */
package edu.bu.met.cs665.season;

import java.time.LocalDate;
import java.time.Month;

public class SeasonUtils {
    /**
     * Determines the current season based on the current date.
     * The year is divided into four seasons: Winter, Spring, Summer, and Autumn.
     * This method uses the current month to determine the corresponding season.
     * 
     * @return The current season as a {@link Season} enum value.
     */
    public static Season getCurrentSeason() {
        LocalDate currentDate = LocalDate.now();
        Month currentMonth = currentDate.getMonth();
        if (currentMonth == Month.DECEMBER || currentMonth == Month.JANUARY || currentMonth == Month.FEBRUARY) {
            return Season.WINTER;
        } else if (currentMonth == Month.MARCH || currentMonth == Month.APRIL || currentMonth == Month.MAY) {
            return Season.SPRING;
        } else if (currentMonth == Month.JUNE || currentMonth == Month.JULY || currentMonth == Month.AUGUST) {
            return Season.SUMMER;
        } else {
            return Season.AUTUMN;
        }
    }
}
