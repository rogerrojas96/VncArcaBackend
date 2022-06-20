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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vncarca.arcasys.animal.model.Animal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alarms")
public class Alarm implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = true)
	private Boolean checked;

	@Column(nullable = true)
	private String description;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Guayaquil")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date eventDay;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "animal_id", nullable = false, insertable = false, updatable = false)
	private Animal paciente;

	/**
	 * @param checked
	 * @param description
	 * @param eventDay
	 * @param pacientes
	 */
	public Alarm(Boolean checked, String description, @NotNull Date eventDay, Animal paciente) {
		this.checked = checked;
		this.description = description;
		this.eventDay = eventDay;
		this.paciente = paciente;
	}

}
