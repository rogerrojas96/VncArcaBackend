package com.vncarca.arcasys.adopciones.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.vncarca.arcasys.adopciones.dto.AdopcionDto;
import com.vncarca.arcasys.adopciones.model.Adopcion;
import com.vncarca.arcasys.adopciones.model.Adoptante;
import com.vncarca.arcasys.adopciones.repository.AdopcionRepository;
import com.vncarca.arcasys.adopciones.repository.AdoptanteRepository;
import com.vncarca.arcasys.animal.model.Animal;
import com.vncarca.arcasys.animal.repository.AnimalRepository;
import com.vncarca.arcasys.persona.repository.PersonaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdopcionServiceImpl implements IAdopcionService{
    
    @Autowired
    private AdopcionRepository adopcionRepository;

    @Autowired
    private AnimalRepository animalRepository;


    @Autowired
    private AdoptanteRepository adoptanteRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public Page<Adopcion> getAllAdopciones(Pageable pageable) {
        return adopcionRepository.findAll(pageable);
    }

    @Override
    public List<Adopcion> getAdopcionesPorCedulaAdoptante(String cedula) {
        Long idAdoptante = adoptanteRepository.getId(personaRepository.findByCedula(cedula).orElse(null).getId());
        if(idAdoptante != null){
            return getAdopcionesPorIdAdoptante(idAdoptante);
        }
        return null;
    }

    @Override
    public List<Adopcion> getAdopcionesPorIdAdoptante(Long idAdoptante) {
        return adopcionRepository.getAdopcionesPorIdAdoptante(idAdoptante);
    }

    @Override
    public Adopcion getAdopcionPorId(Long idAdopcion) {
        if(adopcionRepository.existsById(idAdopcion)){
            return adopcionRepository.findById(idAdopcion).get();
        }
        return null;
    }

    @Override
    public Adopcion crearAdocion(AdopcionDto adopcionDto, Long idAdoptante, Long idAnimal) {
        Animal animal = animalRepository.findById(idAnimal).orElse(null);
        Adoptante adoptante = adoptanteRepository.findById(idAdoptante).orElse(null);
        if(adoptante !=null && animal != null && !adopcionRepository.existsByAnimal(animal)){ 
            Adopcion adopcion = new Adopcion();
            adopcion.setAdoptante(adoptante);
            adopcion.setAnimal(animal);
            adopcion.setDescripcion(adopcionDto.getDescripcion());
            adopcion.setFechaAdopcion(getDate(adopcionDto.getFechaAdopcion()));
            return adopcionRepository.save(adopcion);
        }
        return null;
    }

    @Override
    public boolean eliminarAdopcion(Long idAdopcion) {
        if(adopcionRepository.existsById(idAdopcion)){
            adopcionRepository.deleteById(idAdopcion);
            return true;
        }
        return false;
    }

    @Override
    public Adopcion actualizarAdocion(AdopcionDto adopcionDto, Long idAdopcion, Long idAnimal) {
        Adopcion adopcion = null;
        Animal animal = null;

        if (adopcionRepository.existsById(idAdopcion)){
            adopcion = adopcionRepository.getById(idAdopcion);
            animal = adopcion.getAnimal();
            if(idAnimal != animal.getId() && !adopcionRepository.existsByAnimal(
                animalRepository.findById(idAnimal).orElse(null))){
                    return null;
            }
            if(idAnimal != animal.getId()){
                animal = animalRepository.findById(idAnimal).orElse(null);
            }
            if(animal != null){
                adopcion.setAnimal(animal);
                adopcion.setDescripcion(adopcionDto.getDescripcion());
                adopcion.setFechaAdopcion(getDate(adopcionDto.getFechaAdopcion()));
                return adopcionRepository.save(adopcion);
            }
        }
        return null;
    }



    private Date getDate(String fecha){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try{
            return formato.parse(fecha);
        }catch(ParseException e){
            return new Date();
        }
    }
}
