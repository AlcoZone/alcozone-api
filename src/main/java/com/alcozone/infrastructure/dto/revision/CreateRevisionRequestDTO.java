package com.alcozone.infrastructure.dto.revision;

import java.io.InputStream;

import lombok.Getter;

import org.jboss.resteasy.reactive.RestForm;

@Getter
public class CreateRevisionRequestDTO {
    @RestForm("name")
    private String revisionName;

    @RestForm("file")
    private InputStream csvFile;
}
