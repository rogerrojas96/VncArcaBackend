package com.vncarca.arcasys.adopciones.dto;

import com.vncarca.arcasys.persona.model.PersonaDto;
import com.vncarca.arcasys.persona.model.PersonaDtoExtends;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@NoArgsConstructor
@Setter
@Getter
public class AdoptanteDto extends PersonaDto {

    private String telefonoFamiliar;
    private String nicknameFacebook;
    public AdoptanteDto(String telefonoFamiliar, String nicknameFacebook) {
        this.telefonoFamiliar = telefonoFamiliar;
        this.nicknameFacebook = nicknameFacebook;
    }
}
