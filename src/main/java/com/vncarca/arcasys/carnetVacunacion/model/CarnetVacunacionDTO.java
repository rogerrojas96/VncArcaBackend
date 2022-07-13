/**
* Created by Roy Morocho.
* User: steve
* Date: 10 jun 2022
* Time: 16:03:44
*/
package com.vncarca.arcasys.carnetVacunacion.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vncarca.arcasys.animal.dto.AnimalRefugioResponse;
import com.vncarca.arcasys.carnetVacunacion.vacuna.model.VacunaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarnetVacunacionDTO {

	private Long id;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Guayaquil")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date fechaAplicacion;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Guayaquil")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date fechaProximaAplicacion;

	private VacunaDTO vacuna;

	private AnimalRefugioResponse animal;
}