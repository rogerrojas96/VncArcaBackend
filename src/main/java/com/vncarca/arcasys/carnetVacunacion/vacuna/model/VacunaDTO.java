package com.vncarca.arcasys.carnetVacunacion.vacuna.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Roy Morocho.
 * User: steve
 * Date: 04/07/2022
 * Time: 17:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacunaDTO implements Serializable {
    private Long id;
    @NotNull
    @NotBlank
    private String nombre;
    @NotNull
    @NotBlank
    private String tipo;
    @NotNull
    @NotBlank
    private String descripcion;
}
