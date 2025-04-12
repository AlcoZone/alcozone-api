package com.alcozone.infrastructure.dto.revision;

import lombok.Data;

@Data
public class CreatedRevisionResultDTO {
    private String uuid;
    private String name;
    private Integer accidents;
}
