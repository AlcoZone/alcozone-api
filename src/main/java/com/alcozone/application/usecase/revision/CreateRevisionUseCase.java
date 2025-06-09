package com.alcozone.application.usecase.revision;

import java.io.Reader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.stream.StreamSupport;

import jakarta.inject.Named;
import org.apache.commons.csv.CSVFormat;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.alcozone.domain.model.Revision;
import com.alcozone.application.service.RevisionService;
import com.alcozone.infrastructure.persistence.crash.CrashMapper;
import com.alcozone.infrastructure.persistence.revision.RevisionEntity;
import com.alcozone.application.usecase.crash.CreateCrashUseCase;
import com.alcozone.infrastructure.dto.revision.request.CreateRevisionRequestDTO;

import org.apache.commons.csv.CSVRecord;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class CreateRevisionUseCase {

    private static final Logger log = LoggerFactory.getLogger(CreateRevisionUseCase.class);
    @ConfigProperty(name = "csv.headers")
    String[] headers;

    @Inject RevisionService revisionService;
    @Inject CreateCrashUseCase createCrashUseCase;

    @Inject
    @Named("customExecutor")
    ExecutorService executor;

    public void execute(CreateRevisionRequestDTO requestDTO) throws IOException {
        executor.submit(() -> {
            try {
                Revision revision = revisionService.saveRevision(requestDTO.getRevisionName());
                RevisionEntity revisionEntity = revisionService.getRevisionEntity(revision.getUuid());

                Reader reader = new InputStreamReader(requestDTO.getCsvFile());
                CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                        .setHeader(headers)
                        .setSkipHeaderRecord(true)
                        .build();

                Iterable<CSVRecord> records = csvFormat.parse(reader);
                StreamSupport.stream(records.spliterator(), true)
                    .map(record -> CrashMapper.toCreateCrashDTO(record, revisionEntity))
                    .forEach(createCrashUseCase::execute);

                revisionService.markAsCompleted(revision.getUuid());

            } catch (Exception e) {
                log.error("e: ", e);
            }
        });
}

}
