package com.alcozone.domain.models.widgetdata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class WidgetFilters {
    private String town;
    private String startDate;
    private String endDate;
}
