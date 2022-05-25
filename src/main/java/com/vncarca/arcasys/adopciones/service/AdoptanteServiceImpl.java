package com.vncarca.arcasys.adopciones.service;

import java.util.List;

import com.vncarca.arcasys.adopciones.dto.AdoptanteDto;
import com.vncarca.arcasys.adopciones.model.Adopcion;
import com.vncarca.arcasys.adopciones.model.Adoptante;
import com.vncarca.arcasys.adopciones.repository.AdoptanteRepository;
import com.vncarca.arcasys.persona.model.Persona;
import com.vncarca.arcasys.persona.repository.PersonaRepository;
import com.vncarca.util.Validacion;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class AdoptanteServiceImpl implements IAdoptanteService{

    @Autowired
    private AdoptanteRepository adoptanteRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private IAdopcionService adopcionService;


    @Override
    public List<Adoptante> getAllAdoptantes() {
        return adoptanteRepository.findAll();
    }


    @Override
    public Adoptante getAdoptantePorCedula(String cedula) {
        Long idAdoptante = getIdAdoptante(cedula);
        if (idAdoptante != null){
            return adoptanteRepository.findById(idAdoptante).get();
        }
        return null;
    }


    @Override
    public Adoptante getAdoptantePorId(Long idAdoptante) {
        return adoptanteRepository.findById(idAdoptante).get();
    }


    @Override
    public Adoptante crearAdoptante(AdoptanteDto adoptanteDto) {
        String identificador = adoptanteDto.getCedula();
        if(Validacion.validadorDeCedula(identificador) == true){
            if(getIdAdoptante(adoptanteDto.getCedula()) == null){
                Persona persona = null;
                if(!personaRepository.existsByCedula(adoptanteDto.getCedula())){
                    persona = new Persona();
                    persona.setApellidos(adoptanteDto.getApellidos());
                    persona.setCedula(adoptanteDto.getCedula());
                    persona.setCelular(adoptanteDto.getCelular());
                    persona.setCorreo(adoptanteDto.getCorreo());
                    persona.setDireccion(adoptanteDto.getDireccion());
                    persona.setNombre(adoptanteDto.getNombre());
                    persona.setTelefono(adoptanteDto.getTelefono());
                    personaRepository.save(persona);
                }else{
                    persona = personaRepository.findByCedula(adoptanteDto.getCedula()).get();
                }
                Adoptante adoptante = new Adoptante();
                adoptante.setNicknameFacebook(adoptanteDto.getNicknameFacebook());
                adoptante.setTelefonoFamiliar(adoptanteDto.getTelefonoFamiliar());
                adoptante.setPersona(persona);
                return adoptanteRepository.save(adoptante);
            }
        }
        return null;
    }


    @Override
    public Adoptante actualizarAdoptante(AdoptanteDto adoptanteDto, Long idAdoptante) {
        if(adoptanteRepository.existsById(idAdoptante)){
            Adoptante adoptante = adoptanteRepository.findById(idAdoptante).get();
            
            Persona persona = adoptante.getPersona();
            persona.setApellidos(adoptanteDto.getApellidos());
            persona.setCedula(adoptanteDto.getCedula());
            persona.setCelular(adoptanteDto.getCelular());
            persona.setCorreo(adoptanteDto.getCorreo());
            persona.setDireccion(adoptanteDto.getDireccion());
            persona.setNombre(adoptanteDto.getNombre());
            persona.setTelefono(adoptanteDto.getTelefono());
            personaRepository.save(persona);
            
            adoptante.setNicknameFacebook(adoptanteDto.getNicknameFacebook());
            adoptante.setTelefonoFamiliar(adoptanteDto.getTelefonoFamiliar());
            adoptante.setPersona(persona);

            return adoptanteRepository.save(adoptante);
        }
        return null;
    }

    @Override
    public boolean eliminarAdoptante(Long idAdoptante) {
        if(adoptanteRepository.existsById(idAdoptante)){
            List<Adopcion> adopciones = adopcionService.getAdopcionesPorIdAdoptante(idAdoptante);
            for(Adopcion adopcion : adopciones){
                adopcionService.eliminarAdopcion(adopcion.getId());
            }
            adoptanteRepository.deleteById(idAdoptante);
            return true;
        }
        return false;
    }
    

    @Override
    public Long getIdAdoptante(String cedula){
        if (personaRepository.existsByCedula(cedula)){
            Persona persona = personaRepository.findByCedula(cedula).get();
            return adoptanteRepository.getId(persona.getId());
        }
        return null;
    }
}
