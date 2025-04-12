package com.alcozone.application.usecase.revision;

import com.alcozone.application.service.AccidentService;
import com.alcozone.application.service.RevisionService;
import com.alcozone.infrastructure.dto.revision.CreatedRevisionResultDTO;
import com.alcozone.infrastructure.persistence.revision.RevisionEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

@ApplicationScoped
public class CreateRevisionUseCase {
    //TODO Migrate HEADERS into other part

    @Inject
    RevisionService revisionService;
    @Inject
    AccidentService accidentService;

    public CreatedRevisionResultDTO execute(String name, InputStream csvFile) throws IOException {
        RevisionEntity revisionEntity = revisionService.saveRevision(name);

        String[] HEADERS = {"Fecha", "Hora", "Tipo", "SubTipo", "Reportado Por", "Alcaldia", "Colonia", "Latitud", "Longitud"};
        Reader reader = new InputStreamReader(csvFile);
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        Integer accidents = 0;
        Iterable<CSVRecord> records = csvFormat.parse(reader);
        for (CSVRecord record : records) {
            accidentService.saveAccident(
                    revisionEntity,
                    record.get("Fecha"),
                    record.get("Hora"),
                    record.get("Tipo"),
                    record.get("SubTipo"),
                    record.get("Reportado Por"),
                    record.get("Alcaldia"),
                    record.get("Colonia"),
                    Double.parseDouble(record.get("Latitud")),
                    Double.parseDouble(record.get("Longitud"))
            );
            accidents++;
        }

        CreatedRevisionResultDTO createdRevisionResultDTO = new CreatedRevisionResultDTO();
        createdRevisionResultDTO.setUuid(revisionEntity.getUuid());
        createdRevisionResultDTO.setName(revisionEntity.getName());
        createdRevisionResultDTO.setAccidents(accidents);

        return createdRevisionResultDTO;
    }
}
