package com.vncarca.arcasys.serviciosarca.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ServicioArcaDto {
    
    @NotBlank
    private String nombre;

    @NotBlank
    private String descripcion;

    @NotNull
    @Min(0)
    private double precio;
}
