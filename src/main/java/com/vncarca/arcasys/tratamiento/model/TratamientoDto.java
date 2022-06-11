package com.vncarca.arcasys.tratamiento.model;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

@NoArgsConstructor
@Setter
@Getter
public class TratamientoDto {

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Guayaquil")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fechaAplicacion;

	@NotBlank
	private String descripcion;

	@NotBlank
	private String indicaciones;

	@NotBlank
	private String estado;  
}
