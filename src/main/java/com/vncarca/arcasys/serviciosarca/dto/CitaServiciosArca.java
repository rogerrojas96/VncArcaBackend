package com.vncarca.arcasys.serviciosarca.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by Roy Morocho.
 * User: steve
 * Date: 05/07/2022
 * Time: 15:09
 */
@Getter
@Setter
@NoArgsConstructor
public class CitaServiciosArca extends CitaDto{
    @NotNull
    private List<ServicioArcaDtoExtends> servicios;

    public CitaServiciosArca(String nombreCliente, Date fechaCita, String motivo, boolean estado, List<ServicioArcaDtoExtends> servicios) {
        super(nombreCliente, fechaCita, motivo, estado);
        this.servicios = servicios;
    }

}
