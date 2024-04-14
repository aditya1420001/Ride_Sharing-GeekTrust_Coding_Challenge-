package com.geektrust.backend.utils.fareCalculator.fareDecorator.calculations;

import com.geektrust.backend.utils.fareCalculator.Fare;
import com.geektrust.backend.utils.fareCalculator.fareDecorator.BaseFareDecorator;

import java.math.BigDecimal;

public class AddFarePerPM extends BaseFareDecorator {
    private static final BigDecimal farePerKm = BigDecimal.valueOf(6.5D);

    public AddFarePerPM(Fare fareToDecorate, Double totalKmsTravelled) {
        super(fareToDecorate);
        this.price = BigDecimal.valueOf(totalKmsTravelled).multiply(farePerKm);
    }


}
