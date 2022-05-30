package com.vncarca.arcasys.vacunas.services;

import java.util.List;
import java.util.Optional;

import com.vncarca.arcasys.vacunas.model.Vacuna;

public interface IVacunaService {
    public List<Vacuna>listar();
    public Optional<Vacuna>listarId(Long id);
    public Vacuna save(Vacuna vacuna);
    public void delete(Long id);
    public Vacuna findById(Long id); 
}
