package com.vncarca.arcasys.serviciosarca.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vncarca.arcasys.veterinario.model.Veterinario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "citas")
@Where(clause = "deleted=false")
@AllArgsConstructor
@SQLDelete(sql = "UPDATE citas SET deleted = true WHERE id=?")
public class Cita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nombre_cliente")
    private String nombreCliente;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat( shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm",timezone = "America/Guayaquil")
    @Column(name = "fecha_cita", unique = true)
    private Date fechaCita;

    @NotBlank
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "text")
    private String motivo;

    @NotNull
    private boolean estado;

    @NotNull
    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean deleted=Boolean.FALSE;
    /* --------------------------- RELACIONES --------------------------- */
    @ManyToOne
    @JoinColumn(name="id_veterinario")
    private Veterinario veterinario;
}
