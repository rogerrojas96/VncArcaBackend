package com.vncarca.arcasys.adopciones.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vncarca.arcasys.animal.model.Animal;

import org.springframework.format.annotation.DateTimeFormat;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "adopciones")
public class Adopcion implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Guayaquil")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fechaAdopcion;

    @Column(nullable = false)
    private String descripcion;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @JoinColumn(name="id_adoptante")
    private Adoptante adoptante;

    @OneToOne
    @JoinColumn(name="id_animal")
    private Animal animal;

    public Adopcion(Date fechaAdopcion, String descripcion, Adoptante adoptante, Animal animal) {
        this.fechaAdopcion = fechaAdopcion;
        this.descripcion = descripcion;
        this.adoptante = adoptante;
        this.animal = animal;
    }
}
