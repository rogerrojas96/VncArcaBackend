package com.vncarca.arcasys.persona.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Roy Morocho.
 * User: steve
 * Date: 05/07/2022
 * Time: 16:15
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDtoExtends extends PersonaDto implements Serializable {
    private Long id;

    public PersonaDtoExtends(@Size(min = 10, max = 10) @NotBlank @NotNull String cedula, @NotBlank @NotNull String nombre, @NotBlank @NotNull String apellidos, @NotBlank @NotNull String direccion, @Size(min = 7, max = 9) @NotBlank @NotNull String telefono, @Size(min = 9, max = 10) @NotBlank String celular, @NotBlank @NotNull @Email String correo, Long id) {
        super(cedula, nombre, apellidos, direccion, telefono, celular, correo);
        this.id = id;
    }

}