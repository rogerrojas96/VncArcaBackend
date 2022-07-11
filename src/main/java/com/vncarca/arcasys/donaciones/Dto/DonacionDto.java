package com.vncarca.arcasys.donaciones.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DonacionDto {
    
    @NotBlank
    private String descripcion;

    @Min(0)
    private double cantidad;
}
