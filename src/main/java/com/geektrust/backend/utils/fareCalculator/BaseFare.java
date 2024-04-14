package com.geektrust.backend.utils.fareCalculator;

import java.math.BigDecimal;

public class BaseFare extends Fare {
    public BaseFare() {
        this.price = BigDecimal.valueOf(50.0D);
    }
}
