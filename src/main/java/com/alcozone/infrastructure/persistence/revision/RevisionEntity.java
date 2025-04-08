package com.alcozone.infrastructure.persistence.revision;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Revisions")
public class RevisionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String uuid;
    private String name;
    private String status;
    @CreationTimestamp
    private LocalDateTime created_at;
    @UpdateTimestamp
    private LocalDateTime updated_at;

    public RevisionEntity() {}

    public RevisionEntity(String uuid, String name, String status) {
        this.uuid = uuid;
        this.name = name;
        this.status = status;
    }
}