package com.vncarca.arcasys.serviciosarca.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.vncarca.arcasys.serviciosarca.model.ServicioArca;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CitaDto {
    
    @NotBlank
    private String nombreCliente;

    @NotBlank
    private String fechaCita;

    @NotBlank
    private String motivo;

    @NotNull
    private boolean estado;

    @NotNull
    private List<ServicioArca> servicios;
} 
