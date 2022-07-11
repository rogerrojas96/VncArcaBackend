package com.vncarca.arcasys.adopciones.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vncarca.arcasys.animal.dto.AnimalRefugioResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Roy Morocho.
 * User: steve
 * Date: 06/07/2022
 * Time: 14:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdopcionDtoExtends implements Serializable {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Guayaquil")
    private Date fechaAdopcion;
    private String descripcion;
    private AdoptanteDtoExtends adoptante;
    private AnimalRefugioResponse animal;
}
