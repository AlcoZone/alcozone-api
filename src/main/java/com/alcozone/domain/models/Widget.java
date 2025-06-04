package com.alcozone.domain.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Widget {
    private Integer id;
    private String uuid;
    private String name;
    private String description;
    private int minWidth;
    private int minHeight;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
