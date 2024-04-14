package com.geektrust.backend.utils.fareCalculator.fareDecorator.calculations;

import com.geektrust.backend.utils.fareCalculator.Fare;
import com.geektrust.backend.utils.fareCalculator.fareDecorator.BaseFareDecorator;

import java.math.BigDecimal;

public class AddFarePerMinute extends BaseFareDecorator {

    private static final BigDecimal farePerMinuteSpend = BigDecimal.valueOf(2L);

    public AddFarePerMinute(Fare fareToDecorate, Long rideDuration) {
        super(fareToDecorate);
        this.price = BigDecimal.valueOf(rideDuration).multiply(farePerMinuteSpend);
    }
}
