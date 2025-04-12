package com.alcozone.infrastructure.persistence.revision;

import com.alcozone.infrastructure.persistence.accident.AccidentEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Revisions")
@Getter
@Setter
public class RevisionEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String uuid;
    private String name;

    @OneToMany(mappedBy = "revisionEntity")
    private List<AccidentEntity> accidents;

    @CreationTimestamp
    private LocalDateTime created_at;
    @UpdateTimestamp
    private LocalDateTime updated_at;

    public RevisionEntity() {}

    public RevisionEntity(String uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }
}