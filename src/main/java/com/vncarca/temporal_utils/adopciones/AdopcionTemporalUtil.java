package com.vncarca.temporal_utils.adopciones;

import com.vncarca.arcasys.adopciones.model.Adoptante;
import com.vncarca.arcasys.adopciones.repository.AdoptanteRepository;
import com.vncarca.arcasys.persona.model.Persona;
import com.vncarca.arcasys.persona.repository.PersonaRepository;

public class AdopcionTemporalUtil {


    public void crearAdoptantes(PersonaRepository personaRepository, AdoptanteRepository adoptanteRepository){
        
        /* ------------------ Adoptante 1 ------------------ */
        Persona persona = new Persona();
        persona.setApellidos("Torres Sanchez");
        persona.setCedula("0111111111");
        persona.setCelular("0911111111");
        persona.setCorreo("mary@gmail.com");
        persona.setDireccion("Av de las americas y Canton Babahollo");
        persona.setNombre("María Juana");
        persona.setTelefono("0711111");
        personaRepository.save(persona);

        Adoptante adoptante = new Adoptante();
        adoptante.setNicknameFacebook("Mary Juana");
        adoptante.setTelefonoFamiliar("0711111");
        adoptante.setPersona(persona);
        adoptanteRepository.save(adoptante);

         /* ------------------ Adoptante 2 ------------------ */
         Persona persona2 = new Persona();
         persona2.setApellidos("Machado Urgiles");
         persona2.setCedula("0222222222");
         persona2.setCelular("0922222222");
         persona2.setCorreo("pablito@gmail.com");
         persona2.setDireccion("Av Loja sector el Tiempo");
         persona2.setNombre("Pablo Andres");
         persona2.setTelefono("0722222");
         personaRepository.save(persona2);
 
         Adoptante adoptante2 = new Adoptante();
         adoptante2.setNicknameFacebook("Andy Machado");
         adoptante2.setTelefonoFamiliar("0722222");
         adoptante2.setPersona(persona2);
         adoptanteRepository.save(adoptante2);


         /* ------------------ Adoptante 3 ------------------ */
         Persona persona3 = new Persona();
         persona3.setApellidos("Alemán Hurtado");
         persona3.setCedula("0333333333");
         persona3.setCelular("0933333333");
         persona3.setCorreo("alemanp@gmail.com");
         persona3.setDireccion("Redondel de la feria Libre, junto a Megatienda Valverde");
         persona3.setNombre("Pablo Danilo");
         persona3.setTelefono("0733333");
         personaRepository.save(persona3);
 
         Adoptante adoptante3 = new Adoptante();
         adoptante3.setNicknameFacebook("Pablito FB");
         adoptante3.setTelefonoFamiliar("0733333");
         adoptante3.setPersona(persona3);
         adoptanteRepository.save(adoptante3);
    }
}
