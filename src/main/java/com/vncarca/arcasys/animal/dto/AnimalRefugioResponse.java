package com.vncarca.arcasys.animal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalRefugioResponse extends AnimalRefugioRequest{

	private Long id;
	private String urlImagenAnimal;
}
