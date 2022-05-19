package com.vncarca.arcasys.medicamento.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "medicamento")
public class Medicamento implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
    private String nombreComercial;

	@Column(nullable = false)
    private String nombreGenerico;
	

	@Column(nullable = false)
    private int cantidad;
	
	@Column(nullable = false)
    private float precio;

	public Medicamento(Long id, String nombreComercial, String nombreGenerico, int cantidad, float precio) {
		super();
		this.id = id;
		this.nombreComercial = nombreComercial;
		this.nombreGenerico = nombreGenerico;
		this.cantidad = cantidad;
		this.precio = precio;
	}
	
	

}
