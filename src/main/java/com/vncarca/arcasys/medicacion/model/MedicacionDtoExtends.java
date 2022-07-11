package com.vncarca.arcasys.medicacion.model;

import com.vncarca.arcasys.medicacion.dto.MedicacionDto;
import com.vncarca.arcasys.medicamento.model.MedicamentoDto;
import com.vncarca.arcasys.tratamiento.model.TratamientoDtoExtends;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Roy Morocho.
 * User: steve
 * Date: 11/07/2022
 * Time: 11:58
 */
@Data
@ConstructorBinding
@AllArgsConstructor
public class MedicacionDtoExtends extends MedicacionDto implements Serializable {
	private final Long id;
	private final MedicamentoDto medicamento;
	@NotNull
	private final TratamientoDtoExtends tratamiento;
	
	public MedicacionDtoExtends(@NotBlank String descripcionMd, @NotBlank String dosis, @NotBlank String frecuencia, @NotBlank String duracion, Date fechaCaducidad, Long id, MedicamentoDto medicamento, TratamientoDtoExtends tratamiento) {
		super(descripcionMd, dosis, frecuencia, duracion, fechaCaducidad);
		this.id = id;
		this.medicamento = medicamento;
		this.tratamiento = tratamiento;
	}
}
