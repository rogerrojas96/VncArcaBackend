package com.vncarca.arcasys.animal.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vncarca.arcasys.enums.Enum;
import com.vncarca.arcasys.enums.Types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalRefugioRequest {

	@NotBlank
	private String nombre;

	@NotBlank
	private String especie;

	@NotBlank
	private String colorCaracteristicas;

	@NotBlank
	@Enum(enumClass = Types.SEXO.class, regexp = "MACHO o HEMBRA")
	private String sexo;

	@Min(0)
	private int edad;

	@NotBlank
	private String raza;

	@NotBlank
	@Enum(enumClass = Types.ESTANCIA.class, regexp = "CLINICA o REFUGIO")
	private String lugarEstancia;
	
	@NotBlank
	@Enum(enumClass = Types.PROCEDENCIA.class, regexp = "PARTICULAR o ECU911")
	private String procedencia;

	@NotBlank
	private String observacionesProcedencia;

	@Min(0)
	private float peso;

	private Boolean adoptado=Boolean.FALSE;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Guayaquil")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
}
