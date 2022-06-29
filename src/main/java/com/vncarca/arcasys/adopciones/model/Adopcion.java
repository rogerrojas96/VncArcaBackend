package com.vncarca.arcasys.adopciones.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vncarca.arcasys.animal.model.AnimalRefugio;

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
    @Column(nullable = false, name = "fecha_adopcion")
    private Date fechaAdopcion;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(nullable = false, columnDefinition = "text")
    private String descripcion;

	@ManyToOne
    @JoinColumn(name="id_adoptante")
    private Adoptante adoptante;

    @OneToOne
    @JoinColumn(name="id_animal")
    private AnimalRefugio animal; 

    public Adopcion(Date fechaAdopcion, String descripcion, Adoptante adoptante, AnimalRefugio animal) {
        this.fechaAdopcion = fechaAdopcion;
        this.descripcion = descripcion;
        this.adoptante = adoptante;
        this.animal = animal;
    }
}
