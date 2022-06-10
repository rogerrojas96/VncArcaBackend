/**
* Created by Roy Morocho.
* User: steve
* Date: 08 jun 2022
* Time: 16:53:08
*/
package com.vncarca.arcasys.animal.model;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class AnimalDTO {

	private Long id;
	private String nombre;
	private String especie;
	private String colorCaracteristicas;
	private String sexo;
	private int edad;
	private String raza;
	private String tamanyo;
	private String tipoEntorno;
	private String lugarEstancia;
	private String procedencia;
	private String observacionesProcedencia;
	private float peso;
	private String foto;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Guayaquil")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;

	// private Set<CarnetVacunacion> historialVacunaciones;
}
