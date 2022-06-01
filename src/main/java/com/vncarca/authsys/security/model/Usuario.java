package com.vncarca.authsys.security.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vncarca.arcasys.persona.model.Persona;
import com.vncarca.authsys.security.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true, nullable = false)
    private String username;

    @Email
    @Column(length = 100, unique = true, nullable = false)
    private String email;

    @Column(length = 200, nullable = false)
    private String password;

    @NotNull
    private Boolean enabled;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Persona persona;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id", updatable = true), inverseJoinColumns = @JoinColumn(name = "rol_id", updatable = true), uniqueConstraints = {
            @UniqueConstraint(columnNames = { "usuario_id", "rol_id" }) })
    public Set<Rol> roles;

    public UserDto toUserDto() {
        UserDto userDto = new UserDto();
        userDto.setEmail(this.email);
        userDto.setId(this.id);
        return userDto;
    }
}
