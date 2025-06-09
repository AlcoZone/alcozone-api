package com.alcozone.infrastructure.dto.widget;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class MonthlyAccidentsDTO {
    private String month_name;
    private String accidents;
}
