package com.vncarca.arcasys.serviciosarca.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vncarca.arcasys.persona.cliente.ClienteDtoExtends;
import com.vncarca.arcasys.veterinario.model.VeterinarioDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CitaArcaExtends implements Serializable {
	private Long id;

	@NotNull
	private ClienteDtoExtends cliente;

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "America/Guayaquil")
	private Date fechaCita;

	@NotBlank
	@NotNull
	private String motivo;

	@NotNull
	private boolean estado;

	@NotNull
	private VeterinarioDTO veterinario;
}