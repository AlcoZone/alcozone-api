package com.alcozone.infrastructure.dto.revision.response;

import lombok.Data;

@Data
public class RevisionListItemDTO {
    private String uuid;
    private String name;
    private int dataQuantity;
    private String date;
}

