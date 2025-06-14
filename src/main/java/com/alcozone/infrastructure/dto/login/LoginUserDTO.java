package com.alcozone.infrastructure.dto.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDTO {
    private String uuid;
    private Integer role_id;
    private String email;
    private String name;
}
