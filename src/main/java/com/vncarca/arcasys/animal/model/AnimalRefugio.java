package com.vncarca.arcasys.animal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vncarca.arcasys.adopciones.model.Adopcion;
import com.vncarca.arcasys.carnetVacunacion.model.CarnetVacunacion;
import com.vncarca.arcasys.enums.Enum;
import com.vncarca.arcasys.enums.Types;
import com.vncarca.arcasys.fichaclinica.model.FichaClinica;
import com.vncarca.notificaciones.models.EventAlarm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "animales_refugio")
@SQLDelete(sql = "UPDATE animales_refugio SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
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

	@Column(nullable = true, columnDefinition = "tinyint(1) default 0")
	private Boolean adoptado;

	@NotNull
	@Column(nullable = false, columnDefinition = "tinyint(1) default 0")
	private Boolean deleted=Boolean.FALSE;

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

	//Soft delete en cascada todas entidades donde sea dependiente un animal de refugio

	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, mappedBy = "animal")
	private List<CarnetVacunacion> carnetVacunaciones;

	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, mappedBy = "animal")
	private List<FichaClinica> fichasClinicas;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "animal")
	private Adopcion adopcion;

	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, mappedBy = "paciente")
	private List<EventAlarm> eventAlarms;

}
