package com.alcozone.domain.repository;

import java.util.List;
import com.alcozone.domain.models.Widget;

public interface WidgetRepository {

    List<Widget> findAll();

    Widget save(Widget widget);

    void deleteByUuid(String uuid);
}
