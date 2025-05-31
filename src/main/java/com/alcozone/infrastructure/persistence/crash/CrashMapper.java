package com.alcozone.infrastructure.persistence.crash;

import com.alcozone.domain.model.MinifiedCrash;
import com.alcozone.infrastructure.persistence.minifiedCrash.MinifiedCrashEntity;
import com.alcozone.infrastructure.persistence.revision.RevisionEntity;
import org.apache.commons.csv.CSVRecord;

import com.alcozone.domain.model.Crash;
import com.alcozone.application.dto.crash.CreateCrashDTO;

public class CrashMapper {
    public static Crash toDomain(CrashEntity crashEntity) {
        return new Crash(
                crashEntity.getId(),
                crashEntity.getUuid(),
                crashEntity.getDatetime(),
                crashEntity.getType(),
                crashEntity.getSubType(),
                crashEntity.getReportedBy(),
                crashEntity.getTown(),
                crashEntity.getNeighbourhood(),
                crashEntity.getLatitude(),
                crashEntity.getLongitude()
        );
    }

    public static MinifiedCrash toMinifiedDomain(MinifiedCrashEntity minifiedEntity) {
        return new MinifiedCrash(
                minifiedEntity.getDatetime(),
                minifiedEntity.getLatitude(),
                minifiedEntity.getLongitude()
        );
    }

    public static CrashEntity toEntity(Crash crash) {
        return new CrashEntity(
                crash.getUuid(),
                crash.getDatetime(),
                crash.getType(),
                crash.getSubType(),
                crash.getReportedBy(),
                crash.getTown(),
                crash.getNeighbourhood(),
                crash.getLatitude(),
                crash.getLongitude()
        );
    }

    public static CreateCrashDTO toCreateCrashDTO(CSVRecord record, RevisionEntity revisionEntity) {
        CreateCrashDTO dto = new CreateCrashDTO();
        dto.setDatetime(record.get("Fecha") + " " + record.get("Hora"));
        dto.setType(record.get("Tipo"));
        dto.setSubType(record.get("SubTipo"));
        dto.setReportedBy(record.get("Reportado Por"));
        dto.setTown(record.get("Alcaldia"));
        dto.setNeighbourhood(record.get("Colonia"));
        dto.setLatitude(Double.parseDouble(record.get("Latitud")));
        dto.setLongitude(Double.parseDouble(record.get("Longitud")));
        dto.setRevisionEntity(revisionEntity);
        return dto;
    }
}
