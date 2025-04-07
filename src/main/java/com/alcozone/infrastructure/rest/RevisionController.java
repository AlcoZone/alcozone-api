package com.alcozone.infrastructure.rest;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.jboss.resteasy.reactive.RestForm;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

@Path("/revision")
@Produces(MediaType.APPLICATION_JSON)
public class RevisionController {

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String saveRevision(@RestForm("name") String revisionName, @RestForm("file") InputStream file) throws IOException {

        String[] HEADERS = { "fecha_creacion", "hora_creacion", "tipo_incidente_c4", "incidente_c4", "tipo_entrada", "alcaldia_catalogo", "colonia_catalogo", "longitud", "latitud"};
        Reader reader = new InputStreamReader(file);
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        Iterable<CSVRecord> records = csvFormat.parse(reader);

        for (CSVRecord record : records) {
            System.out.println(record);
//            String author = record.get("author");
//            String title = record.get("title");
        }

        return revisionName;
    }
}
