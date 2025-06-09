package com.alcozone.infrastructure.dto.widget;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

public class AccidentNumberDTO {
    private String subType;
    private Integer accidentCount;
}

