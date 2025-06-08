package com.alcozone.infrastructure.dto.revision.response;

import lombok.Data;

import com.alcozone.infrastructure.dto.crash.DefaultCrashesResponseDTO;

@Data
public class DefaultRevisionResponseDTO {
    private String uuid;
    private String name;
    private DefaultCrashesResponseDTO crashes;
    private boolean deleted;
}
