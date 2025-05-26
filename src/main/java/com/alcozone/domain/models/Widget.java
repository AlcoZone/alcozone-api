package com.alcozone.domain.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Widget {
    private int id;
    private String uuid;
    private String dashboardUuid;
    private String name;
    private Integer gridPositionX;
    private Integer gridPositionY;
    private Integer gridWidth;
    private Integer gridHeight;
    private Integer minWidth;
    private Integer minHeight;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
