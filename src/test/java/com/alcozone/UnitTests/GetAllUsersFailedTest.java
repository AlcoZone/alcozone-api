package com.alcozone.UnitTests;

import com.alcozone.application.service.UserService;
import com.alcozone.infrastructure.dto.register.userDTO;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class GetAllUsersFailedTest {
    @Inject
    UserService userService;

    @Test
    void testGetAllUsersFailed() {
        List<userDTO> users = userService.getAll();

        if(!users.isEmpty()) {
            System.out.println("Se listaron los usuarios cuando no se esperaba");
        }
        assertEquals(0, users.size(), "Se esperaba que no hubiera usuarios enlistados");
    }
}
