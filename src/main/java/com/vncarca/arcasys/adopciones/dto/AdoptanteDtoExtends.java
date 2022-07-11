package com.vncarca.arcasys.adopciones.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.vncarca.arcasys.persona.model.PersonaDtoExtends;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Roy Morocho.
 * User: steve
 * Date: 05/07/2022
 * Time: 17:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class AdoptanteDtoExtends extends AdoptanteDto implements Serializable {
    private Long id;
    private PersonaDtoExtends persona;
    public AdoptanteDtoExtends(String telefonoFamiliar, String nicknameFacebook, Long id, PersonaDtoExtends persona) {
        super(telefonoFamiliar, nicknameFacebook);
        this.id = id;
        this.persona = persona;
    }
}
