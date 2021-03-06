package com.vncarca.arcasys.medicacion.controllers;

import com.vncarca.arcasys.medicacion.dto.MedicacionDto;
import com.vncarca.arcasys.medicacion.model.MedicacionDtoExtends;
import com.vncarca.arcasys.medicacion.services.IMedicacionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Api(tags = "Medicaciones", description = "Controlador para CRUD medicaciones")
@RestController
@RequestMapping("/medicaciones")



public class MedicacionController {
    @Autowired
    private IMedicacionService medicacionService;
    private Map<String, Object> response = new HashMap<>();
    private MedicacionDtoExtends medicacion;
    private HttpStatus status;
    
    @ResponseBody
    @GetMapping("/")
    public ResponseEntity<List<MedicacionDtoExtends>> getAllMedicacions() {
        return new ResponseEntity<>(medicacionService.getAllMedicacions(), HttpStatus.OK);
    }
    
    @ResponseBody
    @GetMapping("/{idMedicacion}")
    public ResponseEntity<?> getMedicacionPorId(@PathVariable Long idMedicacion){
        response.clear();
        try{
            medicacion = medicacionService.getMedicacionPorId(idMedicacion);
            if(medicacion != null){
                response.put("medicacion", medicacion);
                status = HttpStatus.OK;
            }else{
                response.put("mensaje", "No existe la medicacion: "+ idMedicacion.toString()+" ");
                status = HttpStatus.BAD_REQUEST;
            }
        }catch(DataAccessException e){
            response.put("mensaje", "Ha ocurrido un error en el servidor al intentar crear la medicacion!");
            response.put("error", e.getMostSpecificCause().getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } 
        return new ResponseEntity<>(response, status);
    }

    @GetMapping("/tratamiento/{idTratamiento}")
    public ResponseEntity<?> getMedicacionesPorIdTratamiento(@PathVariable Long idTratamiento){

        return new ResponseEntity<>(medicacionService.getMedicacionPorTratamiento(idTratamiento), HttpStatus.OK);
    }

    
    @ResponseBody
    @PostMapping("/{idMedicamento}/{idTratamiento}")
    public ResponseEntity<?> crearMedicacion(@Valid @RequestBody MedicacionDto medicacionDto, BindingResult result, @PathVariable Long idMedicamento, @PathVariable Long idTratamiento){
        response.clear();
        if(!result.hasErrors()){
            try{
                medicacion = medicacionService.crearMedicacion(medicacionDto, idMedicamento, idTratamiento);
                if(medicacion != null){
                    response.put("mensaje", "medicacion creada con exito!");
                    response.put("medicacion", medicacion);
                    status = HttpStatus.CREATED;
                }else{
                    response.put("mensaje", "No existe el id de medicacion o tratamiento");
                    status = HttpStatus.BAD_REQUEST;
                }
            }catch(DataAccessException e){
                response.put("mensaje", "Ha ocurrido un error en el servidor al intentar crear la medicacion!");
                response.put("error", e.getMostSpecificCause().getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            } 
        }else{
            response.put( "errors", getErrors(result));
             status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<Map<String, Object>>(response, status);
    }


    @ResponseBody
    @DeleteMapping("/{idMedicacion}")
    public ResponseEntity<?> eliminarMedicacion(Long idMedicacion) {
        response.clear();
        try{
            boolean eliminado = medicacionService.eliminarMedicacion(idMedicacion);
            if(eliminado){
                response.put("mensaje", "Medicacion eliminada con exito!");
                status = HttpStatus.OK;
            }else{
                response.put("mensaje", "No existe la medicacion con id: "+idMedicacion.toString());
                status = HttpStatus.BAD_REQUEST;
            }
        }catch(DataAccessException e){
            response.put("mensaje", "Ha ocurrido un error en el servidor al intentar eliminar la medicacion! ");
            response.put("error", e.getMostSpecificCause().getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(response, status); 
    }

    

     /* ---------------------- M??TODOS AUXILIARES ---------------------- */

     private List<String> getErrors(BindingResult result){
        return result.getFieldErrors().stream().map(
            err -> {
                return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
            }
        ).collect(Collectors.toList());
    }
}
