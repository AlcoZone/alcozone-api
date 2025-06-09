package com.alcozone.infrastructure.persistence.minifiedCrash;

import com.alcozone.infrastructure.persistence.revision.RevisionEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Crashes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MinifiedCrashEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String datetime;
    private Double latitude;
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "revision_id", nullable = false)
    private RevisionEntity revisionEntity;
}
