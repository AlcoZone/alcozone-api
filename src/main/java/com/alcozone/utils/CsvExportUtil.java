package com.alcozone.utils;

import com.alcozone.domain.models.Crash;
import com.alcozone.infrastructure.dto.revision.response.DefaultRevisionResponseDTO;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.nio.charset.StandardCharsets;
import java.util.List;

@ApplicationScoped
public class CsvExportUtil {

    @Inject
    @ConfigProperty(name = "csv.headers")
    String csvHeaders;


    public byte[] revisionToCsv(DefaultRevisionResponseDTO revision) {
        StringBuilder sb = new StringBuilder();
        sb.append(csvHeaders).append("\n");

        List<Crash> crashes = revision.getCrashes().getData();

        for (Crash c : crashes) {
            String[] fechaHora = c.getDatetime() != null ? c.getDatetime().split(" ") : new String[]{"", ""};
            String fecha = fechaHora.length > 0 ? fechaHora[0] : "";
            String hora = fechaHora.length > 1 ? fechaHora[1] : "";

            sb.append(String.format(
                    "%s,%s,%s,%s,%s,%s,%s,%f,%f\n",
                    fecha,
                    hora,
                    c.getType(),
                    c.getSubType(),
                    c.getReportedBy(),
                    c.getTown(),
                    c.getNeighbourhood(),
                    c.getLatitude() != null ? c.getLatitude() : 0.0,
                    c.getLongitude() != null ? c.getLongitude() : 0.0
            ));
        }

        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }
}

