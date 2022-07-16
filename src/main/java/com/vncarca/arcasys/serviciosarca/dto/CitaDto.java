package com.vncarca.arcasys.serviciosarca.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @NotNull
    private Long cliente_id;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "America/Guayaquil")
    private Date fechaCita;

    @NotBlank
    @NotNull
    private String motivo;

    @NotNull
    private boolean estado;

    /**
     * @param cliente
     * @param fechaCita
     * @param motivo
     * @param estado
     */
    public CitaDto(Long cliente_id, Date fechaCita, String motivo, boolean estado) {
        this.cliente_id = cliente_id;
        this.fechaCita = fechaCita;
        this.motivo = motivo;
        this.estado = estado;
    }
}