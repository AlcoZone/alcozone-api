package com.alcozone.domain;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Accident {
    private String uuid;
    private Date date;
    private String hour;
    private String town;
    private String neighbourhood;
    private String type;
    private String subType;
    private String reportedBy;
    private String latitude;
    private String longitude;
}
