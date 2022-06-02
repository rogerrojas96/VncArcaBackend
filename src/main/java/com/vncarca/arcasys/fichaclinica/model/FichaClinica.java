package com.vncarca.arcasys.fichaclinica.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vncarca.arcasys.veterinario.model.Veterinario;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "fichasClinicas")

public class FichaClinica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Guayaquil")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date fechaIngreso;

	@Column(nullable = false)
	private String motivoConsulta;

	@Column(nullable = false)
	private String hallazgos;

	@Column(nullable = false)
	private float temperatura;

	@Column(nullable = false)
	private String conjuntiva;

	@Column(nullable = false)
	private float frecuenciaCardiaca;

	@Column(nullable = false)
	private float frecuenciaRespiratoria;

	@Column(nullable = false)
	private String TRC;

	@Column(nullable = false)
	private String mucosas;

	// @Column(nullable = false)
	// private String vacunas;

	// @Column(nullable = false)
	// private String desparacitaciones;

	@Column(nullable = false)
	private Boolean esterilizacion;

	@Column(nullable = false)
	private float alimentacion;

	@Column(nullable = false)
	private String pronostico;

	// @Column(nullable = false)
	// private String examenesSolicitados;

	@Column(nullable = false)
	private String diagnosticoDiferencial;

	@Column(nullable = false)
	private float costo;

	@NotNull
	@ManyToOne()
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Veterinario veterinario;

	
	// @JsonBackReference
	// @NotNull
	// @ManyToOne(fetch = FetchType.LAZY)
	// @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	// private Animal animal;
}
