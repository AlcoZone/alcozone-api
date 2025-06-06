package com.alcozone.infrastructure.dto.widget;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AccidentsByReportSourceDTO {
    private String report_source;
    private Integer total_accidents;
}
