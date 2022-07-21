package com.vncarca.arcasys.usuario.model;

import com.vncarca.arcasys.persona.model.PersonaDtoExtends;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto implements Serializable {
	private Long id;
	@NotBlank
	@NotNull
	private String username;
	@NotNull
	private PersonaDtoExtends persona;
}