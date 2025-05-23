package com.alcozone.infrastructure.dto.revision.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevisionListItemDTO {
    private String uuid;
    private String name;
    private int dataQuantity;
    private String date;
}

