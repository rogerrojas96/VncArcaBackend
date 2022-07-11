package com.vncarca.arcasys.usuario.model;

import com.vncarca.arcasys.persona.model.PersonaDto;
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
 * Date: 04/07/2022
 * Time: 18:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto implements Serializable {
    private Long id;
    @NotBlank
    @NotNull
    private String username;
    @NotBlank
    @NotNull
    private String password;
    @NotNull
    private PersonaDtoExtends persona;
}
