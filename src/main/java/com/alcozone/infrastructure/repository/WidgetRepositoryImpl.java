package com.alcozone.infrastructure.repository;

import com.alcozone.domain.models.Widget;
import com.alcozone.domain.models.widgetdata.*;
import com.alcozone.domain.repository.WidgetRepository;
import com.alcozone.infrastructure.entity.WidgetEntity;
import com.alcozone.infrastructure.mapper.WidgetMapper;
import com.alcozone.infrastructure.persistence.crash.CrashEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class WidgetRepositoryImpl implements WidgetRepository, PanacheRepositoryBase<CrashEntity, Integer> {

    @Override
    public List<Widget> findAllWidgets() {
        return WidgetEntity.listAll().stream().map(e -> WidgetMapper.toDomain((WidgetEntity) e)).collect(Collectors.toList());
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

    @Override
    public List<AccidentPercentage> getAccidentsPercentage(WidgetFilters filters) {
        String sql = """
                    SELECT
                      subType,
                      ROUND(100.0 * COUNT(*) /
                      (SELECT COUNT(*)
                      FROM Crashes c
                      JOIN Revisions r
                      ON c.revision_id = r.id
                      WHERE r.deleted = false
                      {{AND_FILTERS}}), 2) AS percentage
                    FROM Crashes c
                    JOIN Revisions r
                    ON c.revision_id = r.id
                    WHERE r.deleted = false
                    AND subType IN ('Atropellado', 'Choque con lesionados', 'Motociclista')
                    {{AND_FILTERS}}
                    GROUP BY subType
                    ORDER BY percentage DESC
                """;

        Map<String, Object> params = new HashMap<>();
        String filteredSql = applyFilters(sql, filters, params);
        List<AccidentPercentage> result = new ArrayList<>();

        try {
            List<Object[]> resultQuery = executeQuery(filteredSql, params);

            for (Object[] row : resultQuery) {
                AccidentPercentage accidentPercentage = new AccidentPercentage();
                accidentPercentage.setSubType((String) row[0]);
                accidentPercentage.setPercentage(((Number) row[1]).doubleValue());
                result.add(accidentPercentage);
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }

        return result;
    }

    @Override
    public List<AccidentNumber> getAccidentsNumber(WidgetFilters filters) {
        String sql = """
                    SELECT
                      subType,
                      COUNT(*) AS accidentCount
                    FROM Crashes c
                    JOIN Revisions r
                    ON c.revision_id = r.id
                    WHERE r.deleted = false
                    AND subType IN ('Choque con lesionados', 'Motociclista', 'Ciclista', 'Atropellado')
                    {{AND_FILTERS}}
                    GROUP BY subType
                    ORDER BY accidentCount DESC
                """;

        Map<String, Object> params = new HashMap<>();
        String filteredSql = applyFilters(sql, filters, params);
        List<AccidentNumber> result = new ArrayList<>();

        try {
            List<Object[]> resultQuery = executeQuery(filteredSql, params);

            for (Object[] row : resultQuery) {
                AccidentNumber accidentNumber = new AccidentNumber();
                accidentNumber.setSubType((String) row[0]);
                accidentNumber.setAccidentCount(Integer.valueOf(row[1].toString()));
                result.add(accidentNumber);
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }

        return result;
    }

    @Override
    public List<DangerousTown> getDangerousTown(WidgetFilters filters) {
        String sql = """
                    SELECT
                      town,
                      COUNT(*) AS total_accidents
                    FROM Crashes
                    WHERE town IS NOT NULL AND town != ''
                    {{AND_FILTERS}}
                    GROUP BY town
                    ORDER BY total_accidents DESC
                    LIMIT 2
                """;
        Map<String, Object> params = new HashMap<>();
        String filteredSql = applyFilters(sql, filters, params);
        List<DangerousTown> result = new ArrayList<>();

        try {
            List<Object[]> resultQuery = executeQuery(filteredSql, params);

            for (Object[] row : resultQuery) {
                DangerousTown dangerousTown = new DangerousTown();
                dangerousTown.setTown((String) row[0]);
                dangerousTown.setTotal_accidents(String.valueOf(row[1]));
                result.add(dangerousTown);
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }

        return result;
    }

    @Override
    public List<MonthlyAccidents> getMonthlyAccident(WidgetFilters filters) {
        String sql = """
                    SELECT
                      MONTHNAME(STR_TO_DATE(datetime, '%d/%m/%Y %H:%i:%s')) AS month_name,
                      COUNT(*) AS accidents
                    FROM Crashes c
                    JOIN Revisions r
                    ON c.revision_id = r.id
                    WHERE r.deleted = false
                    AND MONTH(STR_TO_DATE(datetime, '%d/%m/%Y %H:%i:%s')) BETWEEN 1 AND 6
                    {{AND_FILTERS}}
                    GROUP BY MONTH(STR_TO_DATE(datetime, '%d/%m/%Y %H:%i:%s')), MONTHNAME(STR_TO_DATE(datetime, '%d/%m/%Y %H:%i:%s'))
                    ORDER BY MONTH(STR_TO_DATE(datetime, '%d/%m/%Y %H:%i:%s'))
                """;

        Map<String, Object> params = new HashMap<>();
        String filteredSql = applyFilters(sql, filters, params);
        List<MonthlyAccidents> result = new ArrayList<>();

        try {
            List<Object[]> resultQuery = executeQuery(filteredSql, params);

            for (Object[] row : resultQuery) {
                MonthlyAccidents monthlyAccidents = new MonthlyAccidents();
                monthlyAccidents.setMonth_name((String) row[0]);
                monthlyAccidents.setAccidents(row[1].toString());
                result.add(monthlyAccidents);
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }

        return result;
    }

    @Override
    public List<DangerousTownMonth> getDangerousTownMonth(WidgetFilters filters) {
        String sql = """
                    SELECT month_name,town,total_accidents
                    FROM (
                    SELECT\s
                    MONTHNAME(STR_TO_DATE(datetime, '%d/%m/%Y %H:%i:%s')) AS month_name,
                                                                                MONTH(STR_TO_DATE(datetime, '%d/%m/%Y %H:%i:%s')) AS month_number,
                                                                                town,
                                                                                COUNT(*) AS total_accidents,
                                                                                ROW_NUMBER() OVER (PARTITION BY MONTH(STR_TO_DATE(datetime, '%d/%m/%Y %H:%i:%s'))\s
                                                                                                   ORDER BY COUNT(*) DESC) AS rn
                                                                              FROM Crashes
                                                                              WHERE town IS NOT NULL AND town != ''
                                                                                {{AND_FILTERS}}
                                                                              GROUP BY month_number, month_name, town
                                                                            ) AS ranked
                                                                            WHERE rn <= 2
                                                                            ORDER BY month_number, total_accidents DESC;
                
                """;
        Map<String, Object> params = new HashMap<>();
        String filteredSql = applyFilters(sql, filters, params);
        List<DangerousTownMonth> result = new ArrayList<>();

        try {
            List<Object[]> resultQuery = executeQuery(filteredSql, params);

            for (Object[] row : resultQuery) {
                DangerousTownMonth dangerousTownMonth = new DangerousTownMonth();
                dangerousTownMonth.setMonth_name((String) row[0]);
                dangerousTownMonth.setTown((String) row[1]);
                dangerousTownMonth.setTotal_accidents(Integer.parseInt(row[2].toString()));
                result.add(dangerousTownMonth);
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }

        return result;
    }

    @Override
    public List<DailyAccidents> getDailyAccidents(WidgetFilters filters) {
        String sql = """
                    SELECT
                      DATE(STR_TO_DATE(datetime, '%d/%m/%Y %H:%i:%s')) AS accident_date,
                      Count(*) AS total_accidents
                    FROM Crashes c
                    JOIN Revisions r ON c.revision_id = r.id
                    WHERE r.deleted = false
                    {{AND_FILTERS}}
                    GROUP BY accident_date
                    ORDER BY accident_date
                """;

        Map<String, Object> params = new HashMap<>();
        String filteredSql = applyFilters(sql, filters, params);
        List<DailyAccidents> result = new ArrayList<>();

        try {
            List<Object[]> resultQuery = executeQuery(filteredSql, params);

            for (Object[] row : resultQuery) {
                DailyAccidents dailyAccidents = new DailyAccidents();
                dailyAccidents.setAccident_date(row[0].toString());
                dailyAccidents.setTotal_accidents(Integer.valueOf(row[1].toString()));
                result.add(dailyAccidents);
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }

        return result;
    }

    @Override
    public List<AccidentsByReportSource> getAccidentsByReportSource(WidgetFilters filters) {
        String sql = """
                    SELECT
                      reportedBy as report_source,
                      COUNT(*) AS total_accidents
                    FROM Crashes c
                    JOIN Revisions r
                    ON c.revision_id = r.id
                    WHERE r.deleted = false
                    {{AND_FILTERS}}
                    GROUP BY reportedBy;
                """;

        Map<String, Object> params = new HashMap<>();
        String filteredSql = applyFilters(sql, filters, params);
        List<AccidentsByReportSource> result = new ArrayList<>();

        System.out.println(result);

        try {
            List<Object[]> resultQuery = executeQuery(filteredSql, params);

            for (Object[] row : resultQuery) {
                AccidentsByReportSource accidentsByReportSource = new AccidentsByReportSource();
                accidentsByReportSource.setReport_source((String) row[0]);
                accidentsByReportSource.setTotal_accidents(Integer.valueOf(row[1].toString()));
                result.add(accidentsByReportSource);
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }

        return result;
    }

    private String applyFilters(String sql, WidgetFilters filters, Map<String, Object> params) {
        List<String> conditions = new ArrayList<>();

        if (filters != null) {
            if (filters.getTown() != null && !filters.getTown().isBlank()) {
                conditions.add("LOWER(town) = LOWER(:town)");
                params.put("town", filters.getTown());
            }

            if (filters.getStartDate() != null && !filters.getStartDate().isBlank() && filters.getEndDate() != null && !filters.getEndDate().isBlank()) {
                conditions.add("STR_TO_DATE(datetime, '%d/%m/%Y %H:%i:%s') BETWEEN STR_TO_DATE(:startDate, '%d/%m/%Y') AND DATE_ADD(STR_TO_DATE(:endDate, '%d/%m/%Y'), INTERVAL 1 DAY)");
                params.put("startDate", filters.getStartDate());
                params.put("endDate", filters.getEndDate());
            }

        }

        String whereClause = conditions.isEmpty() ? "" : " WHERE " + String.join(" AND ", conditions);
        String andClause = conditions.isEmpty() ? "" : " AND " + String.join(" AND ", conditions);
        return sql.replace("{{WHERE_FILTERS}}", whereClause).replace("{{AND_FILTERS}}", andClause);
    }

    @SuppressWarnings("unchecked")
    private List<Object[]> executeQuery(String sql, Map<String, Object> params) {
        Query query = em.createNativeQuery(sql);
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }
        return query.getResultList();
    }
}


