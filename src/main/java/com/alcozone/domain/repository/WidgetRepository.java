package com.alcozone.domain.repository;

import com.alcozone.application.dto.widget.WidgetFiltersDTO;
import com.alcozone.infrastructure.dto.widget.*;
import java.util.List;
import com.alcozone.domain.models.Widget;

public interface WidgetRepository {
    List<Widget> findAllWidgets();
    Widget save(Widget widget);
    void deleteByUuid(String uuid);

    List<AccidentPercentageDTO> getAccidentsPercentage(WidgetFiltersDTO filters);
    List<AccidentNumberDTO> getAccidentsNumber(WidgetFiltersDTO filters);
    List<DangerousTownDTO> getDangerousTown(WidgetFiltersDTO filters);
    List<MonthlyAccidentsDTO> getMonthlyAccident(WidgetFiltersDTO filters);
    List<DangerousTownMonthDTO> getDangerousTownMonth(WidgetFiltersDTO filters);
    List<DailyAccidentsDTO> getDailyAccidents(WidgetFiltersDTO filters);
    List<AccidentsByReportSourceDTO> getAccidentsByReportSource(WidgetFiltersDTO filters);
}
