package com.vncarca.arcasys.serviciosarca.dto;

import com.vncarca.arcasys.veterinario.model.VeterinarioDTO;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Roy Morocho.
 * User: steve
 * Date: 05/07/2022
 * Time: 14:10
 */
@Getter
@Setter
public class CitaDtoExtends extends CitaDto implements Serializable {
    private Long id;
    private VeterinarioDTO veterinario;

    public CitaDtoExtends(String nombreCliente, Date fechaCita, String motivo, boolean estado, Long id, VeterinarioDTO veterinario) {
        super(nombreCliente, fechaCita, motivo, estado);
        this.id = id;
        this.veterinario = veterinario;
    }

}
