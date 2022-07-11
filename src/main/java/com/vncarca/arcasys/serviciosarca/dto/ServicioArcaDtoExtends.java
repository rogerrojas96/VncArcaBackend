package com.vncarca.arcasys.serviciosarca.dto;

import com.vncarca.arcasys.serviciosarca.dto.ServicioArcaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Roy Morocho.
 * User: steve
 * Date: 04/07/2022
 * Time: 20:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicioArcaDtoExtends extends ServicioArcaDto implements Serializable {
    private Long id;
    public ServicioArcaDtoExtends(@NotBlank String nombre, @NotBlank String descripcion, @NotNull @Min(0) double precio, Long id) {
        super(nombre, descripcion, precio);
        this.id = id;
    }
}
