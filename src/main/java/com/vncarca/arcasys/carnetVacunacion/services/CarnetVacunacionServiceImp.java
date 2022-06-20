/**
* Created by Roy Morocho.
* User: steve
* Date: 10 jun 2022
* Time: 14:54:47
*/
package com.vncarca.arcasys.carnetVacunacion.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vncarca.arcasys.carnetVacunacion.model.CarnetVacunacion;
import com.vncarca.arcasys.carnetVacunacion.model.CarnetVacunacionDTO;
import com.vncarca.arcasys.carnetVacunacion.repository.CarnetVacunacionRepository;
import com.vncarca.arcasys.carnetVacunacion.vacuna.model.Vacuna;
import com.vncarca.arcasys.carnetVacunacion.vacuna.model.VacunaDTO;

@Service
public class CarnetVacunacionServiceImp implements CarnetVacunacionService {
	@Autowired
	CarnetVacunacionRepository carnetVacunacionRepository;

	@Override
	public Page<CarnetVacunacion> findAll(Pageable pageable) {
		return carnetVacunacionRepository.findAll(pageable);
	}

	@Override
	public List<CarnetVacunacion> findAll() {
		return carnetVacunacionRepository.findAll();
	}

	@Override
	public CarnetVacunacion save(CarnetVacunacion carnetVacunacion) {
		return carnetVacunacionRepository.save(carnetVacunacion);
	}

	@Override
	public void delete(Long id) {
		carnetVacunacionRepository.deleteById(id);

	}

	@Override
	public CarnetVacunacion findById(Long id) {
		return carnetVacunacionRepository.findById(id)
				.orElse(null);
	}

	@Override
	public List<CarnetVacunacionDTO> findByanimalId(Long id) {
		return carnetVacunacionRepository.findByanimalId(id).stream().map(this::carnetVacunacionToDTO)
				.collect(Collectors.toList());

	}

	@Override
	public CarnetVacunacionDTO carnetVacunacionToDTO(CarnetVacunacion carnetVacunacion) {
		CarnetVacunacionDTO carnetVacunacionDTO = new CarnetVacunacionDTO();
		carnetVacunacionDTO.setId(carnetVacunacion.getId());
		carnetVacunacionDTO.setFechaAplicacion(carnetVacunacion.getFechaAplicacion());
		carnetVacunacionDTO.setFechaProximaAplicacion(carnetVacunacion.getFechaProximaAplicacion());
		carnetVacunacionDTO.setVacuna(vacunaToDto(carnetVacunacion.getVacuna()));
		return carnetVacunacionDTO;
	}

	public VacunaDTO vacunaToDto(Vacuna vacuna) {
		VacunaDTO vacunaDTO = new VacunaDTO();
		vacunaDTO.setDescripcion(vacuna.getDescripcion());
		vacunaDTO.setId(vacuna.getId());
		vacunaDTO.setNombre(vacuna.getNombre());
		vacunaDTO.setTipo(vacuna.getTipo());
		return vacunaDTO;
	}

}
