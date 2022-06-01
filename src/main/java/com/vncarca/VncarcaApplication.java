package com.vncarca;

import com.vncarca.arcasys.adopciones.repository.AdoptanteRepository;
import com.vncarca.arcasys.animal.repository.AnimalRepository;
import com.vncarca.arcasys.persona.repository.PersonaRepository;
import com.vncarca.temporal_utils.adopciones.AdopcionTemporalUtil;
import com.vncarca.temporal_utils.refugio.RefugioTemporalUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VncarcaApplication implements CommandLineRunner {

	@Autowired
	private PersonaRepository personaRepository;

	@Autowired
	private AdoptanteRepository adoptanteRepository;

	@Autowired
	private AnimalRepository animalRepository;

	public static void main(String[] args) {
		SpringApplication.run(VncarcaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		AdopcionTemporalUtil adopcionTemporal = new AdopcionTemporalUtil(personaRepository, adoptanteRepository);
		RefugioTemporalUtil refugioTemporalUtil = new RefugioTemporalUtil();

		/* Creando Adoptantes */
		adopcionTemporal.crearAdoptantes();

		/* Creando Animales */
		refugioTemporalUtil.crearAnimales(animalRepository);
	}
}
