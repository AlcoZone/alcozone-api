package com.alcozone.UnitTests;

import com.alcozone.application.dto.dashboard.UpdateDashboardDTO;
import com.alcozone.application.service.DashboardService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class DashboardUpdateNotFoundTest {

    @Inject
    DashboardService dashboardService;

    @Test
    public void testUpdateNonexistentDashboardThrowsException() {
        UpdateDashboardDTO dto = new UpdateDashboardDTO();
        dto.setUuid("nonexistent-uuid-123");
        dto.setName("Nombre no importante");

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            dashboardService.updateDashboard(dto);
        });

        assertEquals("Dashboard not found", thrown.getMessage());
    }
}
