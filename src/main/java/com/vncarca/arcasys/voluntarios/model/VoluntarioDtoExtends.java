package com.vncarca.arcasys.voluntarios.model;

import com.vncarca.arcasys.persona.model.PersonaDtoExtends;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Roy Morocho.
 * User: steve
 * Date: 11/07/2022
 * Time: 12:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoluntarioDtoExtends implements Serializable {
	private Long id;
	private String actividad;
	private String tipo;
	private PersonaDtoExtends persona;
}
