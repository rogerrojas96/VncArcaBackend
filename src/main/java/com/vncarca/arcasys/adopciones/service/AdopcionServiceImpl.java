package com.vncarca.arcasys.adopciones.service;

import com.vncarca.arcasys.adopciones.dto.AdopcionDto;
import com.vncarca.arcasys.adopciones.dto.AdopcionDtoExtends;
import com.vncarca.arcasys.adopciones.model.Adopcion;
import com.vncarca.arcasys.adopciones.model.Adoptante;
import com.vncarca.arcasys.adopciones.model.AdoptanteExtendsMapper;
import com.vncarca.arcasys.adopciones.repository.AdopcionRepository;
import com.vncarca.arcasys.adopciones.repository.AdoptanteRepository;
import com.vncarca.arcasys.adopciones.repository.SeguimientoAdopcionRepository;
import com.vncarca.arcasys.animal.mapper.AnimalRefugioMapper;
import com.vncarca.arcasys.animal.model.AnimalRefugio;
import com.vncarca.arcasys.animal.repository.AnimalRefugioRepository;
import com.vncarca.arcasys.enums.Types;
import com.vncarca.arcasys.persona.repository.PersonaRepository;
import com.vncarca.arcasys.persona.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdopcionServiceImpl implements IAdopcionService {

    private final AdopcionRepository adopcionRepository;
    @Autowired
    private AnimalRefugioRepository animalRepository;
    @Autowired
    private AdoptanteRepository adoptanteRepository;
    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private SeguimientoAdopcionRepository seguimientoRepository;

    @Autowired
    PersonaService personaService;

    public AdopcionServiceImpl(AdopcionRepository adopcionRepository) {
        this.adopcionRepository = adopcionRepository;
    }

    @Override
    public List<AdopcionDtoExtends> getAllAdopciones() {
        return adopcionRepository.findAll().stream().map(this::convertToDtoExtends).collect(Collectors.toList());
    }

    @Override
    public List<AdopcionDtoExtends> getAdopcionesPorCedulaAdoptante(String cedula) {
        Long idAdoptante = adoptanteRepository.getId(personaRepository.findByCedula(cedula).orElseThrow(() -> new NoSuchElementException(
                "Adopcion con cédula: " + cedula + " no existe en el servidor")).getId());
        if (idAdoptante != null) {
            return getAdopcionesPorIdAdoptante(idAdoptante);
        }
        return null;
    }

    @Override
    public List<AdopcionDtoExtends> getAdopcionesPorIdAdoptante(Long idAdoptante) {
        return adopcionRepository.findAdopcionsByAdoptante_Id(idAdoptante).stream().filter(Objects::nonNull).map(this::convertToDtoExtends).collect(Collectors.toList());
    }

    @Override
    public AdopcionDtoExtends getAdopcionPorId(Long idAdopcion) {
        if (adopcionRepository.existsById(idAdopcion)) {
            return convertToDtoExtends(adopcionRepository.findById(idAdopcion).get());
        }
        return null;
    }

    @Override
    public AdopcionDtoExtends crearAdocion(AdopcionDto adopcionDto, Long idAdoptante, Long idAnimal) {
        AnimalRefugio animal = animalRepository.findById(idAnimal).orElse(null);
        Adoptante adoptante = adoptanteRepository.findById(idAdoptante).orElse(null);
        if (adoptante != null && animal != null && !adopcionRepository.existsByAnimal(animal)) {
            animal.setAdoptado(true);
            animal.setLugarEstancia(Types.ESTANCIA.HOGAR.toString());
            animal = animalRepository.save(animal);
            Adopcion adopcion = new Adopcion();
            adopcion.setAdoptante(adoptante);
            adopcion.setAnimal(animal);
            adopcion.setDescripcion(adopcionDto.getDescripcion());
            adopcion.setFechaAdopcion(getDate(adopcionDto.getFechaAdopcion()));
            return convertToDtoExtends(adopcionRepository.save(adopcion));
        }
        return null;
    }

    @Override
    public boolean eliminarAdopcion(Long idAdopcion) {
        if(adopcionRepository.existsById(idAdopcion)) {
            Adopcion adopcion = adopcionRepository.findById(idAdopcion).get();
            AnimalRefugio animal = adopcion.getAnimal();
            animal.setAdoptado(false);
            animal.setLugarEstancia(Types.ESTANCIA.REFUGIO.toString());
/*            animal = animalRepository.save(animal);
            List<SeguimientoAdopcion> seguimientos =  seguimientoRepository.getSeguimientosPorIdAdopcion(idAdopcion);
            if(seguimientos != null){
                for(SeguimientoAdopcion seguimiento  : seguimientos){
                    seguimientoRepository.deleteById(seguimiento.getId());
                }
            }*/
            adopcionRepository.deleteById(idAdopcion);
            return true;
        }
        return false;
    }

    @Override
    public AdopcionDtoExtends actualizarAdocion(AdopcionDto adopcionDto, Long idAdopcion, Long idAnimal) {
        Adopcion adopcion = null;
        AnimalRefugio animal = null;

        if (adopcionRepository.existsById(idAdopcion)) {
            adopcion = adopcionRepository.findById(idAdopcion).get();
            animal = adopcion.getAnimal();
            if (idAnimal != animal.getId() && !adopcionRepository.existsByAnimal(
                    animalRepository.findById(idAnimal).orElse(null))) {
                return null;
            }
            if(idAnimal != animal.getId()){
                animal = animalRepository.findById(idAnimal).orElse(null);
            }
            if(animal != null){
                adopcion.setAnimal(animal);
                adopcion.setDescripcion(adopcionDto.getDescripcion());
                adopcion.setFechaAdopcion(getDate(adopcionDto.getFechaAdopcion()));
                return convertToDtoExtends(adopcionRepository.save(adopcion));
            }
        }
        return null;
    }


    private Date getDate(String fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formato.parse(fecha);
        } catch (ParseException e) {
            return new Date();
        }
    }

    @Override
    public AdopcionDtoExtends convertToDtoExtends(Adopcion a) {
        return new AdopcionDtoExtends(a.getId(), a.getFechaAdopcion(), a.getDescripcion(), AdoptanteExtendsMapper.getAdoptanteDtoExtends(a.getAdoptante(), personaService), AnimalRefugioMapper.toResponse(a.getAnimal()));
    }
}