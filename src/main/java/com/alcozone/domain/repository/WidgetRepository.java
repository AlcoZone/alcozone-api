package com.alcozone.domain.repository;

import com.alcozone.domain.models.widgetdata.*;
import java.util.List;
import com.alcozone.domain.models.Widget;

public interface WidgetRepository {
    List<Widget> findAllWidgets();
    Widget save(Widget widget);
    void deleteByUuid(String uuid);

    List<AccidentPercentage> getAccidentsPercentage(WidgetFilters filters);
    List<AccidentNumber> getAccidentsNumber(WidgetFilters filters);
    List<DangerousTown> getDangerousTown(WidgetFilters filters);
    List<MonthlyAccidents> getMonthlyAccident(WidgetFilters filters);
    List<DangerousTownMonth> getDangerousTownMonth(WidgetFilters filters);
    List<DailyAccidents> getDailyAccidents(WidgetFilters filters);
    List<AccidentsByReportSource> getAccidentsByReportSource(WidgetFilters filters);
}
