package com.alcozone.infrastructure.persistence.accident;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Accidents")
public class AccidentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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

    @CreationTimestamp
    private LocalDateTime created_at;
    @UpdateTimestamp
    private LocalDateTime updated_at;

    public AccidentEntity() {}

    public AccidentEntity(String uuid, String date, String hour, String type, String subType, String reportedBy, String town, String neighbourhood, Double latitude, Double longitude) {
        this.uuid = uuid;
        this.date = date;
        this.hour = hour;
        this.type = type;
        this.subType = subType;
        this.reportedBy = reportedBy;
        this.town = town;
        this.neighbourhood = neighbourhood;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
