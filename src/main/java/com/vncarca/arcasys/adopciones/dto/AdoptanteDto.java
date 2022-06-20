package com.vncarca.arcasys.adopciones.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AdoptanteDto {
    
    private String cedula;
	private String nombre;
	private String apellidos;
	private String direccion;
	private String telefono;
	private String celular;
	private String correo;
    private String telefonoFamiliar;
    private String nicknameFacebook;
}
