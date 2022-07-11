/**
* Created by Roy Morocho.
* User: steve
* Date: 10 jun 2022
* Time: 14:54:32
*/
package com.vncarca.arcasys.carnetVacunacion.services;

import java.util.List;

import com.vncarca.arcasys.globalService.GlobalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vncarca.arcasys.carnetVacunacion.model.CarnetVacunacion;
import com.vncarca.arcasys.carnetVacunacion.model.CarnetVacunacionDTO;

public interface CarnetVacunacionService extends GlobalService<CarnetVacunacionDTO,CarnetVacunacion> {
	Page<CarnetVacunacionDTO> findAll(Pageable pageable);

	List<CarnetVacunacionDTO> findAll();

	List<CarnetVacunacionDTO> findByanimalId(Long id);

	CarnetVacunacionDTO save(CarnetVacunacionDTO carnetVacunacion);

	void delete(Long id);

	CarnetVacunacionDTO findById(Long id);

}
