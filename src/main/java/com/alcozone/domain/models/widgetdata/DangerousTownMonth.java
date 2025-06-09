package com.alcozone.domain.models.widgetdata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DangerousTownMonth {
    private String month_name;
    private String town;
    private int total_accidents;
}
