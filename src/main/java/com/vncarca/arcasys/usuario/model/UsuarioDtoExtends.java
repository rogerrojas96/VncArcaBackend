package com.vncarca.arcasys.usuario.model;

import com.vncarca.arcasys.persona.model.PersonaDtoExtends;
import com.vncarca.authsys.security.model.RolDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Roy Morocho.
 * User: steve
 * Date: 05/07/2022
 * Time: 16:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDtoExtends extends UsuarioDto implements Serializable {
    @NotNull
    private Set<RolDto> roles;
    /**
     * @param id
     * @param username
     * @param password
     * @param persona
     * @param roles
     */
    public UsuarioDtoExtends(Long id, @NotBlank @NotNull String username, @NotBlank @NotNull String password, @NotNull PersonaDtoExtends persona, Set<RolDto> roles) {
        super(id, username, password, persona);
        this.roles = roles;
    }
}
