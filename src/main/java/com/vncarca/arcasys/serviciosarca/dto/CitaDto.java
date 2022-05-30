package com.vncarca.arcasys.serviciosarca.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

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

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "America/Guayaquil")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Temporal(TemporalType.DATE)
    private Date fechaCita;

    @NotBlank
    private String motivo;

    @NotNull
    private boolean estado;
}
