package com.alcozone.infrastructure.dto.accident;

import com.alcozone.domain.models.Accident;
import lombok.Data;

import java.util.List;

@Data
public class GetAccidentsDTO {
    private Integer amount;
    private List<Accident> data;
}
