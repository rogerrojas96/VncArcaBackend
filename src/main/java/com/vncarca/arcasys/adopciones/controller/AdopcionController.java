package com.vncarca.arcasys.adopciones.controller;

import com.vncarca.arcasys.adopciones.model.Adopcion;
import com.vncarca.arcasys.adopciones.service.AdopcionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/adopciones")
@CrossOrigin(origins = "*")
@RestController
public class AdopcionController {
    
    @Autowired
    private AdopcionService adopcionService;


    @PostMapping("/adoptar")
    public ResponseEntity<?> generarAdopcion(@RequestBody Adopcion adopcion){
        adopcionService.crearAdopcion(adopcion);
        return new ResponseEntity<>("Registrado Satisfactoriamente", HttpStatus.CREATED);
    }
}
