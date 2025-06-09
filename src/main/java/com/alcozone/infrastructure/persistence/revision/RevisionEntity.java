package com.alcozone.infrastructure.persistence.revision;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import jakarta.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.alcozone.infrastructure.persistence.crash.CrashEntity;

@Entity
@Table(name = "Revisions")
@Getter
@Setter
@NoArgsConstructor
public class RevisionEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String uuid;
    private String name;
    private boolean deleted;
    private String status;

    @OneToMany(mappedBy = "revisionEntity")
    private List<CrashEntity> crashes;

    @CreationTimestamp
    private LocalDateTime created_at;
    @UpdateTimestamp
    private LocalDateTime updated_at;

    public RevisionEntity(String uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }
}