package edu.bu.met.cs665.season;

import java.time.LocalDate;
import java.time.Month;

public class SeasonUtils {
    public static Season getCurrentSeason() {
        LocalDate currentDate = LocalDate.now();
        Month currentMonth = currentDate.getMonth();
        if (currentMonth == Month.DECEMBER || currentMonth == Month.JANUARY || currentMonth == Month.FEBRUARY) {
            return Season.Winter;
        } else if (currentMonth == Month.MARCH || currentMonth == Month.APRIL || currentMonth == Month.MAY) {
            return Season.Spring;
        } else if (currentMonth == Month.JUNE || currentMonth == Month.JULY || currentMonth == Month.AUGUST) {
            return Season.Summer;
        } else {
            return Season.Autumn;
        }
    }
}
