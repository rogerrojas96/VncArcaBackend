package com.vncarca.arcasys.animal.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vncarca.arcasys.carnetVacunacion.model.CarnetVacunacion;
import com.vncarca.arcasys.fichaclinica.model.FichaClinica;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "animales")
public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String especie;

    @Column(nullable = false)
    private String caracteristicas;

    @Column(nullable = false)
    private String sexo;

    @Column(nullable = false)
    private String color;

    @Column(nullable = true)
    private int edad;

    @Column(nullable = false)
    private String raza;

    @Column(nullable = true)
    private String tamanyo;

    @Column(nullable = false)
    private float peso;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Guayaquil")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fechaNacimiento;

    // Por momento solo links de imágenes
    @Column(nullable = true)
    private String foto;
   
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "animal_id")
	private List<FichaClinica> fichasClinicas;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "animal_id")
	private List<CarnetVacunacion> historialVacunaciones;
}
