package com.geektrust.backend.utils;

import com.geektrust.backend.dto.Coordinates;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class Utils {
    public static Double distanceCalculator(Coordinates p1, Coordinates p2) {
        double x1 = p1.getX();
        double y1 = p1.getY();

        double x2 = p2.getX();
        double y2 = p2.getY();

        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
