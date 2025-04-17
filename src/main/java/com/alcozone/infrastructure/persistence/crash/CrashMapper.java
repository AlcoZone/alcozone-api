package com.alcozone.infrastructure.persistence.crash;

import com.alcozone.infrastructure.persistence.revision.RevisionEntity;
import org.apache.commons.csv.CSVRecord;

import com.alcozone.domain.models.Crash;
import com.alcozone.application.dto.crash.CreateCrashDTO;

public class CrashMapper {
        public static Crash toDomain(CrashEntity crashEntity) {
        return new Crash(
                crashEntity.getId(),
                crashEntity.getUuid(),
                crashEntity.getDate(),
                crashEntity.getHour(),
                crashEntity.getType(),
                crashEntity.getSubType(),
                crashEntity.getReportedBy(),
                crashEntity.getTown(),
                crashEntity.getNeighbourhood(),
                crashEntity.getLatitude(),
                crashEntity.getLongitude()
        );
    }

    public static CrashEntity toEntity(Crash crash) {
        return new CrashEntity(
                crash.getUuid(),
                crash.getDate(),
                crash.getHour(),
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
        dto.setDate(record.get("Fecha"));
        dto.setHour(record.get("Hora"));
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
