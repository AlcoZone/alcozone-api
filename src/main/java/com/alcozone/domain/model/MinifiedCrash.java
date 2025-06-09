package com.alcozone.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MinifiedCrash {
    private String datetime;
    private Double latitude;
    private Double longitude;
}
