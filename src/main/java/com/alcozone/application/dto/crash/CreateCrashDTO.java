package com.alcozone.application.dto.crash;

import lombok.Data;

import com.alcozone.domain.models.Crash;
import com.alcozone.infrastructure.persistence.revision.RevisionEntity;

@Data
public class CreateCrashDTO {
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

    public Crash toDomain() {
        Crash crash = new Crash();
        crash.setDate(date);
        crash.setHour(hour);
        crash.setType(type);
        crash.setSubType(subType);
        crash.setReportedBy(reportedBy);
        crash.setTown(town);
        crash.setNeighbourhood(neighbourhood);
        crash.setLatitude(latitude);
        crash.setLongitude(longitude);
        return crash;
    }
}
