/**
* Created by Roy Morocho.
* User: steve
* Date: 08 jun 2022
* Time: 15:44:01
*/
package com.vncarca.arcasys.fichaclinica.model;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.vncarca.arcasys.animal.model.AnimalDTO;
import com.vncarca.arcasys.veterinario.model.VeterinarioDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// @JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
// @JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class FichaClinicaDTO {
	private Long id;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Guayaquil")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fechaIngreso;
	private String motivoConsulta;
	private String hallazgos;
	private float temperatura;
	private String conjuntiva;
	private float frecuenciaCardiaca;
	private float frecuenciaRespiratoria;
	private String TRC;
	private String mucosas;
	private String esterilizacion;
	private float alimentacion;
	private String pronostico;
	private String tipoPaciente;
	private String examenes_solicitados;
	private String diagnosticoDiferencial;
	private float costo;
	private VeterinarioDTO veterinario;
	private AnimalDTO animal;

	/**
	 * @param fechaIngreso
	 * @param motivoConsulta
	 * @param hallazgos
	 * @param temperatura
	 * @param conjuntiva
	 * @param frecuenciaCardiaca
	 * @param frecuenciaRespiratoria
	 * @param tRC
	 * @param mucosas
	 * @param esterilizacion
	 * @param alimentacion
	 * @param pronostico
	 * @param tipoPaciente
	 * @param examenes_solicitados
	 * @param diagnosticoDiferencial
	 * @param costo
	 * @param veterinario
	 * @param animal
	 */
	public FichaClinicaDTO(Date fechaIngreso, String motivoConsulta, String hallazgos, float temperatura,
			String conjuntiva, float frecuenciaCardiaca, float frecuenciaRespiratoria, String tRC, String mucosas,
			String esterilizacion, float alimentacion, String pronostico, String tipoPaciente, String examenes_solicitados,
			String diagnosticoDiferencial, float costo, VeterinarioDTO veterinario, AnimalDTO animal) {
		this.fechaIngreso = fechaIngreso;
		this.motivoConsulta = motivoConsulta;
		this.hallazgos = hallazgos;
		this.temperatura = temperatura;
		this.conjuntiva = conjuntiva;
		this.frecuenciaCardiaca = frecuenciaCardiaca;
		this.frecuenciaRespiratoria = frecuenciaRespiratoria;
		TRC = tRC;
		this.mucosas = mucosas;
		this.esterilizacion = esterilizacion;
		this.alimentacion = alimentacion;
		this.pronostico = pronostico;
		this.tipoPaciente = tipoPaciente;
		this.examenes_solicitados = examenes_solicitados;
		this.diagnosticoDiferencial = diagnosticoDiferencial;
		this.costo = costo;
		this.veterinario = veterinario;
		this.animal = animal;
	}

}
