package com.alcozone.infrastructure.dto.crash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrashDTO {
    private int id;
    private String uuid;
    private String datetime;
    private String type;
    private String subType;
    private String reportedBy;
    private String town;
    private String neighbourhood;
    private Double latitude;
    private Double longitude;
}
