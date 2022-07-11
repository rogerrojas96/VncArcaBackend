package com.vncarca.arcasys.medicamento.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

/**
 * Created by Roy Morocho.
 * User: steve
 * Date: 04/07/2022
 * Time: 18:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicamentoDto implements Serializable {
    private Long id;
    @NotBlank
    @NotNull
    private String nombreComercial;
    @NotBlank
    @NotNull
    private String nombreGenerico;
    @Positive
    private int cantidad;
    @Positive
    private float precio;
}
