package com.alcozone.UnitTests;

import com.alcozone.application.usecase.prediction.GenerateRoadblockPredictionUseCase;
import com.alcozone.domain.model.Roadblock;
import com.alcozone.infrastructure.dto.prediction.PredictionRequestDTO;
import com.alcozone.infrastructure.persistence.crash.CrashEntity;
import com.alcozone.infrastructure.persistence.revision.RevisionEntity;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@QuarkusTest
public class GetPredictionTest {

    @Inject GenerateRoadblockPredictionUseCase generateRoadblockPredictionUseCase;

    public static String generateRandomDate(Date startDate, Date endDate) {
        long minTime = startDate.getTime();
        long maxTime = endDate.getTime();
        long randomTime = ThreadLocalRandom.current().nextLong(minTime, maxTime);
        Date randomDate = new Date(randomTime);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(randomDate);
    }

    private static final Random random = new Random();

    public static double[] generateRandomCoordinate() {
        double lat = 19.0 + (19.6 - 19.0) * random.nextDouble();
        double lng = -99.3 + (-98.9 + 99.3) * random.nextDouble();
        return new double[]{lat, lng};
    }

    @Test
    @Transactional
    public void testGetPredictData() throws ParseException {
        String uuid = UUID.randomUUID().toString();
        RevisionEntity revision = new RevisionEntity();
        revision.setUuid(uuid);
        revision.persist();
        RevisionEntity.getEntityManager().flush();

        SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Date start = parser.parse("01/01/2022 00:00:00");
        Date end = parser.parse("31/12/2022 23:59:59");

        for (int i = 0; i < 12345; i++) {
            double[] coord = generateRandomCoordinate();

            CrashEntity crash = new CrashEntity();
            crash.setUuid(UUID.randomUUID().toString());
            crash.setDatetime(generateRandomDate(start, end));
            crash.setType("Colisión");
            crash.setSubType("Choque con lesionados");
            crash.setReportedBy("LLAMADA 911");
            crash.setTown("Coyoacán");
            crash.setNeighbourhood("Campestre Churubusco");
            crash.setLatitude(coord[0]);
            crash.setLongitude(coord[1]);
            crash.setRevisionEntity(revision);
            crash.persist();
        }

        PredictionRequestDTO predictionRequestDTO = new PredictionRequestDTO();
        predictionRequestDTO.setRevision(uuid);

        Map<String, List<Roadblock>> prediction = generateRoadblockPredictionUseCase.execute(predictionRequestDTO);

        assert prediction.size() > 1;

        System.out.println(prediction);
    }

    @Test
    @Transactional
    public void GetPredictEmptyData() {
        try {
            String uuid = UUID.randomUUID().toString();
            PredictionRequestDTO predictionRequestDTO = new PredictionRequestDTO();
            predictionRequestDTO.setRevision(uuid);

            Map<String, List<Roadblock>> prediction = generateRoadblockPredictionUseCase.execute(predictionRequestDTO);

            assert prediction.size() > 1;

            System.out.println(prediction);
        } catch (Exception e) {
            System.out.println("Error Generating Prediction: Revision was empty/Doesn't Exist");
        }

    }
}

