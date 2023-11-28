package edu.bu.met.cs665.season;

import org.junit.Test;
import java.time.LocalDate;
import java.time.Month;
import static org.junit.Assert.*;

public class SeasonUtilsTest {
    @Test
    public void testGetCurrentSeason() {
        LocalDate currentDate = LocalDate.now();
        Month currentMonth = currentDate.getMonth();
        Season expectedSeason;

        if (currentMonth == Month.DECEMBER || currentMonth == Month.JANUARY || currentMonth == Month.FEBRUARY) {
            expectedSeason = Season.WINTER;
        } else if (currentMonth == Month.MARCH || currentMonth == Month.APRIL || currentMonth == Month.MAY) {
            expectedSeason = Season.SPRING;
        } else if (currentMonth == Month.JUNE || currentMonth == Month.JULY || currentMonth == Month.AUGUST) {
            expectedSeason = Season.SUMMER;
        } else {
            expectedSeason = Season.AUTUMN;
        }

        Season actualSeason = SeasonUtils.getCurrentSeason();
        assertEquals(expectedSeason, actualSeason);
    }
}
