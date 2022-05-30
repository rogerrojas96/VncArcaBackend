package com.vncarca.arcasys.vacunas.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vncarca.arcasys.fichaclinica.model.FichaClinica;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "vacunas")
public class Vacuna implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Guayaquil")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fechaVacunacion;

    @Column(nullable = false)
    private String tipoVacuna;

    @Column(nullable = false)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name="id_ficha_clinica")
    private FichaClinica fichaClinica;

    public Vacuna(String nombre, Date fechaVacunacion, String tipoVacuna, String descripcion,
            FichaClinica fichaClinica) {
        this.nombre = nombre;
        this.fechaVacunacion = fechaVacunacion;
        this.tipoVacuna = tipoVacuna;
        this.descripcion = descripcion;
        this.fichaClinica = fichaClinica;
    }

}
