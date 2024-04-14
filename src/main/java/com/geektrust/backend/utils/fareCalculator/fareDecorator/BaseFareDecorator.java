package com.geektrust.backend.utils.fareCalculator.fareDecorator;

import com.geektrust.backend.utils.fareCalculator.Fare;

import java.math.BigDecimal;

public class BaseFareDecorator extends BaseDecorator {
    protected final Fare fare;

    public BaseFareDecorator(Fare fareToDecorate) {
        this.fare = fareToDecorate;
    }

    @Override
    public BigDecimal getPrice() {
        return this.fare.getPrice().add(this.price);
    }
}
