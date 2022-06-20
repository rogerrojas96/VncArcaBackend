/**
* Created by Roy Morocho.
* User: steve
* Date: 10 jun 2022
* Time: 14:54:32
*/
package com.vncarca.arcasys.carnetVacunacion.services;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vncarca.arcasys.carnetVacunacion.model.CarnetVacunacion;
import com.vncarca.arcasys.carnetVacunacion.model.CarnetVacunacionDTO;

public interface CarnetVacunacionService {
	Page<CarnetVacunacion> findAll(Pageable pageable);

	List<CarnetVacunacion> findAll();

	List<CarnetVacunacionDTO> findByanimalId(Long id);

	CarnetVacunacionDTO carnetVacunacionToDTO(CarnetVacunacion carnetVacunacion);

	CarnetVacunacion save(CarnetVacunacion carnetVacunacion);

	void delete(Long id);

	CarnetVacunacion findById(Long id);

}
