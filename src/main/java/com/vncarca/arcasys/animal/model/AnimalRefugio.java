package com.vncarca.arcasys.animal.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
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
@Table(name = "animales_refugio")
@SQLDelete(sql = "UPDATE animales_refugio SET deleted = true WHERE id=?")
@FilterDef(name = "deletedAnimalRefugioFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedAnimalRefugioFilter", condition = "deleted = :isDeleted")
public class AnimalRefugio implements Serializable {

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

	@Column(nullable = false)
	private Boolean deleted = Boolean.FALSE;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Guayaquil")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date fechaNacimiento;

	/*
	 * Atributos propios de la imagen que se guarda en el servidor de cloudinary
	 * ----------------------------------------------
	 */
	@Column(name = "nombre_imagen_animal_cld")
	private String nombreImagenAnimalCld;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "text", name = "url_imagen_animal_cld")
	private String urlImagenAnimalCld;

	@Column(name = "id_imagen_animal_cld")
	private String idImagenAnimalCld;
}
