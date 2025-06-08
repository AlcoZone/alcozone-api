package com.alcozone.domain.models.widgetdata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MonthlyAccidents {
    private String month_name;
    private String accidents;
}
