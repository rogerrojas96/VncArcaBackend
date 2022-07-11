package com.vncarca.arcasys.veterinario.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vncarca.arcasys.persona.model.PersonaDto;
import com.vncarca.arcasys.persona.model.PersonaDtoExtends;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Roy Morocho.
 * User: steve
 * Date: 04/07/2022
 * Time: 17:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeterinarioDTO implements Serializable {
    private Long id;
    @NotBlank
    @NotNull
    private String cargo;
    @JsonProperty(required = true)
    private PersonaDtoExtends persona;
}
