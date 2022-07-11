package com.vncarca.arcasys.serviciosarca.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class CitaDto {
    @NotBlank
    private String nombreCliente;

    @NotNull
    @JsonFormat( shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm",timezone = "America/Guayaquil")
    private Date fechaCita;

    @NotBlank
    private String motivo;

    @NotNull
    private boolean estado;

    /**
     * @param nombreCliente
     * @param fechaCita
     * @param motivo
     * @param estado
     */
    public CitaDto(String nombreCliente, Date fechaCita, String motivo, boolean estado) {
        this.nombreCliente = nombreCliente;
        this.fechaCita = fechaCita;
        this.motivo = motivo;
        this.estado = estado;
    }
}
