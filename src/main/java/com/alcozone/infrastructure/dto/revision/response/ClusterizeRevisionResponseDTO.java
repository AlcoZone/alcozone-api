package com.alcozone.infrastructure.dto.revision.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.alcozone.domain.models.Cluster;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClusterizeRevisionResponseDTO {
    private Integer count;
    private List<Cluster> clusters;
}
