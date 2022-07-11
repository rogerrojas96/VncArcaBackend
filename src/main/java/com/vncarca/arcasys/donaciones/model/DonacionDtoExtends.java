package com.vncarca.arcasys.donaciones.model;

import com.vncarca.arcasys.donaciones.Dto.DonacionDto;
import com.vncarca.arcasys.persona.model.PersonaDtoExtends;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Created by Roy Morocho.
 * User: steve
 * Date: 11/07/2022
 * Time: 13:12
 */
@Data
@AllArgsConstructor
public class DonacionDtoExtends extends DonacionDto implements Serializable {
	private Long id;
	private PersonaDtoExtends persona;
	
	public DonacionDtoExtends(@NotBlank String descripcion, @Min(0) double cantidad, Long id, PersonaDtoExtends persona) {
		super(descripcion, cantidad);
		this.id = id;
		this.persona = persona;
	}
}
