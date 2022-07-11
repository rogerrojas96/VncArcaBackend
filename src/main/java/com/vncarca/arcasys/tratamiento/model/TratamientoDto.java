package com.vncarca.arcasys.tratamiento.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Roy Morocho.
 * User: steve
 * Date: 11/07/2022
 * Time: 11:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TratamientoDto implements Serializable {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Guayaquil")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaAplicacion;
	@NotBlank
	private String descripcion;
	@NotBlank
	private String indicaciones;
	@NotBlank
	private String estado;
}
