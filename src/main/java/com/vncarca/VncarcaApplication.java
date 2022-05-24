package com.vncarca;

//import com.vncarca.arcasys.adopciones.repository.AdopcionRepository;
import com.vncarca.arcasys.adopciones.repository.AdoptanteRepository;
import com.vncarca.arcasys.animal.repository.AnimalRepository;
import com.vncarca.arcasys.persona.repository.PersonaRepository;
import com.vncarca.temporal_utils.adopciones.AdopcionTemporalUtil;
import com.vncarca.temporal_utils.refugio.RefugioTemporalUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class VncarcaApplication implements CommandLineRunner{

	@Autowired
    private PersonaRepository personaRepository;
    
    @Autowired
    private AdoptanteRepository adoptanteRepository;

	@Autowired
	private AnimalRepository animalRepository;

	//@Autowired
	//private AdopcionRepository adopcionRepository;


	public static void main(String[] args) {
		SpringApplication.run(VncarcaApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOriginPatterns("*")
						.allowedMethods("*")
						.allowedHeaders("*")
						.allowedOrigins("http://localhost:4200", "http://localhost:9898/**").maxAge(3600);
			}
		};
	}

	@Override
	public void run(String... args) throws Exception {

		AdopcionTemporalUtil adopcionTemporal = new AdopcionTemporalUtil(personaRepository, adoptanteRepository, animalRepository);
		RefugioTemporalUtil refugioTemporalUtil = new RefugioTemporalUtil();

		/*Creando Adoptantes*/
		adopcionTemporal.crearAdoptantes();


		/*Creando Animales*/
		refugioTemporalUtil.crearAnimales(animalRepository);


		/*Creando Adopciones*/
		adopcionTemporal.crearAdopcion((long) 1, (long) 1);
		adopcionTemporal.crearAdopcion((long) 2, (long) 2);
	}
}
