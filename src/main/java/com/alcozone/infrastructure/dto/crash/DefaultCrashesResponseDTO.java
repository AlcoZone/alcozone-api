package com.alcozone.infrastructure.dto.crash;

import java.util.List;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.alcozone.domain.model.Crash;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultCrashesResponseDTO {
    private Integer count;
    private List<Crash> data;
}
