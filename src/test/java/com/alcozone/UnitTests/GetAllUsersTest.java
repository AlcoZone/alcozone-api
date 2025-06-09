package com.alcozone.UnitTests;

import com.alcozone.application.service.UserService;
import com.alcozone.infrastructure.dto.register.userDTO;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

@QuarkusTest
public class GetAllUsersTest {

    @Inject
    UserService userService;

    @BeforeEach
    public void init() {
        List<userDTO> users = userService.getAll();
        if (users.isEmpty()) {
            userDTO newUser = new userDTO();
            newUser.setEmail("Alondra@pruebas.com");
            newUser.setUsername("testuser");
            newUser.setPassword("test123");
            newUser.setRole_id(1);
            userService.register(newUser);
    }}
    @Test
    public void testGetAllReturnsUsers() {
        List<userDTO> users = userService.getAll();

        assert users != null;
        assert !users.isEmpty();

        userDTO firstUser = users.get(0);

        assert firstUser.getEmail() != null;
        assert firstUser.getUsername() != null;
    }

}
