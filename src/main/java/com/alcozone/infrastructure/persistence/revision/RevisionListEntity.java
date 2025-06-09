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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String uuid;
    private String name;
    private String status;

    @CreationTimestamp
    private LocalDateTime created_at;

    @Transient
    private int dataQuantity;
}
