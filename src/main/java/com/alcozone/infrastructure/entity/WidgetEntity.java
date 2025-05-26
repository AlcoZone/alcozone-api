package com.alcozone.infrastructure.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Widgets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WidgetEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uuid", nullable = false, unique = true)
    private String uuid;

    @Column(name = "dashboard_id", nullable = false)
    private String dashboardUuid;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "grid_position_x", nullable = false)
    private Integer gridPositionX;

    @Column(name = "grid_position_y", nullable = false)
    private Integer gridPositionY;

    @Column(name = "grid_width", nullable = false)
    private Integer gridWidth;

    @Column(name = "grid_height", nullable = false)
    private Integer gridHeight;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "min_width")
    private Integer minWidth;

    @Column(name = "min_height")
    private Integer minHeight;
}
