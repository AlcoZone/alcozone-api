package com.alcozone.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cluster {
    private Double latitude;
    private Double longitude;
    private int count;
}