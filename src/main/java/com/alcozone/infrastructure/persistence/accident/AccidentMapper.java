package com.alcozone.infrastructure.persistence.accident;

import com.alcozone.infrastructure.persistence.revision.RevisionEntity;
import org.apache.commons.csv.CSVRecord;

import com.alcozone.domain.models.Accident;
import com.alcozone.application.dto.accident.CreateAccidentDTO;

public class AccidentMapper {
        public static Accident toDomain(AccidentEntity accidentEntity) {
        return new Accident(
                accidentEntity.getId(),
                accidentEntity.getUuid(),
                accidentEntity.getDate(),
                accidentEntity.getHour(),
                accidentEntity.getType(),
                accidentEntity.getSubType(),
                accidentEntity.getReportedBy(),
                accidentEntity.getTown(),
                accidentEntity.getNeighbourhood(),
                accidentEntity.getLatitude(),
                accidentEntity.getLongitude()
        );
    }

    public static AccidentEntity toEntity(Accident accident) {
        return new AccidentEntity(
                accident.getUuid(),
                accident.getDate(),
                accident.getHour(),
                accident.getType(),
                accident.getSubType(),
                accident.getReportedBy(),
                accident.getTown(),
                accident.getNeighbourhood(),
                accident.getLatitude(),
                accident.getLongitude()
        );
    }

    public static CreateAccidentDTO toCreateAccidentDTO(CSVRecord record, RevisionEntity revisionEntity) {
        CreateAccidentDTO dto = new CreateAccidentDTO();
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
