package com.alcozone.infrastructure.persistence.minifiedRevision;


import com.alcozone.infrastructure.persistence.minifiedCrash.MinifiedCrashEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "Revisions")
@Getter
@Setter
@NoArgsConstructor
public class MinifiedRevisionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String uuid;
    private LocalDateTime created_at;

    @OneToMany(mappedBy = "revisionEntity")
    private List<MinifiedCrashEntity> crashes;
}
