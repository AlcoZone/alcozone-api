package com.alcozone.domain.models.widgetdata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccidentsByReportSource {
    private String report_source;
    private Integer total_accidents;
}
