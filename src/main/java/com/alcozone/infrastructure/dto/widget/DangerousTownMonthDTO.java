package com.alcozone.infrastructure.dto.widget;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DangerousTownMonthDTO {
    public String month_name;
    public String town;
    public int total_accidents;
}
