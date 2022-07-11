package com.vncarca.arcasys.tratamiento.model;

import com.vncarca.arcasys.fichaclinica.model.FichaClinicaDTO;
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
 * Time: 11:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TratamientoDtoExtends extends TratamientoDto implements Serializable {
	private Long id;
	@NotNull
	private FichaClinicaDTO idFichaClinica;
	
	public TratamientoDtoExtends(Date fechaAplicacion, @NotBlank String descripcion, @NotBlank String indicaciones, @NotBlank String estado, Long id, FichaClinicaDTO idFichaClinica) {
		super(fechaAplicacion, descripcion, indicaciones, estado);
		this.id = id;
		this.idFichaClinica = idFichaClinica;
	}
}
