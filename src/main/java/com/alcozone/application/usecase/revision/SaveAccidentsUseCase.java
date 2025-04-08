package com.alcozone.application.usecase.revision;

import com.alcozone.domain.Revision;

import java.io.IOException;
import java.io.InputStream;
//
//public class SaveAccidentsUseCase {
//        public void execute(InputStream csvFile) throws IOException {
//        return revisionService.createRevision(name, csvFile);
//    }
//}



//        String[] HEADERS = { "Fecha", "Hora", "Tipo", "SubTipo", "Reportado Por", "Alcaldia", "Colonia", "Latitud", "Longitud" };
//        Reader reader = new InputStreamReader(csvFile);
//        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
//                .setHeader(HEADERS)
//                .setSkipHeaderRecord(true)
//                .build();
//
//        Iterable<CSVRecord> records = csvFormat.parse(reader);

//        for (CSVRecord record : records) {
//            revision.addAccident(accidentService.createAccident(
//                    record.get("Fecha"),
//                    record.get("Hora"),
//                    record.get("Tipo"),
//                    record.get("SubTipo"),
//                    record.get("Reportado Por"),
//                    record.get("Alcaldia"),
//                    record.get("Colonia"),
//                    Double.parseDouble(record.get("Latitud")),
//                    Double.parseDouble(record.get("Longitud"))
//            ));
//        }