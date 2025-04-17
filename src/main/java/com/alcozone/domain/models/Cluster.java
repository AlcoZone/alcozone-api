package com.alcozone.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cluster {
    //TODO New Cluster should have: latitude, longitude, count
    private int id;
    private Double[] centroid;
}