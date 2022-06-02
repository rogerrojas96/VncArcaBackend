package com.vncarca.arcasys.medicacion.model;

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
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vncarca.arcasys.medicamento.model.Medicamento;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="medicacion")
public class Medicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "descripcion_md")
    private String nombreCliente;

    @Column(name = "dosis")
    private String dosis;

    @Column(name = "frecuencia")
    private String frecuencia;

    @Column(name = "duracion")
    private String duracion;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "America/Guayaquil")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "fecha_caducidad", unique = true)
    private Date fechaCaducidad;

    /* --------------------------- RELACIONES --------------------------- */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @JoinColumn(name="id_medicamento")
    private Medicamento medicamento;

    
}
