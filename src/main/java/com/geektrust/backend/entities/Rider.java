package com.geektrust.backend.entities;

import com.geektrust.backend.dto.Coordinates;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rider {
    private String Id;
    private Coordinates location;
}
