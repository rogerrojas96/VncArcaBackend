package com.vncarca.arcasys.medicamento.services;

import com.vncarca.arcasys.medicamento.model.Medicamento;
import com.vncarca.arcasys.medicamento.model.MedicamentoDto;
import com.vncarca.arcasys.medicamento.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class MedicamentoServiceImp implements MedicamentoService {
    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Override
    public Page<MedicamentoDto> findAll(Pageable pageable) {
        return medicamentoRepository.findAll(pageable).map(this::convertToDto);

    }

    @Override
    public MedicamentoDto save(MedicamentoDto medicamento) {
        return convertToDto(medicamentoRepository.save(convertToEntity(medicamento)));
    }

    @Override
    public MedicamentoDto findById(Long id) {
        return medicamentoRepository.findById(id).map(this::convertToDto).orElseThrow(() -> new NoSuchElementException(
                "Medicamento con ID: " + id.toString() + " no existe en el servidor"));

    }

    @Override
    public void delete(Long id) {
        medicamentoRepository.deleteById(id);
    }

    @Override
    public Page<MedicamentoDto> findAllByNombreComercialContainingOrNombreGenericoContaining(Pageable pageable, String nombreComercial, String nombreGenerico) {
        return medicamentoRepository.findAllByNombreComercialContainingOrNombreGenericoContaining(pageable, nombreComercial, nombreGenerico).map(this::convertToDto);
    }

    @Override
    public List<MedicamentoDto> findAll() {
        return medicamentoRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<MedicamentoDto> findByNombreComercialContainingOrNombreGenericoContaining(String nombreComercial,String nombreGenerico) {
        return medicamentoRepository.findByNombreComercialContainingOrNombreGenericoContaining(nombreComercial, nombreGenerico).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public MedicamentoDto convertToDto(Medicamento medicamento) {
        return new MedicamentoDto(medicamento.getId(), medicamento.getNombreComercial(), medicamento.getNombreGenerico(), medicamento.getCantidad(), medicamento.getPrecio());
    }

    @Override
    public Medicamento convertToEntity(MedicamentoDto medicamentoDto) {
        return new Medicamento(medicamentoDto.getId(), medicamentoDto.getNombreComercial(), medicamentoDto.getNombreGenerico(), medicamentoDto.getCantidad(), medicamentoDto.getPrecio());
    }
}
