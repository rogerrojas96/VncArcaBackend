package com.vncarca.arcasys.adopciones.controller;

import com.vncarca.arcasys.adopciones.dto.SeguimientoAdopcionDto;
import com.vncarca.arcasys.adopciones.model.SeguimientoAdopcionDtoExtends;
import com.vncarca.arcasys.adopciones.service.ISeguimientoAdopcionService;
import com.vncarca.util.Response;
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

@Api(tags = "Seguimiento de Adoptados", description = "Controlador para CRUD de seguimiento de animalitos adoptados.")
@RestController
@RequestMapping("/adopciones/seguimientos")
public class SeguimientoAdopcionController {

    @Autowired
    private ISeguimientoAdopcionService seguimientoService;
    
    private Map<String, Object> response = new HashMap<>();
    private SeguimientoAdopcionDtoExtends seguimiento;
    private HttpStatus status;
    
    
    @ResponseBody
    @PostMapping("/{idAdopcion}")
    public ResponseEntity<?> crearSeguimiento(@Valid @RequestBody SeguimientoAdopcionDto seguimientoDto, 
            BindingResult result, @PathVariable Long idAdopcion) {
        response.clear();
        if(!result.hasErrors()){
             try {
                 Response<SeguimientoAdopcionDtoExtends> seguimiento = seguimientoService.crearSeguimiento(seguimientoDto,
                         idAdopcion);
                 if (seguimiento.getStatus() == HttpStatus.CREATED) {
                     response.put("mensaje", "Seguimiento creado con exito!");
                     response.put("seguimiento", seguimiento);
                     status = HttpStatus.CREATED;
                 } else if (seguimiento.getStatus() == HttpStatus.BAD_REQUEST) {
                     response.put("mensaje", "No se encontro una adopci??n con: " + idAdopcion.toString());
                     status = HttpStatus.BAD_REQUEST;
                 } else {
                     response.put("mensaje", " Error en el servidor al intentar enviar un Email ");
                    status = HttpStatus.INTERNAL_SERVER_ERROR;
                }
            }catch(DataAccessException e){
                response.put("mensaje", "Ha ocurrido un error en el servidor al intentar crear el seguimiento! ");
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
   @PutMapping("/{idSeguimiento}")
    public ResponseEntity<?> editarSeguimiento(@Valid @RequestBody SeguimientoAdopcionDto seguimientoDto, BindingResult result,
            @PathVariable Long idSeguimiento) {
        response.clear();
        if(!result.hasErrors()){
            try{
                seguimiento = seguimientoService.editarSeguimiento(seguimientoDto, idSeguimiento);
                if(seguimiento != null){
                    response.put("mensaje", "Seguimiento editado con exito!");
                    response.put("seguimiento", seguimiento);
                    status = HttpStatus.CREATED;
                 }else{
                    response.put("mensaje", "Id de seguimiento: "+idSeguimiento.toString()+", No encontrado!");
                    status = HttpStatus.BAD_REQUEST;
                }
            }catch(DataAccessException e){
                response.put("mensaje", "Ha ocurrido un error en el servidor al intentar editar el seguimiento! ");
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
    @DeleteMapping("/{idSeguimiento}")
    public ResponseEntity<?> eliminarSeguimiento(@PathVariable Long idSeguimiento) {
        response.clear();
        try{
            seguimiento = seguimientoService.eliminarSeguimiento(idSeguimiento);
            if(seguimiento != null){
                response.put("mensaje", "Seguimiento eliminado con exito!");
                response.put("seguimiento", seguimiento);
                status = HttpStatus.OK;
             }else{
                response.put("mensaje", "Id de seguimiento: "+idSeguimiento.toString()+", No encontrado!");
                status = HttpStatus.BAD_REQUEST;
            }
        }catch(DataAccessException e){
            response.put("mensaje", "Ha ocurrido un error en el servidor al intentar eliminar el seguimiento! ");
            response.put("error", e.getMostSpecificCause().getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(response, status);
    }


    @ResponseBody
    @PutMapping("/finalizar/{idSeguimiento}")
    public ResponseEntity<?> finalizarSeguimiento(@Valid @RequestBody SeguimientoAdopcionDto seguimientoDto, 
            BindingResult result, @PathVariable Long idSeguimiento) {
        response.clear();
        if(!result.hasErrors()){
            try{
                seguimiento = seguimientoService.finalizarSeguimiento(seguimientoDto, idSeguimiento);
                if(seguimiento != null){
                    response.put("mensaje", "Seguimiento finalizado con exito!");
                    response.put("seguimiento", seguimiento);
                    status = HttpStatus.OK;
                 }else{
                    response.put("mensaje", "Id de seguimiento: "+idSeguimiento.toString()+", No encontrado!");
                    status = HttpStatus.BAD_REQUEST;
                }
            }catch(DataAccessException e){
                response.put("mensaje", "Ha ocurrido un error en el servidor al intentar finalizar el seguimiento! ");
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
    @GetMapping("/adopciones")
    public ResponseEntity<?> getAdopcionesEnSeguimientoActivo() {
        response.clear();
        response.put("adopciones", seguimientoService.getAdopcionesEnSeguimientoActivo());
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/all/enproceso/{idAdopcion}")
    public ResponseEntity<?> getAllSeguimientosActivos(@PathVariable Long idAdopcion) {
        response.clear();
        try{
            List<SeguimientoAdopcionDtoExtends> seguimientos = seguimientoService.getAllSeguimientosActivos(idAdopcion);
            if(seguimientos != null){
                response.put("seguimientos", seguimientos);
                status = HttpStatus.OK;
             }else{
                response.put("mensaje", "Id de adopcion: "+idAdopcion.toString()+", No encontrado!");
                status = HttpStatus.BAD_REQUEST;
            }
        }catch(DataAccessException e){
            response.put("mensaje", "Ha ocurrido un error en el servidor al intentar buscar seguimientos! ");
            response.put("error", e.getMostSpecificCause().getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(response, status);
    }

    @ResponseBody
    @GetMapping("/all/finalizado/{idAdopcion}")
    public ResponseEntity<?> getAllSeguimientosTerminados(@PathVariable Long idAdopcion) {
        response.clear();
        try{
            List<SeguimientoAdopcionDtoExtends> seguimientos = seguimientoService.getAllSeguimientosTerminados(idAdopcion);
            if(seguimientos != null){
                response.put("seguimientos", seguimientos);
                status = HttpStatus.OK;
             }else{
                response.put("mensaje", "Id de adopcion: "+idAdopcion.toString()+", No encontrado!");
                status = HttpStatus.BAD_REQUEST;
            }
        }catch(DataAccessException e){
            response.put("mensaje", "Ha ocurrido un error en el servidor al intentar buscar seguimientos! ");
            response.put("error", e.getMostSpecificCause().getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(response, status);
    }

    @ResponseBody
    @GetMapping("/count/{idAdopcion}")
    public ResponseEntity<?> getNumSeguimientosActivos(@PathVariable Long idAdopcion) {
        response.clear();
        try{
            Long numSeguimientos = seguimientoService.getNumSeguimientosActivos(idAdopcion);
            if(numSeguimientos != null){
                response.put("numero", numSeguimientos);
                status = HttpStatus.OK;
             }else{
                response.put("mensaje", "Id de adopcion: "+idAdopcion.toString()+", No encontrado!");
                status = HttpStatus.BAD_REQUEST;
            }
        }catch(DataAccessException e){
            response.put("mensaje", "Ha ocurrido un error en el servidor al intentar buscar seguimientos! ");
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
