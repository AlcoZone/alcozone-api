package com.alcozone.application.dto.widget;

import lombok.Data;

@Data
public class CreateWidgetDTO {
    private String name;
    private String description;
    private int minWidth;
    private int minHeight;
}
