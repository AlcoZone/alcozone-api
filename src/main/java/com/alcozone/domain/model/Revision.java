package com.alcozone.domain.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Revision {
    private int id;
    private String uuid;
    private String name;

    private List<Crash> crashes;
}
