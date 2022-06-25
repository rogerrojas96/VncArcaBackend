/**
* Created by Roy Morocho.
* User: steve
* Date: 20 jun 2022
* Time: 12:14:02
*/
package com.vncarca.notificaciones.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vncarca.arcasys.animal.model.Animal;
import com.vncarca.arcasys.enums.Enum;
import com.vncarca.arcasys.enums.Types;
import com.vncarca.arcasys.enums.Types.EVENT;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "eventAlarms")
public class EventAlarm implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Boolean checked = false;

	@NotNull
	@Column(nullable = false)
	@Enum(enumClass = Types.EVENT.class, regexp = "VACUNA o TRATAMIENTO")
	private String eventType;

	@Column(nullable = true)
	private String body;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Guayaquil")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date eventDay;

	@ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Animal.class)
	@JoinColumn(name = "animal_id", nullable = false)
	private Animal paciente;

	/**
	 * @param checked
	 * @param eventType
	 * @param body
	 * @param eventDay
	 * @param paciente
	 */
	public EventAlarm(Boolean checked,
			@NotNull @Enum(enumClass = EVENT.class, regexp = "VACUNA o TRATAMIENTO") String eventType, String body,
			@NotNull Date eventDay, Animal paciente) {
		this.checked = checked;
		this.eventType = eventType;
		this.body = body;
		this.eventDay = eventDay;
		this.paciente = paciente;
	}

}
