package com.vncarca.arcasys.voluntarios.model;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class VoluntarioDto {

    @NotBlank
    private String actividad;

    @NotBlank
    private String tipo;
}
