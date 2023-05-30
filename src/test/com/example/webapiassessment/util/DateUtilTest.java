package com.example.webapiassessment.util;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateUtilTest {

    @Test
    public void testGetCurrentMonthStart() {
        LocalDate currentDate = LocalDate.now();
        LocalDate startOfMonth = currentDate.withDayOfMonth(1);

        assertEquals("MAY", startOfMonth.getMonth().toString());
    }

    @Test
    public void testGetCurrentMonthEnd() {
        LocalDate currentDate = LocalDate.now();
        LocalDate twoMonthsAgo = currentDate.minusMonths(2);

        assertEquals("MARCH", twoMonthsAgo.getMonth().toString());
    }
}