package com.alcozone.domain.repository;

import com.alcozone.infrastructure.dto.widget.*;
import java.util.List;
import com.alcozone.domain.models.Widget;

public interface WidgetRepository {
    List<Widget> findAllWidgets();
    Widget save(Widget widget);
    void deleteByUuid(String uuid);

    List<AccidentPercentageDTO> getAccidentsPercentage();
    List<AccidentNumberDTO> getAccidentsNumber();
    List<DangerousTownDTO> getDangerousTown();
    List<MonthlyAccidentsDTO> getMonthlyAccident();
    List<DangerousTownMonthDTO> getDangerousTownMonth();
}
