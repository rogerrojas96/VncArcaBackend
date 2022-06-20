package com.vncarca.arcasys.adopciones.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "seguimientos_adopciones")
public class SeguimientoAdopcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "mensaje_seguimiento",columnDefinition = "text", nullable = false)
    private String mensajeSeguimiento;

    @NotNull
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Guayaquil")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_seguimiento", nullable = false)
    private Date fechaSeguimiento;

    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "respuesta_adoptante",columnDefinition = "text", nullable = true)
    private String RespuestaAdoptante;

    @Column(name = "estado_seguimiento", nullable = false)
    private boolean estadoSeguimiento;


    /* ------------------------------------- RELACIONES ------------------------------------- */
    @ManyToOne
    @JoinColumn(name = "id_adopcion")
    private Adopcion adopcion;
}
