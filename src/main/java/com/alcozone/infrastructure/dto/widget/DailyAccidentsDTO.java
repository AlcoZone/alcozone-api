package com.alcozone.infrastructure.dto.widget;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DailyAccidentsDTO {
    private String accident_date;
    private Integer total_accidents;
}
