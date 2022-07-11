package com.vncarca.arcasys.persona.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.vncarca.arcasys.adopciones.dto.AdoptanteDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Roy Morocho.
 * User: steve
 * Date: 04/07/2022
 * Time: 14:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDto implements Serializable {
    @Size(min = 10, max = 10)
    @NotBlank
    @NotNull
    private String cedula;
    @NotBlank
    @NotNull
    private String nombre;
    @NotBlank
    @NotNull
    private String apellidos;
    @NotBlank
    @NotNull
    private String direccion;
    @Size(min = 7, max = 9)
    @NotBlank
    @NotNull
    private String telefono;
    @Size(min = 9, max = 10)
    @NotBlank
    private String celular;
    @NotBlank
    @NotNull
    @Email
    private String correo;
}
