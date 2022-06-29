package com.vncarca.arcasys.animal.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	private String sexo;

	@Min(0)
	private int edad;

	@NotBlank
	private String raza;

	@NotBlank
	private String lugarEstancia;

	private String procedencia;

	@NotBlank
	private String observacionesProcedencia;

	@Min(0)
	private float peso;

	private Boolean adoptado;

	private Boolean deleted;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Guayaquil")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
}
