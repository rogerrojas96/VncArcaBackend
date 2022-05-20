package com.vncarca.arcasys.adopciones.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.vncarca.arcasys.persona.model.Persona;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "adoptantes")
public class Adoptante implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "telefono_familiar" ,nullable = false)
    private String telefonoFamiliar;


    @Column(name = "nickname_facebook" ,nullable = false)
    private String nicknameFacebook;

    @OneToOne
    @JoinColumn(name="id_persona")
    private Persona persona;

    @OneToMany(mappedBy = "adoptante",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List <Adopcion> adopciones; 

    public Adoptante(String telefonoFamiliar,String nicknameFacebook) {
        
        this.telefonoFamiliar = telefonoFamiliar;
        this.nicknameFacebook = nicknameFacebook;
    }
}
