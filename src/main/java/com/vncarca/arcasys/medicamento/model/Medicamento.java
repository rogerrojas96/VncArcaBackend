package com.vncarca.arcasys.medicamento.model;

import com.vncarca.arcasys.medicacion.model.Medicacion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "medicamentos")
@Where(clause = "deleted=false")
@SQLDelete(sql = "UPDATE medicamentos SET deleted = true WHERE id=?")
public class Medicamento implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(nullable = false)
	@NotBlank
	@NotNull
    private String nombreComercial;

	@NotBlank
	@NotNull
	@Column(nullable = false)
    private String nombreGenerico;


	@Positive
	@Column(nullable = false)
    private int cantidad;

	@Positive
	@Column(nullable = false)
    private float precio;

	@NotNull
	@Column(nullable = false, columnDefinition = "tinyint(1) default 0")
	private Boolean deleted=Boolean.FALSE;

	/**
	 * @param id
	 * @param nombreComercial
	 * @param nombreGenerico
	 * @param cantidad
	 * @param precio
	 */
	public Medicamento(Long id, String nombreComercial, String nombreGenerico, int cantidad, float precio) {
		this.id = id;
		this.nombreComercial = nombreComercial;
		this.nombreGenerico = nombreGenerico;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	//Para soft delete
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "medicamento")
	private List<Medicacion> medicaciones = new ArrayList<>();

	public void setMedicaciones(List<Medicacion> medicaciones) {
		this.medicaciones.addAll(medicaciones);
	}
}