package com.geektrust.backend.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Coordinates {
    private final Double x;
    private final Double y;
}
