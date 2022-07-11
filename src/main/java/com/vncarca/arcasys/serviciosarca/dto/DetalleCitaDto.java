package com.vncarca.arcasys.serviciosarca.dto;

import com.vncarca.arcasys.serviciosarca.dto.CitaDto;
import com.vncarca.arcasys.serviciosarca.dto.ServicioArcaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Roy Morocho.
 * User: steve
 * Date: 05/07/2022
 * Time: 17:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleCitaDto implements Serializable {
    private Long id;
    private CitaDtoExtends cita;
    private ServicioArcaDtoExtends servicioArca;
}
