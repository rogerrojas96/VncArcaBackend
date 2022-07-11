package com.vncarca.arcasys.adopciones.service;

import com.vncarca.arcasys.adopciones.dto.AdopcionDtoExtends;
import com.vncarca.arcasys.adopciones.dto.AdoptanteDto;
import com.vncarca.arcasys.adopciones.dto.AdoptanteDtoExtends;
import com.vncarca.arcasys.adopciones.model.Adoptante;
import com.vncarca.arcasys.adopciones.model.AdoptanteExtendsMapper;
import com.vncarca.arcasys.adopciones.repository.AdoptanteRepository;
import com.vncarca.arcasys.persona.model.Persona;
import com.vncarca.arcasys.persona.repository.PersonaRepository;
import com.vncarca.arcasys.persona.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Component
public class AdoptanteServiceImpl implements IAdoptanteService{

    @Autowired
    private AdoptanteRepository adoptanteRepository;

    @Autowired
    private PersonaRepository personaRepository;


    @Autowired
    private IAdopcionService adopcionService;

    @Autowired
    PersonaService personaService;

    @Override
    public List<AdoptanteDtoExtends> getAllAdoptantes() {
        return adoptanteRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }


    @Override
    public AdoptanteDtoExtends getAdoptantePorCedula(String cedula) {
        Long idAdoptante = getIdAdoptante(cedula);
        if (idAdoptante != null){
            return convertToDto(adoptanteRepository.findById(idAdoptante).get());
        }
        return null;
    }


    @Override
    public AdoptanteDtoExtends getAdoptantePorId(Long idAdoptante) {
        return convertToDto(adoptanteRepository.findById(idAdoptante).get());
    }


    @Override
    public AdoptanteDtoExtends crearAdoptante(AdoptanteDto adoptanteDto) {
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
            return convertToDto(adoptanteRepository.save(adoptante));
        }
        return null;
    }


    @Override
    public AdoptanteDtoExtends actualizarAdoptante(AdoptanteDto adoptanteDto, Long idAdoptante) {
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

            return convertToDto(adoptanteRepository.save(adoptante));
        }
        return null;
    }

    @Override
    public boolean eliminarAdoptante(Long idAdoptante) {
        if(adoptanteRepository.existsById(idAdoptante)){
//            List<AdopcionDtoExtends> adopciones = adopcionService.getAdopcionesPorIdAdoptante(idAdoptante);
//            for(AdopcionDtoExtends adopcion : adopciones){
//                adopcionService.eliminarAdopcion(adopcion.getId());
//            }
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
    @Override
    public AdoptanteDtoExtends convertToDto(Adoptante a) {
        return AdoptanteExtendsMapper.getAdoptanteDtoExtends(a,personaService);
    }
}
