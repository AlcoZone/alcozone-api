package com.alcozone.infrastructure.persistence.revision;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "Revisions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RevisionListEntity {

    @Id
    @Column(name = "uuid")
    private String uuid;

    private String name;

    @Transient
    private int dataQuantity;

    @CreationTimestamp
    private LocalDateTime created_at;
}
