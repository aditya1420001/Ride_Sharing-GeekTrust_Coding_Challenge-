package com.geektrust.backend.utils;

import com.geektrust.backend.dto.Coordinates;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("UtilsTest")
public class UtilsTest {

    @Test
    @DisplayName("matching when driver is greater than 5km radius of rider")
    public void distanceCalculationTest() {

        // stub
        Coordinates rider = new Coordinates(0.0D, 0.0D);
        Coordinates driver = new Coordinates(3.0D, 4.0D);

        // expected
        Double expectedResult = 5.0D;

        // actual
        Double actualResult = Utils.distanceCalculator(rider, driver);

        // assert
        assertNotNull(actualResult);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void distanceCalculationTest1() {

        // stub
        Coordinates rider = new Coordinates(0.0D, 0.0D);
        Coordinates driver = new Coordinates(9.0D, 5.0D);

        // expected
        Double expectedResult = 10.295630140987D;

        // actual
        Double actualResult = Utils.distanceCalculator(rider, driver);

        // assert
        assertNotNull(actualResult);
        assertEquals(expectedResult, actualResult);
    }


}
