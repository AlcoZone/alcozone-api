package com.alcozone.infrastructure.persistence.accident;

import com.alcozone.domain.models.Accident;

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
}
