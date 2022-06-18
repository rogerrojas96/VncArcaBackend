package com.vncarca.arcasys.serviciosarca.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "detalles_citas")
public class DetalleCita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* --------------------------- RELACIONES --------------------------- */
    @ManyToOne
    @JoinColumn(name="id_cita")
    private Cita cita;

    @ManyToOne
    @JoinColumn(name="id_servicio")
    private ServicioArca servicioArca;
}
