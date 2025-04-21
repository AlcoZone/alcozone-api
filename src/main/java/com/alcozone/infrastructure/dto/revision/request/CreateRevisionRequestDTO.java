package com.alcozone.infrastructure.dto.revision.request;

import java.io.InputStream;

import lombok.Getter;
import lombok.Setter;

import org.jboss.resteasy.reactive.RestForm;

@Getter
@Setter
public class CreateRevisionRequestDTO {
    @RestForm("name")
    private String revisionName;

    @RestForm("file")
    private InputStream csvFile;
}
