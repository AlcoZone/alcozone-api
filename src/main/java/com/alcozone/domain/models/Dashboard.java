package com.alcozone.domain.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dashboard {
    private Integer id;
    private String uuid;
    private String userUuid;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
