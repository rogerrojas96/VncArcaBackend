package com.vncarca.authsys.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "usuarios",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "username"),
           @UniqueConstraint(columnNames = "email")
       })
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 10)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    private String nombre;

    @NotBlank
    @Size(min = 7, max = 10)
    private String telefono;

    @NotBlank
    @Size(max = 120)
    private String password;            

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_roles", 
                joinColumns = @JoinColumn(name = "usuario_id"),
                inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();


    public Usuario(String username, String email, String nombre, String telefono, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.telefono = telefono;
    }
}
