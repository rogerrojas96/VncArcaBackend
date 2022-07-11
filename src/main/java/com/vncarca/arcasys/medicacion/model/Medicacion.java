package com.vncarca.arcasys.medicacion.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vncarca.arcasys.medicamento.model.Medicamento;
import com.vncarca.arcasys.tratamiento.model.Tratamiento;
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
@Getter
@Setter
@Entity
@Table(name="medicaciones")
@Where(clause = "deleted=false")
@SQLDelete(sql = "UPDATE medicaciones SET deleted = true WHERE id=?")
public class Medicacion implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "text", name = "descripcion_md")
    private String descripcionMd;

    @Column(name = "dosis")
    private String dosis;

    @Column(name = "frecuencia")
    private String frecuencia;

    @Column(name = "duracion")
    private String duracion;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Guayaquil")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false, name = "fecha_caducidad")
    private Date fechaCaducidad;

    /* --------------------------- RELACIONES --------------------------- */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_medicamento")
    private Medicamento medicamento;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tratamiento")
    private Tratamiento tratamiento;

    @NotNull
    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean deleted=Boolean.FALSE;
    
    
}
