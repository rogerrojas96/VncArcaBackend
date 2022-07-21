package com.vncarca.arcasys.adopciones.model;

import com.vncarca.arcasys.persona.model.Persona;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "adoptantes")
@Where(clause = "deleted=false")
@SQLDelete(sql = "UPDATE adoptantes SET deleted = true WHERE id=?")
public class Adoptante implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "telefono_familiar" ,nullable = false)
    private String telefonoFamiliar;


    @Column(name = "nickname_facebook" ,nullable = false)
    private String nicknameFacebook;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_persona")
    private Persona persona;

    @NotNull
    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean deleted = Boolean.FALSE;

    //    Soft Delete
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "adoptante")
    private List<Adopcion> adopciones;

    public void setAdopciones(List<Adopcion> adopciones) {
        this.adopciones.addAll(adopciones);
    }
}