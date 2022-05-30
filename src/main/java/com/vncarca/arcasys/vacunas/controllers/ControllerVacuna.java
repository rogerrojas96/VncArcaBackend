package com.vncarca.arcasys.vacunas.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.vncarca.arcasys.vacunas.model.Vacuna;
import com.vncarca.arcasys.vacunas.services.IVacunaService;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
@Controller
@RequestMapping("/vacunas")
public class ControllerVacuna {
    
    private IVacunaService service;


    @GetMapping("/eliminarVacuna/{id}") 
    public String elimnarVacuna(Model model, @PathVariable Long id){
        service.delete(id);
        return "reditect:/listarVacunas";
    }

    //EndPoint para registrar Vacuna
    @PostMapping("/crearVacuna")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> crearVacuna(@Valid @RequestBody Vacuna vacuna, BindingResult result){

        Map<String, Object> response = new HashMap<>();
        Vacuna newVacuna = null;

        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream().map(err ->{return "El campo '"+err.getField()+" "+err.getDefaultMessage();}).collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try{
            newVacuna = service.save(vacuna);
        }catch(DataAccessException e){
            response.put("mensaje","Error al guardar vacuna en el servidor");
            response.put("error",e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","Vacuna guardad con exito");
        response.put("vacuna", newVacuna);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    // EndPonit para eliminar vacuna
    @ResponseBody
    @DeleteMapping("/{id}") 
    public ResponseEntity<?> deletVacuna(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        try{
            service.delete(id);
        } catch (DataAccessException e){
            response.put("mensaje", "Error al eliminar la vacuna del servidor");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Vacuna elimindad con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }


    //EndPoint para buscar por ID de vacunasS
    @ResponseBody
	@GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Vacuna vacuna = null;
        Map<String, Object> response = new HashMap<>();

        try{
            vacuna = service.findById(id);
        }catch (DataAccessException e){
            response.put("mensaje", "Error en la consulta de Vacuna en el servidor");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(vacuna == null){
            response.put("mensaje ","La vacuna con ID: ".concat(id.toString().concat("no existe en el servidor")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Vacuna>(vacuna, HttpStatus.OK);
    }

}
