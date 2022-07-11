package com.vncarca.arcasys.adopciones.model;

import com.vncarca.arcasys.adopciones.dto.AdoptanteDtoExtends;
import com.vncarca.arcasys.persona.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Roy Morocho.
 * User: steve
 * Date: 06/07/2022
 * Time: 17:23
 */
public class AdoptanteExtendsMapper {
    public static AdoptanteDtoExtends getAdoptanteDtoExtends(Adoptante a, PersonaService personaService){
        return new AdoptanteDtoExtends(a.getTelefonoFamiliar(),a.getNicknameFacebook(),a.getId(),personaService.convertToDto(a.getPersona())) ;
    }
}
