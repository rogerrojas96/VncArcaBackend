/**
* Created by Roy Morocho.
* User: steve
* Date: 25 jun 2022
* Time: 9:21:33
*/
package com.vncarca.arcasys.fichaclinica.model;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vncarca.arcasys.enums.Enum;
import com.vncarca.arcasys.enums.Types;

import lombok.Data;

@Data
public class FichaClinicaRequestDTO {
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
	@Enum(enumClass = Types.ESTERILIZACION.class, regexp = "SI o NO")
	private String esterilizacion;
	private float alimentacion;
	private String pronostico;
	@Enum(enumClass = Types.TIPO_PACIENTE.class, regexp = "INTERNO o EXTERNO")
	private String tipoPaciente;
	private String examenes_solicitados;
	private String diagnosticoDiferencial;
	private float costo;
	private Long personaId;
	private Long animalId;
}
