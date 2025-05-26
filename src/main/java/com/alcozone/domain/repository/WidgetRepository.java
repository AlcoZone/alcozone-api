package com.alcozone.domain.repository;

import com.alcozone.infrastructure.dto.widget.*;


import java.util.List;

public interface WidgetRepository {
    List<AccidentPercentageDTO> getAccidentsPercentage();
    List<AccidentNumberDTO> getAccidentsNumber();
    List<DangerousTownDTO> getDangerousTown();
    List<MonthlyAccidentsDTO> getMonthlyAccident();
    List<DangerousTownMonthDTO> getDangerousTownMonth();

}
