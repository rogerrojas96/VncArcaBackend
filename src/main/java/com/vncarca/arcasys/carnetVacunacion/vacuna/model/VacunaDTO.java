/**
* Created by Roy Morocho.
* User: steve
* Date: 10 jun 2022
* Time: 16:11:39
*/
package com.vncarca.arcasys.carnetVacunacion.vacuna.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)

public class VacunaDTO {
	private Long id;
	private String nombre;
	private String tipo;
	private String descripcion;
}
