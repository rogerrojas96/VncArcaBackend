package com.vncarca.arcasys.serviciosarca.dto;

import com.vncarca.arcasys.veterinario.model.VeterinarioDTO;
import lombok.Getter;
import lombok.Setter;

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

    public CitaDtoExtends(long cliente_id, Date fechaCita, String motivo, boolean estado, Long id, VeterinarioDTO veterinario) {
        super(cliente_id, fechaCita, motivo, estado);
        this.id = id;
        this.veterinario = veterinario;
    }

}