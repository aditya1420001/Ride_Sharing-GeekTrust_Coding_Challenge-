package com.geektrust.backend.utils.fareCalculator;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public abstract class Fare {

    protected BigDecimal price;

}
