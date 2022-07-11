package com.vncarca.arcasys.carnetVacunacion.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vncarca.arcasys.animal.model.AnimalRefugio;
import com.vncarca.arcasys.carnetVacunacion.vacuna.model.Vacuna;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "carnetVacunaciones")
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "deleted=false")
@SQLDelete(sql = "UPDATE carnet_vacunaciones SET deleted = true WHERE id=?")
public class CarnetVacunacion implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "vacuna_id", nullable = false)
	private Vacuna vacuna;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "animal_id", nullable = false)
	private AnimalRefugio animal;

	@NotNull
	@Column(nullable = false, columnDefinition = "tinyint(1) default 0")
	private Boolean deleted=Boolean.FALSE;

	public CarnetVacunacion(Long id, Date fechaAplicacion, Date fechaProximaAplicacion, Vacuna vacuna, AnimalRefugio animal) {
		this.id = id;
		this.fechaAplicacion = fechaAplicacion;
		this.fechaProximaAplicacion = fechaProximaAplicacion;
		this.vacuna = vacuna;
		this.animal = animal;
	}
}
