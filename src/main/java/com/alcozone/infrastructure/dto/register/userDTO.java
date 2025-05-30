package com.alcozone.infrastructure.dto.register;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class userDTO {
    private String uuid;
    private Integer role_id;
    private String username;
    private String password;
    private String email;
    private String role;
}
