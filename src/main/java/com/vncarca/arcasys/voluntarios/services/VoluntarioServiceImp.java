package com.vncarca.arcasys.voluntarios.services;

import org.aspectj.weaver.patterns.PerObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vncarca.arcasys.persona.model.Persona;
import com.vncarca.arcasys.persona.repository.PersonaRepository;
import com.vncarca.arcasys.voluntarios.model.Voluntario;
import com.vncarca.arcasys.voluntarios.model.VoluntarioDto;
import com.vncarca.arcasys.voluntarios.repository.VoluntarioRepository;

@Service
public class VoluntarioServiceImp implements VoluntarioService {

    @Autowired
    VoluntarioRepository voluntariorepository;

    @Autowired
    private PersonaRepository personaRepository;
    
    @Override
    public Page<Voluntario> findAll(Pageable pageable) {
        return voluntariorepository.findAll(pageable);
    }

    @Override
    public Voluntario findById(Long id) {
        return voluntariorepository.findById(id).orElse(null);
    }

    

    @Override
    public void delete(Long id) {
        voluntariorepository.deleteById(id);
    }

    @Override
    public Voluntario findByCedula(String cedulaPersona) {
        
        if(personaRepository.existsByCedula(cedulaPersona)){
            Persona persona = personaRepository.findByCedula(cedulaPersona).get();
            Voluntario voluntario = voluntariorepository.findByPersona(persona).orElse(null);
            return voluntario;
        }
        return null;
    }

    @Override
    public Voluntario save(VoluntarioDto voluntarioDto, Long idPersona) {
        Persona persona = personaRepository.findById(idPersona).orElse(null);
        if (persona != null) {
            Voluntario voluntario = new Voluntario();
            voluntario.setActividad(voluntarioDto.getActividad());
            voluntario.setPersona(persona);
            voluntario.setTipo(voluntarioDto.getTipo());
            return voluntariorepository.save(voluntario);
        }
        return null;
    }

    @Override
    public Voluntario update(VoluntarioDto voluntarioDto, Long idVoluntario) {
        Voluntario voluntario = voluntariorepository.findById(idVoluntario).orElse(null);
        if (voluntario != null) {
            voluntario.setActividad(voluntarioDto.getActividad());
            voluntario.setTipo(voluntarioDto.getTipo());
            return voluntariorepository.save(voluntario);
        }
        return null;
    }
    
}
