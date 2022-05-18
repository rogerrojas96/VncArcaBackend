package com.vncarca.arcasys.adopciones.service;

import com.vncarca.arcasys.adopciones.model.Adopcion;
import com.vncarca.arcasys.adopciones.repository.AdopcionRepository;
import com.vncarca.arcasys.adopciones.repository.AdoptanteRepository;
import com.vncarca.arcasys.animal.repository.AnimalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdopcionService {
    
    @Autowired
    private AdoptanteRepository adoptanteRepository;

    @Autowired
    private AdopcionRepository adopcionRepository;

    @Autowired
    private AnimalRepository animalRepository;

    public void crearAdopcion(Adopcion adopcion){
        String cedula = adopcion.getAdoptante().getCedula();
        Long idAnimal = adopcion.getAnimal().getId();
        if(cedula != null &&  idAnimal != null && !adoptanteRepository.existsByCedula(cedula) && animalRepository.existsById(idAnimal)){
            if(adopcionRepository.getById(idAnimal) == null){
                adopcionRepository.save(adopcion);
            }
        }
    }
}
