package com.vncarca.arcasys.animal.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vncarca.arcasys.enums.Enum;
import com.vncarca.arcasys.enums.Types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "animales")
public class Animal implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotBlank
	@Column(nullable = false)
	private String nombre;

	@NotBlank
	@NotNull
	@Column(nullable = false)
	private String especie;

	@Lob
	@NotBlank
	@NotNull
	@Column(nullable = false)
	private String colorCaracteristicas;

	@NotNull
	@Column(nullable = false)
	@Enum(enumClass = Types.SEXO.class, regexp = "MACHO o HEMBRA")
	private String sexo;

	@NotNull
	@Column(nullable = false)
	private int edad;

	@NotNull
	@NotBlank
	@Column(nullable = false)
	private String raza;

	@NotBlank
	@NotNull
	private String tamanyo;

	/*
	 * El usuario podrá registrar el lugar en donde se encuentran los animales.
	 * (Clínica, Refugio)
	 */
	@NotNull
	@Column(nullable = false)
	@Enum(enumClass = Types.ESTANCIA.class, regexp = "CLINICA o REFUGIO")
	private String lugarEstancia;

	/*
	 * El usuario podrá registrar los animales que son rescatados ya sea por
	 * llamadas particulares o por el 911
	 */
	@NotNull
	@Enum(enumClass = Types.PROCEDENCIA.class, regexp = "PARTICULAR o ECU911")
	@Column(nullable = true)
	private String procedencia;

	@Lob
	@NotBlank
	@NotNull
	@Column(nullable = false)
	private String observacionesProcedencia;

	@NotNull
	@Positive
	@Column(nullable = false)
	private float peso;

	
	@Column(nullable = true)
	private Boolean adoptado;

	@Lob
	@NotNull
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "text")
	private String foto;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Guayaquil")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date fechaNacimiento;

	// @NotNull
	// @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "animal", orphanRemoval =
	// true, fetch = FetchType.EAGER, targetEntity = FichaClinica.class)
	// private List<FichaClinica> fichasClinicas = new ArrayList<>();

	// @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "animal", fetch =
	// FetchType.EAGER, orphanRemoval = true, targetEntity = CarnetVacunacion.class)
	// private Set<CarnetVacunacion> historialVacunaciones;

}
