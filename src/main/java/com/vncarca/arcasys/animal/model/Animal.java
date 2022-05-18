package com.vncarca.arcasys.animal.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vncarca.arcasys.adopciones.model.Adopcion;

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


    @OneToOne(mappedBy = "animal")
    private Adopcion adopcion;

    //Por momento solo links de imágenes
    @Column(nullable = true)
    private String foto;

    public Animal(Long id, String nombre, String especie, String caracteristicas, String sexo, String color, int edad,
            String raza, String tamanyo, float peso, Date fechaNacimiento, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.caracteristicas = caracteristicas;
        this.sexo = sexo;
        this.color = color;
        this.edad = edad;
        this.raza = raza;
        this.tamanyo = tamanyo;
        this.peso = peso;
        this.fechaNacimiento = fechaNacimiento;
        this.foto = foto;
    }

}
