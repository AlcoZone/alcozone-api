package com.alcozone.infrastructure.persistence.crash;

import java.time.LocalDateTime;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import jakarta.persistence.*;

import lombok.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.alcozone.infrastructure.persistence.revision.RevisionEntity;

@Entity
@Table(name = "Crashes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrashEntity extends PanacheEntityBase{
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

    @ManyToOne
    @JoinColumn(name = "revision_id", nullable = false)
    private RevisionEntity revisionEntity;

    @CreationTimestamp
    private LocalDateTime created_at;
    @UpdateTimestamp
    private LocalDateTime updated_at;

    public CrashEntity(String uuid, String date, String hour, String type, String subType, String reportedBy, String town, String neighbourhood, Double latitude, Double longitude) {
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
