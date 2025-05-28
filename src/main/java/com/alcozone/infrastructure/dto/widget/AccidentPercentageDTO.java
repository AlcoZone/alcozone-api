package com.alcozone.infrastructure.dto.widget;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccidentPercentageDTO {
    public Double percentage;
    public String subType;

    public void subType(String s) {
    }

    public void percentage(double v) {
    }
}
