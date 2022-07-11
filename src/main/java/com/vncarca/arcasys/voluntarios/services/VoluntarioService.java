package com.vncarca.arcasys.voluntarios.services;


import com.vncarca.arcasys.voluntarios.model.VoluntarioDto;
import com.vncarca.arcasys.voluntarios.model.VoluntarioDtoExtends;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VoluntarioService {
    public Page<VoluntarioDtoExtends> findAll(Pageable pageable);
    
    public VoluntarioDtoExtends findById(Long id);
    
    public VoluntarioDtoExtends save(VoluntarioDto voluntarioDto);
    
    public void delete(Long id);
    
    public VoluntarioDtoExtends findByCedula(String cedulaPersona);
    
    public VoluntarioDtoExtends update(VoluntarioDto voluntarioDto, Long idVoluntario);
}
