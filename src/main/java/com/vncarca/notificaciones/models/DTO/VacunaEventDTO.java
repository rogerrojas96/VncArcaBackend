/**
* Created by Roy Morocho.
* User: steve
* Date: 20 jun 2022
* Time: 15:52:32
*/
package com.vncarca.notificaciones.models.DTO;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vncarca.arcasys.animal.model.AnimalDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacunaEventDTO {
	private Long id;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Guayaquil")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date eventDay;
	private Boolean checked;
	private String body;
	private String eventType;
	private AnimalDTO paciente;
}
