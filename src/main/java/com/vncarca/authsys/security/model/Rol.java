package com.vncarca.authsys.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
@Where(clause = "deleted=false")
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE roles SET deleted = true WHERE id=?")
public class Rol implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, unique = true)
    @NotNull
    @NotBlank
    private String nombre;

    @NotNull
    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean deleted=Boolean.FALSE;

    /**
     * @param id
     * @param nombre
     */
    public Rol(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    public GrantedAuthority grantedAuthority() {
        return new SimpleGrantedAuthority(this.nombre);
    }
    
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "roles")
    private List<Usuario> usuarios;
}
