package com.vncarca.arcasys.serviciosarca.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Roy Morocho.
 * User: steve
 * Date: 05/07/2022
 * Time: 15:09
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CitaServiciosArca extends CitaDto {
    @NotNull
    private List<ServicioArcaDtoExtends> servicios;
}