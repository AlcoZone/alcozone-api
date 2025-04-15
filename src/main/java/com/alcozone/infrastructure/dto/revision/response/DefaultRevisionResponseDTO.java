package com.alcozone.infrastructure.dto.revision.response;

import lombok.Data;

import com.alcozone.infrastructure.dto.accident.DefaultAccidentsResponseDTO;

@Data
public class DefaultRevisionResponseDTO {
    private String uuid;
    private String name;
    private DefaultAccidentsResponseDTO accidents;
}
