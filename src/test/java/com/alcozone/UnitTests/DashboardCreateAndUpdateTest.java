package com.alcozone.UnitTests;

import com.alcozone.application.dto.dashboard.CreateDashboardDTO;
import com.alcozone.application.dto.dashboard.UpdateDashboardDTO;
import com.alcozone.application.service.DashboardService;
import com.alcozone.domain.models.Dashboard;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class DashboardCreateAndUpdateTest {

    @Inject
    DashboardService dashboardService;

    @Test
    public void testCreateAndUpdateDashboard() {
        CreateDashboardDTO createDTO = new CreateDashboardDTO();
        createDTO.setName("Dashboard original");
        createDTO.setUserUuid("user-123");

        Dashboard created = dashboardService.createDashboard(createDTO);

        assertNotNull(created.getUuid());
        assertEquals("Dashboard original", created.getName());
        assertNotNull(created.getCreatedAt());
        assertNotNull(created.getUpdatedAt());

        UpdateDashboardDTO updateDTO = new UpdateDashboardDTO();
        updateDTO.setUuid(created.getUuid());
        updateDTO.setName("Dashboard actualizado");

        Dashboard updated = dashboardService.updateDashboard(updateDTO);

        assertEquals("Dashboard actualizado", updated.getName());
        assertEquals(created.getUuid(), updated.getUuid());
    }
}
