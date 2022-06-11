package com.vncarca.arcasys.voluntarios.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vncarca.arcasys.voluntarios.model.Voluntario;

public interface VoluntarioService {
    public Page<Voluntario> findAll(Pageable pageable);
    public Voluntario findById(Long id);
    public Voluntario save(Voluntario voluntario);
    public void delete(Long id);
}
