package com.alcozone.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class MinifiedRevision {
    private String uuid;
    private List<MinifiedCrash> crashes;
}
