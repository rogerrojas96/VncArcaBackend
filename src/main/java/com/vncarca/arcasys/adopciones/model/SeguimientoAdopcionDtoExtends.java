package com.vncarca.arcasys.adopciones.model;

import com.vncarca.arcasys.adopciones.dto.AdopcionDtoExtends;
import com.vncarca.arcasys.adopciones.dto.SeguimientoAdopcionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Roy Morocho.
 * User: steve
 * Date: 11/07/2022
 * Time: 12:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeguimientoAdopcionDtoExtends extends SeguimientoAdopcionDto implements Serializable {
	private Long id;
	private AdopcionDtoExtends adopcion;
	
	public SeguimientoAdopcionDtoExtends(@NotBlank String mensajeSeguimiento, @NotNull Date fechaSeguimiento, String RespuestaAdoptante, @NotNull boolean estadoSeguimiento, Long id, AdopcionDtoExtends adopcion) {
		super(mensajeSeguimiento, fechaSeguimiento, RespuestaAdoptante, estadoSeguimiento);
		this.id = id;
		this.adopcion = adopcion;
	}
}
