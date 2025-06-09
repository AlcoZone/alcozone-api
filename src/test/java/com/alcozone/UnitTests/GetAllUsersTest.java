package com.alcozone.UnitTests;

import com.alcozone.application.service.UserService;
import com.alcozone.infrastructure.dto.register.userDTO;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

@QuarkusTest
public class GetAllUsersTest {

    @Inject
    UserService userService;

    public static String generateRandomEmail() {
        String randomPart = UUID.randomUUID().toString().substring(0, 8);
        return "test-" + randomPart + "@example.com";
    }

    @BeforeEach
    public void init() {
        List<userDTO> users = userService.getAll();
        if (users.isEmpty()) {
            userDTO newUser = new userDTO();
            newUser.setEmail(generateRandomEmail());
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
