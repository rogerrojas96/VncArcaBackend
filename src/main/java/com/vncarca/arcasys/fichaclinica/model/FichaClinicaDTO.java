/**
* Created by Roy Morocho.
* User: steve
* Date: 08 jun 2022
* Time: 15:44:01
*/
package com.vncarca.arcasys.fichaclinica.model;

import java.util.Date;

import com.vncarca.arcasys.animal.model.AnimalDTO;
import com.vncarca.arcasys.veterinario.model.VeterinarioDTO;

import lombok.Data;

@Data
public class FichaClinicaDTO {
	private Long id;
	private Date fechaIngreso;
	private String motivoConsulta;
	private String hallazgos;
	private float temperatura;
	private String conjuntiva;
	private float frecuenciaCardiaca;
	private float frecuenciaRespiratoria;
	private String TRC;
	private String mucosas;
	private Boolean esterilizacion;
	private float alimentacion;
	private String pronostico;
	private String diagnosticoDiferencial;
	private float costo;
	private VeterinarioDTO veterinario;
	private AnimalDTO animal;
}
