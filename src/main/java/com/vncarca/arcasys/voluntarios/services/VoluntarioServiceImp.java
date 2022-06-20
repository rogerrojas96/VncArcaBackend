package com.vncarca.arcasys.voluntarios.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vncarca.arcasys.voluntarios.model.Voluntario;
import com.vncarca.arcasys.voluntarios.repository.VoluntarioRepository;

@Service
public class VoluntarioServiceImp implements VoluntarioService {

    @Autowired
    VoluntarioRepository voluntariorepository;

    @Override
    public Page<Voluntario> findAll(Pageable pageable) {
        return voluntariorepository.findAll(pageable);
    }

    @Override
    public Voluntario findById(Long id) {
        return voluntariorepository.findById(id).orElse(null);
    }

    @Override
    public Voluntario save(Voluntario voluntario) {
        return voluntariorepository.save(voluntario);
    }

    @Override
    public void delete(Long id) {
        voluntariorepository.deleteById(id);
    }
    
}
