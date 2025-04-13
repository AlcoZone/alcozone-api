package com.alcozone.application.dto.accident;

import lombok.Data;

import com.alcozone.domain.models.Accident;
import com.alcozone.infrastructure.persistence.revision.RevisionEntity;

@Data
public class CreateAccidentDTO {
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
    private RevisionEntity revisionEntity;

    public Accident toDomain() {
        Accident accident = new Accident();
        accident.setDate(date);
        accident.setHour(hour);
        accident.setType(type);
        accident.setSubType(subType);
        accident.setReportedBy(reportedBy);
        accident.setTown(town);
        accident.setNeighbourhood(neighbourhood);
        accident.setLatitude(latitude);
        accident.setLongitude(longitude);
        return accident;
    }
}
