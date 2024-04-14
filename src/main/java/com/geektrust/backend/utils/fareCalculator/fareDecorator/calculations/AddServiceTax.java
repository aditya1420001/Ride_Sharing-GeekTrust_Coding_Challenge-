package com.geektrust.backend.utils.fareCalculator.fareDecorator.calculations;

import com.geektrust.backend.utils.fareCalculator.Fare;
import com.geektrust.backend.utils.fareCalculator.fareDecorator.BaseFareDecorator;

import java.math.BigDecimal;

public class AddServiceTax extends BaseFareDecorator {

    private static final BigDecimal SERVICE_TAX = BigDecimal.valueOf(20L);

    public AddServiceTax(Fare fareToDecorate) {
        super(fareToDecorate);
        this.price = fareToDecorate.getPrice().multiply(SERVICE_TAX).multiply(BigDecimal.valueOf(0.01D));
    }
}
