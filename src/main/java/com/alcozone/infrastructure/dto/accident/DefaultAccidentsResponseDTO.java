package com.alcozone.infrastructure.dto.accident;

import java.util.List;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.alcozone.domain.models.Accident;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultAccidentsResponseDTO {
    private Integer count;
    private List<Accident> data;
}
