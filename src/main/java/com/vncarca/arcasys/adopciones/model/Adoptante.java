package com.vncarca.arcasys.adopciones.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

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

    @Column(nullable = false)
    @Size(max = 10)
    private String cedula;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String telefono;

    @Column(name = "telefono_familiar" ,nullable = false)
    private String telefonoFamiliar;

    @Column(nullable = false)
    private String celular;

    @Column(nullable = false)
    private String correo;

    @Column(name = "nickname_facebook" ,nullable = false)
    private String nicknameFacebook;


    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="id_adoptante")
    private List<Adopcion> adopciones;


    public Adoptante(@Size(max = 10) String cedula, String nombre, String apellido, String direccion, String telefono,
        String telefonoFamiliar, String celular, String correo, String nicknameFacebook) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.celular = celular;
        this.correo = correo;
        this.telefonoFamiliar = telefonoFamiliar;
        this.nicknameFacebook = nicknameFacebook;
    }
}
