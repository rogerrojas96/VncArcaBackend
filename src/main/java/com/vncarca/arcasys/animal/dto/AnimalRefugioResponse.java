package com.vncarca.arcasys.animal.dto;

import com.vncarca.arcasys.enums.Enum;
import com.vncarca.arcasys.enums.Types;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalRefugioResponse extends AnimalRefugioRequest {

	private Long id;
	private String urlImagenAnimal;

	public AnimalRefugioResponse(@NotBlank String nombre, @NotBlank String especie, @NotBlank String colorCaracteristicas, @NotBlank @Enum(enumClass = Types.SEXO.class, regexp = "MACHO o HEMBRA") String sexo, @Min(0) int edad, @NotBlank String raza, @NotBlank @Enum(enumClass = Types.ESTANCIA.class, regexp = "CLINICA o REFUGIO") String lugarEstancia, @NotBlank @Enum(enumClass = Types.PROCEDENCIA.class, regexp = "PARTICULAR o ECU911") String procedencia, @NotBlank String observacionesProcedencia, @Min(0) float peso, Boolean adoptado, Date fechaNacimiento, Long id, String urlImagenAnimal) {
		super(nombre, especie, colorCaracteristicas, sexo, edad, raza, lugarEstancia, procedencia, observacionesProcedencia, peso, adoptado, fechaNacimiento);
		this.id = id;
		this.urlImagenAnimal = urlImagenAnimal;
	}
}