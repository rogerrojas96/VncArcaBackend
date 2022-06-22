package com.vncarca.arcasys.voluntarios.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vncarca.arcasys.voluntarios.model.Voluntario;
import com.vncarca.arcasys.voluntarios.model.VoluntarioDto;

public interface VoluntarioService {
    public Page<Voluntario> findAll(Pageable pageable);
    public Voluntario findById(Long id);
    public Voluntario save(VoluntarioDto voluntarioDto, Long idPersona);
    public void delete(Long id);
    public Voluntario findByCedula(String cedulaPersona);
    public Voluntario update(VoluntarioDto voluntarioDto, Long idVoluntario);
}
