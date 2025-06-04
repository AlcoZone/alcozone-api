package com.alcozone.infrastructure.repository;

import com.alcozone.domain.models.Widget;
import com.alcozone.domain.repository.WidgetRepository;
import com.alcozone.infrastructure.entity.WidgetEntity;
import com.alcozone.infrastructure.mapper.WidgetMapper;
import com.alcozone.infrastructure.dto.widget.*;
import com.alcozone.infrastructure.persistence.crash.CrashEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class WidgetRepositoryImpl implements WidgetRepository, PanacheRepositoryBase<CrashEntity, Integer> {

    @Override
    public List<Widget> findAllWidgets() {
        return WidgetEntity.listAll().stream()
                .map(e -> WidgetMapper.toDomain((WidgetEntity) e))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Widget save(Widget widget) {
        WidgetEntity entity = WidgetMapper.toEntity(widget);

        if (entity.getId() == null) {
            entity.persist();
        } else {
            entity = WidgetEntity.getEntityManager().merge(entity);
        }

        return WidgetMapper.toDomain(entity);
    }

    @Override
    @Transactional
    public void deleteByUuid(String uuid) {
        WidgetEntity.delete("uuid", uuid);
    }

    @Inject
    EntityManager em;

    //metodo para sacar el porcentaje de accidentes
    public List<AccidentPercentageDTO> getAccidentsPercentage() {
        String sql = """
        SELECT
          subType,
          ROUND(100.0 * COUNT(*) / (SELECT COUNT(*) FROM crashes), 2) AS percentage
        FROM crashes
        WHERE subType IN ('Atropellado', 'Choque con lesionados', 'Motociclista')
        GROUP BY subType
        ORDER BY percentage DESC
    """;

        List<AccidentPercentageDTO> result = new ArrayList<>();

        try {
            @SuppressWarnings("unchecked")
            List<Object[]> resultQuery = em.createNativeQuery(sql).getResultList();

            for (Object[] row : resultQuery) {
                AccidentPercentageDTO dto = new AccidentPercentageDTO();
                dto.setSubType((String) row[0]);
                dto.setPercentage(((Number) row[1]).doubleValue());
                result.add(dto);
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }

        return result;
    }

    //metodo para sacar el número de accidentes

    public List<AccidentNumberDTO> getAccidentsNumber() {
        String sql = """
        SELECT
          subType,
          COUNT(*) AS accidentCount
        FROM crashes
        WHERE subType IN ('Choque con lesionados', 'Motociclista', 'Ciclista', 'Atropellado')
        GROUP BY subType
        ORDER BY accidentCount DESC
    """;

        List<AccidentNumberDTO> result = new ArrayList<>();

        try {
            @SuppressWarnings("unchecked")
            List<Object[]> resultQuery = em.createNativeQuery(sql).getResultList();

            for (Object[] row : resultQuery) {
                AccidentNumberDTO dto = new AccidentNumberDTO();
                dto.setSubType((String) row[0]);
                dto.setAccidentCount(Integer.valueOf(row[1].toString()));
                result.add(dto);
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }

        return result;
    }


    //metodo para sacar la delegacion peligrosa


    public List<DangerousTownDTO> getDangerousTown() {
        String sql = """
        SELECT
          town,
          COUNT(*) AS total_accidents
        FROM alcozone.crashes
        WHERE town IS NOT NULL AND town != ''
        GROUP BY town
        ORDER BY total_accidents DESC
        LIMIT 2
    """;

        List<DangerousTownDTO> result = new ArrayList<>();

        try {
            @SuppressWarnings("unchecked")
            List<Object[]> resultQuery = em.createNativeQuery(sql).getResultList();

            for (Object[] row : resultQuery) {
                DangerousTownDTO dto = new DangerousTownDTO();
                dto.town = (String) row[0];
                dto.total_accidents = String.valueOf(row[1]);
                result.add(dto);
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }

        return result;
    }

    //metodo para obtener los accidentes por mes
    public List<MonthlyAccidentsDTO> getMonthlyAccident() {
        String sql = """
        SELECT
          MONTHNAME(STR_TO_DATE(datetime, '%d/%m/%Y %H:%i:%s')) AS month_name,
          COUNT(*) AS accidents
        FROM alcozone.crashes
        WHERE MONTH(STR_TO_DATE(datetime, '%d/%m/%Y %H:%i:%s')) BETWEEN 1 AND 6
        GROUP BY MONTH(STR_TO_DATE(datetime, '%d/%m/%Y %H:%i:%s')), MONTHNAME(STR_TO_DATE(datetime, '%d/%m/%Y %H:%i:%s'))
        ORDER BY MONTH(STR_TO_DATE(datetime, '%d/%m/%Y %H:%i:%s'))
    """;

        List<MonthlyAccidentsDTO> result = new ArrayList<>();

        try {
            @SuppressWarnings("unchecked")
            List<Object[]> resultQuery = em.createNativeQuery(sql).getResultList();

            for (Object[] row : resultQuery) {
                MonthlyAccidentsDTO dto = new MonthlyAccidentsDTO();
                dto.setMonth_name((String) row[0]);
                dto.setAccidents(row[1].toString());
                result.add(dto);
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }

        return result;
    }

    //metodo para obtener las delegaciones mas peligrosas por cada mes
    public List<DangerousTownMonthDTO> getDangerousTownMonth() {
        String sql = """
        SELECT 
          month_name,
          town,
          total_accidents
        FROM (
          SELECT 
            MONTHNAME(STR_TO_DATE(datetime, '%d/%m/%Y %H:%i:%s')) AS month_name,
            MONTH(STR_TO_DATE(datetime, '%d/%m/%Y %H:%i:%s')) AS month_number,
            town,
            COUNT(*) AS total_accidents
          FROM alcozone.crashes
          WHERE town IS NOT NULL AND town != ''
          GROUP BY month_number, month_name, town
        ) AS sub
        WHERE (
            SELECT COUNT(*) 
            FROM (
              SELECT 
                town,
                COUNT(*) AS total
              FROM alcozone.crashes
              WHERE 
                MONTH(STR_TO_DATE(datetime, '%d/%m/%Y %H:%i:%s')) = sub.month_number AND
                town IS NOT NULL AND town != ''
              GROUP BY town
              HAVING COUNT(*) > sub.total_accidents
            ) AS top_towns
        ) < 2
        ORDER BY month_number, total_accidents DESC;
    """;

        List<DangerousTownMonthDTO> result = new ArrayList<>();

        try {
            @SuppressWarnings("unchecked")
            List<Object[]> resultQuery = em.createNativeQuery(sql).getResultList();

            for (Object[] row : resultQuery) {
                DangerousTownMonthDTO dto = new DangerousTownMonthDTO();
                dto.setMonth_name((String) row[0]);
                dto.setTown((String) row[1]);
                dto.setTotal_accidents(Integer.parseInt(row[2].toString()));
                result.add(dto);
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }

        return result;
    }



}


