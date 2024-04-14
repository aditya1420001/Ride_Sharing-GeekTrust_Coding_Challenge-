package com.geektrust.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Match {

    private static final String NO_DRIVERS_AVAILABLE = "NO_DRIVERS_AVAILABLE";
    private static final String DRIVERS_MATCHED = "DRIVERS_MATCHED ";

    private final List<String> driverIds;

    public List<String> getDriverIds() {
        return driverIds;
    }

    @Override
    public String toString() {
        if (driverIds.size() == 0) {
            return NO_DRIVERS_AVAILABLE;
        }

        return DRIVERS_MATCHED + String.join(" ", driverIds);
    }


}
