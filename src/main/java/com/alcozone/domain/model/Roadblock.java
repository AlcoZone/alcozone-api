package com.alcozone.domain.model;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class Roadblock {
    private double latitude;
    private double longitude;
    private int predictedCrashes;
    private int score;
}

