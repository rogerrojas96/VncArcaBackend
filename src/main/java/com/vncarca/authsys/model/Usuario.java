package com.vncarca.authsys.model;

import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.vncarca.arcasys.persona.model.Persona;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "usuarios",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "username")

       })
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 10)
    private String username;


    @NotBlank
    @Size(max = 120)
    private String password;            

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_roles", 
                joinColumns = @JoinColumn(name = "usuario_id"),
                inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles;

    @OneToOne
    @JoinColumn(name="id_persona")
    private Persona persona;
    

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
