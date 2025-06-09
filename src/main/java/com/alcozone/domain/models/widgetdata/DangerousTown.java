package com.alcozone.domain.models.widgetdata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DangerousTown {
    private String town;
    private String total_accidents;
}
