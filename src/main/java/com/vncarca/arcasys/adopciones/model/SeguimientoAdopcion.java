package com.vncarca.arcasys.adopciones.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vncarca.arcasys.animal.model.AnimalRefugio;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "seguimientos_adopciones")
@Where(clause = "deleted=false")
@SQLDelete(sql = "UPDATE seguimientos_adopciones SET deleted = true WHERE id=?")
public class SeguimientoAdopcion implements Serializable {

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

    @NotNull
    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean deleted=Boolean.FALSE;

    /* ------------------------------------- RELACIONES ------------------------------------- */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_adopcion",nullable = false)
    private Adopcion adopcion;
}
