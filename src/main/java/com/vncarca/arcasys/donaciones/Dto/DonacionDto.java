package com.vncarca.arcasys.donaciones.Dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
