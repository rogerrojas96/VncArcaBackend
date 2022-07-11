package com.vncarca.arcasys.voluntarios.model;

import com.vncarca.arcasys.persona.model.PersonaDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class VoluntarioDto extends PersonaDto implements Serializable {
    
    @NotBlank
    private String actividad;
    
    @NotBlank
    private String tipo;
}
