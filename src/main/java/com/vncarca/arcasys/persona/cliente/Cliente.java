package com.vncarca.arcasys.persona.cliente;

import com.vncarca.arcasys.persona.model.Persona;
import com.vncarca.arcasys.serviciosarca.model.Cita;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted=false")
@SQLDelete(sql = "UPDATE personas SET deleted = true WHERE id=?", callable = true)
public class Cliente extends Persona {

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cliente")
	private List<Cita> citas = new ArrayList<>();

	public void setCitas(List<Cita> citas) {
		this.citas.addAll(citas);
	}

	public Cliente(Long id, String cedula, String nombre, String apellidos, String direccion, String telefono, String celular, String correo) {
		super(id, cedula, nombre, apellidos, direccion, telefono, celular, correo);
	}

	public Cliente(String cedula, String nombre, String apellidos, String direccion, String telefono, String celular, String correo) {
		super(cedula, nombre, apellidos, direccion, telefono, celular, correo);
	}
}