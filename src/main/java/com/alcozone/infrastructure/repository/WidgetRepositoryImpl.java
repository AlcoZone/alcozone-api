package com.alcozone.infrastructure.repository;

import com.alcozone.application.dto.widget.WidgetFiltersDTO;
import com.alcozone.domain.models.Widget;
import com.alcozone.domain.repository.WidgetRepository;
import com.alcozone.infrastructure.entity.WidgetEntity;
import com.alcozone.infrastructure.mapper.WidgetMapper;
import com.alcozone.infrastructure.dto.widget.*;
import com.alcozone.infrastructure.persistence.crash.CrashEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;

import java.util.*;
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
    public List<AccidentPercentageDTO> getAccidentsPercentage(WidgetFiltersDTO filters) {
        String sql = """
        SELECT
          subType,
          ROUND(100.0 * COUNT(*) / (SELECT COUNT(*) FROM crashes {{WHERE_FILTERS}}), 2) AS percentage
        FROM crashes
        WHERE subType IN ('Atropellado', 'Choque con lesionados', 'Motociclista')
        {{AND_FILTERS}}
        GROUP BY subType
        ORDER BY percentage DESC
    """;

        Map<String, Object> params = new HashMap<>();
        String filteredSql = applyFilters(sql, filters, params);
        List<AccidentPercentageDTO> result = new ArrayList<>();

        try {
            List<Object[]> resultQuery = executeQuery(filteredSql, params);

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

    public List<AccidentNumberDTO> getAccidentsNumber(WidgetFiltersDTO filters) {
        String sql = """
        SELECT
          subType,
          COUNT(*) AS accidentCount
        FROM alcozone.crashes
        WHERE subType IN ('Choque con lesionados', 'Motociclista', 'Ciclista', 'Atropellado')
        {{AND_FILTERS}}
        GROUP BY subType
        ORDER BY accidentCount DESC
    """;

        Map<String, Object> params = new HashMap<>();
        String filteredSql = applyFilters(sql, filters, params);
        List<AccidentNumberDTO> result = new ArrayList<>();

        try {
            List<Object[]> resultQuery = executeQuery(filteredSql, params);

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


    public List<DangerousTownDTO> getDangerousTown(WidgetFiltersDTO filters) {
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
    public List<MonthlyAccidentsDTO> getMonthlyAccident(WidgetFiltersDTO filters) {
        String sql = """
        SELECT
          MONTHNAME(STR_TO_DATE(datetime, '%d/%m/%Y %H:%i:%s')) AS month_name,
          COUNT(*) AS accidents
        FROM alcozone.crashes
        WHERE MONTH(STR_TO_DATE(datetime, '%d/%m/%Y %H:%i:%s')) BETWEEN 1 AND 6
        {{AND_FILTERS}}
        GROUP BY MONTH(STR_TO_DATE(datetime, '%d/%m/%Y %H:%i:%s')), MONTHNAME(STR_TO_DATE(datetime, '%d/%m/%Y %H:%i:%s'))
        ORDER BY MONTH(STR_TO_DATE(datetime, '%d/%m/%Y %H:%i:%s'))
    """;

        Map<String, Object> params = new HashMap<>();
        String filteredSql = applyFilters(sql, filters, params);
        List<MonthlyAccidentsDTO> result = new ArrayList<>();

        try {
            List<Object[]> resultQuery = executeQuery(filteredSql, params);

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
    public List<DangerousTownMonthDTO> getDangerousTownMonth(WidgetFiltersDTO filters) {
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

    //metodo para obtener el número de accidentes por día
    public List<DailyAccidentsDTO> getDailyAccidents(WidgetFiltersDTO filters) {
        String sql = """
        SELECT
          DATE(STR_TO_DATE(datetime, '%d/%m/%Y %H:%i:%s')) AS accident_date,
          Count(*) AS total_accidents
        FROM crashes
        {{WHERE_FILTERS}}
        GROUP BY accident_date
        ORDER BY accident_date
    """;

        Map<String, Object> params = new HashMap<>();
        String filteredSql = applyFilters(sql, filters, params);
        List<DailyAccidentsDTO> result = new ArrayList<>();

        try{
            List<Object[]> resultQuery = executeQuery(filteredSql, params);

            for (Object[] row : resultQuery) {
                DailyAccidentsDTO dto = new DailyAccidentsDTO();
                dto.setAccident_date(row[0].toString());
                dto.setTotal_accidents(Integer.valueOf(row[1].toString()));
                result.add(dto);
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }

        return result;
    }

    public List<AccidentsByReportSourceDTO> getAccidentsByReportSource(WidgetFiltersDTO filters){
        String sql = """
        SELECT
          reportedBy as report_source,
          COUNT(*) AS total_accidents
        FROM crashes
        {{WHERE_FILTERS}}
        GROUP BY reportedBy;
    """;

        Map<String, Object> params = new HashMap<>();
        String filteredSql = applyFilters(sql, filters, params);
        List<AccidentsByReportSourceDTO> result = new ArrayList<>();

        try{
            List<Object[]> resultQuery = executeQuery(filteredSql, params);

            for(Object[] row : resultQuery) {
                AccidentsByReportSourceDTO dto = new AccidentsByReportSourceDTO();
                dto.setReport_source((String) row[0]);
                dto.setTotal_accidents(Integer.valueOf(row[1].toString()));
                result.add(dto);
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }

        return result;
    }

    private String applyFilters(String sql, WidgetFiltersDTO filters, Map<String, Object> params) {
        List<String> conditions = new ArrayList<>();
        if(filters != null) {
            if(filters.getTown() != null && !filters.getTown().isBlank()) {
                conditions.add("town =:town");
                params.put("town", filters.getTown());
            }
        }

        String whereClause = conditions.isEmpty() ? "" : " WHERE " + String.join(" AND ", conditions);
        String andClause = conditions.isEmpty() ? "" : " AND " + String.join(" AND ", conditions);

        return sql
                .replace("{{WHERE_FILTERS}}", whereClause)
                .replace("{{AND_FILTERS}}", andClause);
    }

    @SuppressWarnings("unchecked")
    private List<Object[]> executeQuery(String sql, Map<String, Object> params) {
        Query query = em.createNativeQuery(sql);
        if(params != null) {
            for(Map.Entry<String, Object> entry : params.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }
        return query.getResultList();
    }
}


