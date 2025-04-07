package com.alcozone.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Revision {
    private String uuid;
    private String name;
    private List<Accident> accidents;
}
