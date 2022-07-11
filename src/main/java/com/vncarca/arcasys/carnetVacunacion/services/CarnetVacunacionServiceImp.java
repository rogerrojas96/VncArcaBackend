/**
 * Created by Roy Morocho.
 * User: steve
 * Date: 10 jun 2022
 * Time: 14:54:47
 */
package com.vncarca.arcasys.carnetVacunacion.services;

import com.vncarca.arcasys.animal.mapper.AnimalRefugioMapper;
import com.vncarca.arcasys.carnetVacunacion.model.CarnetVacunacion;
import com.vncarca.arcasys.carnetVacunacion.model.CarnetVacunacionDTO;
import com.vncarca.arcasys.carnetVacunacion.repository.CarnetVacunacionRepository;
import com.vncarca.arcasys.carnetVacunacion.vacuna.services.VacunaServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CarnetVacunacionServiceImp implements CarnetVacunacionService {
    @Autowired
    CarnetVacunacionRepository carnetVacunacionRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    VacunaServices vacunaServices;

    @Override
    public Page<CarnetVacunacionDTO> findAll(Pageable pageable) {

        return carnetVacunacionRepository.findAll(pageable).map(this::convertToDto);
    }

    @Override
    public List<CarnetVacunacionDTO> findAll() {
        return carnetVacunacionRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public CarnetVacunacionDTO save(CarnetVacunacionDTO carnetVacunacion) {
        return this.convertToDto(carnetVacunacionRepository.save(this.convertToEntity(carnetVacunacion)));
    }

    @Override
    public void delete(Long id) {
        if (Objects.equals(findById(id).getId(), id))
            carnetVacunacionRepository.deleteById(id);
    }

    @Override
    public CarnetVacunacionDTO findById(Long id) {
        return carnetVacunacionRepository.findById(id).map(this::convertToDto).orElseThrow(() -> new NoSuchElementException(
                "Carnet de vacunación con ID: " + id.toString() + " no existe en el servidor"));
    }

    @Override
    public List<CarnetVacunacionDTO> findByanimalId(Long id) {
        return carnetVacunacionRepository.findByanimalId(id).stream().map(this::convertToDto)
                .collect(Collectors.toList());

    }

    @Override
    public CarnetVacunacionDTO convertToDto(CarnetVacunacion carnetVacunacion) {
        return new CarnetVacunacionDTO(carnetVacunacion.getId(),
                carnetVacunacion.getFechaAplicacion(),
                carnetVacunacion.getFechaProximaAplicacion(),
                vacunaServices.convertToDto(carnetVacunacion.getVacuna()),
                AnimalRefugioMapper.toResponse(carnetVacunacion.getAnimal()));
    }

    @Override
    public CarnetVacunacion convertToEntity(CarnetVacunacionDTO carnetVacunacionDTO) {
        return new CarnetVacunacion(carnetVacunacionDTO.getId(),
                carnetVacunacionDTO.getFechaAplicacion(),
                carnetVacunacionDTO.getFechaProximaAplicacion(),
                vacunaServices.convertToEntity(carnetVacunacionDTO.getVacuna()),
                AnimalRefugioMapper.toAnimal(carnetVacunacionDTO.getAnimal()));
    }
}
