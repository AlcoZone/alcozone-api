package com.alcozone.infrastructure.dto.widget;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class DangerousTownMonthDTO {
    public String month_name;
    public String town;
    public int total_accidents;
}
