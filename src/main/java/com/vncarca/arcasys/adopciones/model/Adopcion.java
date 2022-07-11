package com.vncarca.arcasys.adopciones.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vncarca.arcasys.animal.model.AnimalRefugio;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "adopciones")
@SQLDelete(sql = "UPDATE adopciones SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
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

    @ManyToOne( fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "id_adoptante", nullable = false)
    private Adoptante adoptante;

    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="id_animal",nullable = false)
    private AnimalRefugio animal;

    @NotNull
    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean deleted=Boolean.FALSE;

    public Adopcion(Date fechaAdopcion, String descripcion, Adoptante adoptante, AnimalRefugio animal) {
        this.fechaAdopcion = fechaAdopcion;
        this.descripcion = descripcion;
        this.adoptante = adoptante;
        this.animal = animal;
    }
    //SoftDelete
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "adopcion")
    private List<SeguimientoAdopcion> seguimientoAdopciones;
}
