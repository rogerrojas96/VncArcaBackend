package com.vncarca.authsys.security.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;
@Data
public class LoginRequest {
    @NotBlank(message = "El nombre de usuario o la dirección de correo electrónico no pueden estar vacíos")
    private String username;

    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;
}
