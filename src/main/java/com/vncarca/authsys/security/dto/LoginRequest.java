package com.vncarca.authsys.security.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginRequest {
    @NotBlank(message = "El nombre de usuario o la dirección de correo electrónico no pueden estar vacíos")
    private String username;

    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;
}
