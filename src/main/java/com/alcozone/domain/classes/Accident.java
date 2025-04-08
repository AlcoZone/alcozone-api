package com.alcozone.domain.classes;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Accident {
    private String uuid;
    private String date;
    private String hour;
    private String type;
    private String subType;
    private String reportedBy;
    private String town;
    private String neighbourhood;
    private Double latitude;
    private Double longitude;
}
