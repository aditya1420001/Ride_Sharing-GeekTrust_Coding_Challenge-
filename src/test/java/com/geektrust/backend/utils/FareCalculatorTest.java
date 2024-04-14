package com.geektrust.backend.utils;

import com.geektrust.backend.utils.fareCalculator.BaseFare;
import com.geektrust.backend.utils.fareCalculator.Fare;
import com.geektrust.backend.utils.fareCalculator.fareDecorator.calculations.AddFarePerMinute;
import com.geektrust.backend.utils.fareCalculator.fareDecorator.calculations.AddFarePerPM;
import com.geektrust.backend.utils.fareCalculator.fareDecorator.calculations.AddServiceTax;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

@DisplayName("FareCalculatorTest")
public class FareCalculatorTest {

    @Test
    @DisplayName("Testing the decorator pattern | adding up the costs sequentially")
    public void fareCalculation_FareIncrementalAddUp_DecoratorPatternTest() {

        // Base Fare
        Fare fare = new BaseFare();

        // Adding up the fare per KM travelled
        fare = new AddFarePerPM(fare, 3.0D);

        // Adding up the fare based on ride spend in minutes
        fare = new AddFarePerMinute(fare, Long.valueOf("50"));

        // Adding up the service tax (20%)
        fare = new AddServiceTax(fare);

        // Assert
        Assertions.assertEquals(BigDecimal.valueOf(203.4000D).setScale(2, RoundingMode.HALF_EVEN), fare.getPrice().setScale(2, RoundingMode.HALF_EVEN));
    }

}
