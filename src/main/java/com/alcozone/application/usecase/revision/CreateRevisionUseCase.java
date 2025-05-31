package com.alcozone.application.usecase.revision;

import java.util.List;
import java.io.Reader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.StreamSupport;

import org.apache.commons.csv.CSVFormat;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.alcozone.domain.model.Crash;
import com.alcozone.domain.model.Revision;
import com.alcozone.application.service.RevisionService;
import com.alcozone.infrastructure.persistence.crash.CrashMapper;
import com.alcozone.infrastructure.persistence.revision.RevisionEntity;
import com.alcozone.application.usecase.crash.CreateCrashUseCase;
import com.alcozone.infrastructure.dto.revision.request.CreateRevisionRequestDTO;
import com.alcozone.infrastructure.dto.revision.response.DefaultRevisionResponseDTO;
import com.alcozone.infrastructure.dto.crash.DefaultCrashesResponseDTO;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class CreateRevisionUseCase {

    @ConfigProperty(name = "csv.headers")
    String[] headers;

    @Inject RevisionService revisionService;
    @Inject
    CreateCrashUseCase createCrashUseCase;

    public DefaultRevisionResponseDTO execute(CreateRevisionRequestDTO requestDTO) throws IOException {
        Revision revision = revisionService.saveRevision(requestDTO.getRevisionName());
        RevisionEntity revisionEntity = revisionService.getRevisionEntity(revision.getUuid());

        Reader reader = new InputStreamReader(requestDTO.getCsvFile());
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(headers)
                .setSkipHeaderRecord(true)
                .build();

        List<Crash> crashes = StreamSupport.stream(csvFormat.parse(reader).spliterator(), true)
            .map(record -> CrashMapper.toCreateCrashDTO(record, revisionEntity))
            .map(createCrashUseCase::execute)
            .toList();

        DefaultCrashesResponseDTO wrapperDTO = new DefaultCrashesResponseDTO();
        wrapperDTO.setCount(crashes.size());

        DefaultRevisionResponseDTO defaultRevisionResponseDTO = new DefaultRevisionResponseDTO();
        defaultRevisionResponseDTO.setUuid(revisionEntity.getUuid());
        defaultRevisionResponseDTO.setName(revisionEntity.getName());
        defaultRevisionResponseDTO.setCrashes(wrapperDTO);

        return defaultRevisionResponseDTO;
    }
}
