package com.vncarca.arcasys.medicamento.controllers;

import com.vncarca.arcasys.medicamento.model.MedicamentoDto;
import com.vncarca.arcasys.medicamento.services.MedicamentoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api(tags = "Medicamentos", description = "Controlador para CRUD de medicamentos")
@RestController
@RequestMapping("/medicamentos")

public class MedicamentoController {
	@Autowired
	MedicamentoService medicamentoService;

    @ResponseBody
    @GetMapping("/page")
	public Page<MedicamentoDto> getMedicamentos(@RequestParam(required = true) Integer page,
                                                @RequestParam(required = true) Integer size, @RequestParam(required = false, defaultValue = "") String nombre){
    	Pageable pageable = PageRequest.of(page, size);
        Page<MedicamentoDto> pageMedicamento=null;

        if (nombre.isEmpty() || nombre==null) {
			pageMedicamento = medicamentoService.findAll(pageable);
		} else {
			pageMedicamento = medicamentoService.findAllByNombreComercialContainingOrNombreGenericoContaining(pageable, nombre.toUpperCase(),nombre.toUpperCase());
		}
    	return pageMedicamento;
    }

    // Trae a todos los medicamentos
    @GetMapping("/")
	public List<MedicamentoDto> getMedicamentos(){
		 return medicamentoService.findAll();
	}

    //Buscar medicamento por nombre
    @GetMapping("/find")
	public List<MedicamentoDto> getMedicamentosByNombres(@RequestParam(required = false ,defaultValue = "",name = "nombre") String nombre ){

        if (nombre.isEmpty() || nombre==null) {
		 return medicamentoService.findAll();
		} else {
		 return medicamentoService.findByNombreComercialContainingOrNombreGenericoContaining(nombre.toUpperCase(),nombre.toUpperCase());
		}
	}

    //EndPoint crear tratamiento
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody MedicamentoDto medicamento, BindingResult result){
    	Map<String, Object> response = new HashMap<>();
        MedicamentoDto newMedicamento = null;
    	if (result.hasErrors()) {
    		List<String> errors = result.getFieldErrors().stream().map(err -> {
    			 return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
            }).collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

    	}
        try {
            newMedicamento = medicamentoService.save(medicamento);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al guardar medicamento en el servidor");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Medicamento guardada con ??xito");
        response.put("medicamento", newMedicamento);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }





    // EndPoint Actualizar Medicamento
    @ResponseBody
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody MedicamentoDto medicamento, BindingResult result,
            @PathVariable Long id) {
        MedicamentoDto med = medicamentoService.findById(id);

        MedicamentoDto medicamentoUpdate = null;

        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err -> {
                return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
            }).collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (med == null) {
            response.put("mensaje", "Error al actualizar, el Medicamento med ID: ".concat(id.toString())
                    .concat(" no existe en el servidor"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            med = medicamento;

            medicamentoUpdate = medicamentoService.save(med);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el Medicamento en el servidor");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Medicamento actualizado med exito");
        response.put("medicamento", medicamentoUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    // EndPoint Eliminar Medicamento
    @ResponseBody
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            medicamentoService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el Animal en el servidor");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Medicamento eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    // EndPoint Buscar por ID
    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        MedicamentoDto medicamento = null;
        Map<String, Object> response = new HashMap<>();

        try {
            medicamento = medicamentoService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error en la consulta de medicamento en el servidor");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (medicamento == null) {
            response.put("mensaje", "El medicamento con ID: ".concat(id.toString()).concat(" no existe en el servidor ni en ni una parte porfavor revise bien :)"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<MedicamentoDto>(medicamento, HttpStatus.OK);
    }

}
