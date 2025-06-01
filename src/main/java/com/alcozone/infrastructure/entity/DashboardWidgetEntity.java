package com.alcozone.infrastructure.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Dashboard_Widget")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardWidgetEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uuid", nullable = false, unique = true)
    private String uuid;

    @Column(name = "widget_id", nullable = false)
    private String widgetUuid;

    @Column(name = "dashboard_id", nullable = false)
    private String dashboardUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "widget_id",
            referencedColumnName = "uuid",
            insertable = false,
            updatable = false
    )
    private WidgetEntity widget;

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
}
