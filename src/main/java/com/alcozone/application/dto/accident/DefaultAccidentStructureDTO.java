package com.alcozone.application.dto.accident;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultAccidentStructureDTO {
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
