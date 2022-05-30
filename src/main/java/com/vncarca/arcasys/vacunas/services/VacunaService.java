package com.vncarca.arcasys.vacunas.services;

import java.util.List;
import java.util.Optional;

import com.vncarca.arcasys.vacunas.model.Vacuna;
import com.vncarca.arcasys.vacunas.repository.VacunaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VacunaService implements IVacunaService{


    @Autowired
    private VacunaRepository data;

    @Override
    public List<Vacuna> listar() {
        
        return (List<Vacuna>)data.findAll();
    }

    @Override
    public Optional<Vacuna> listarId(Long id) {
        return data.findById(id);
    }

    @Override
    public Vacuna save(Vacuna v) {

        return data.save(v);
    }

    @Override
    public void delete(Long id) {
        data.deleteById(id);
    }

    @Override
    public Vacuna findById(Long id) {
        return data.findById(id).orElse(null);
    }
    
}
