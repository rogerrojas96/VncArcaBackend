package com.vncarca.temporal_utils.refugio;

import java.util.Date;

import com.vncarca.arcasys.animal.model.Animal;
import com.vncarca.arcasys.animal.repository.AnimalRepository;

public class RefugioTemporalUtil {
    
    public void crearAnimales(AnimalRepository animalRepository){

        Animal animal = new Animal();
        animal.setCaracteristicas("Tiene manchitas negras");
        animal.setColor("negro");
        animal.setEdad(6);
        animal.setEspecie("Gato");
        animal.setFechaNacimiento(new Date());
        animal.setFoto("url");
        animal.setNombre("Paca");
        animal.setPeso(5);
        animal.setRaza("Egipcio");
        animal.setSexo("hembra");
        animal.setTamanyo("mediano");

        Animal animal2 = new Animal();
        animal2.setCaracteristicas("Tiene manchitas negras");
        animal2.setColor("Blanco");
        animal2.setEdad(6);
        animal2.setEspecie("Perro");
        animal2.setFechaNacimiento(new Date());
        animal2.setFoto("url");
        animal2.setNombre("Juanita");
        animal2.setPeso(5);
        animal2.setRaza("Dalmata");
        animal2.setSexo("hembra");
        animal2.setTamanyo("grande");

        Animal animal3 = new Animal();
        animal3.setCaracteristicas("Es medio cojo");
        animal3.setColor("Cafe");
        animal3.setEdad(6);
        animal3.setEspecie("Perro");
        animal3.setFechaNacimiento(new Date());
        animal3.setFoto("url");
        animal3.setNombre("Pulgoso");
        animal3.setPeso(5);
        animal3.setRaza("French");
        animal3.setSexo("Macho");
        animal3.setTamanyo("pequeño");

        Animal animal4 = new Animal();
        animal4.setCaracteristicas("");
        animal4.setColor("blanco");
        animal4.setEdad(6);
        animal4.setEspecie("Gato");
        animal4.setFechaNacimiento(new Date());
        animal4.setFoto("url");
        animal4.setNombre("Tilingo");
        animal4.setPeso(5);
        animal4.setRaza("Gato montes");
        animal4.setSexo("macho");
        animal4.setTamanyo("mediano");

        animalRepository.save(animal);
        animalRepository.save(animal2);
        animalRepository.save(animal3);
        animalRepository.save(animal4);
    }
}
