package com.vncarca.arcasys.usuario.model;

import com.vncarca.arcasys.persona.model.PersonaDtoExtends;
import com.vncarca.authsys.security.model.RolDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Roy Morocho.
 * User: steve
 * Date: 05/07/2022
 * Time: 16:50
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioDtoResponse extends UsuarioDtoExtends implements Serializable {
    @NotNull
    private Boolean enabled;

    /**
     * @param id
     * @param username
     * @param password
     * @param persona
     * @param roles
     * @param enabled
     */
    public UsuarioDtoResponse(Long id, String username, String password, PersonaDtoExtends persona, Set<RolDto> roles, Boolean enabled) {
        super(id, username, password, persona, roles);
        this.enabled = enabled;
    }
}
