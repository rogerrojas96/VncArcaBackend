package com.vncarca.arcasys.adopciones.repository;

import com.vncarca.arcasys.adopciones.model.Adopcion;
import com.vncarca.arcasys.animal.model.AnimalRefugio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdopcionRepository extends JpaRepository<Adopcion, Long> {

	//    @Query(value = "Select * from adopciones where adopciones.id_adoptante = :idAdoptante", nativeQuery = true)
	//EL native query no hace referencia a cuando hay objetos eliminados con el metodo de softDelete por lo que lanzara una excepcion, en su defecto se podria usar el JPQL O  Derived Query Methods
	public List<Adopcion> findAdopcionsByAdoptante_Id(Long idAdoptante);

	public boolean existsByAnimal(AnimalRefugio animal);
}