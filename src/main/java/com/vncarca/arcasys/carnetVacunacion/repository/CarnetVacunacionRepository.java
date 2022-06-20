/**
* Created by Roy Morocho.
* User: steve
* Date: 10 jun 2022
* Time: 14:53:10
*/
package com.vncarca.arcasys.carnetVacunacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vncarca.arcasys.carnetVacunacion.model.CarnetVacunacion;

@Repository
public interface CarnetVacunacionRepository extends JpaRepository<CarnetVacunacion, Long> {
	List<CarnetVacunacion> findByanimalId(Long animalId);
}
