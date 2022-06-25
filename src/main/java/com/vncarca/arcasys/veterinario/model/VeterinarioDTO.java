/**
* Created by Roy Morocho.
* User: steve
* Date: 08 jun 2022
* Time: 15:23:00
*/
package com.vncarca.arcasys.veterinario.model;

import com.vncarca.arcasys.persona.model.PersonaDto;

import lombok.Data;

@Data
public class VeterinarioDTO {
	private Long id;
	private String cargo;
	private PersonaDto persona;
}
