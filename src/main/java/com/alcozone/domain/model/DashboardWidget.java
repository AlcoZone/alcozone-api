package com.alcozone.domain.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardWidget {
    private int id;
    private String uuid;
    private String dashboardUuid;
    private String widgetUuid;
    private String name;
    private int gridPositionX;
    private int gridPositionY;
    private int gridWidth;
    private int gridHeight;
    private int minWidth;
    private int minHeight;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
