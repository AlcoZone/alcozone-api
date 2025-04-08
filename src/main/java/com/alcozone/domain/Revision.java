package com.alcozone.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Revision {
    private String uuid;
    private String name;
    private String status;
}
