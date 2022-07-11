package com.vncarca.arcasys.donaciones.model;

import com.vncarca.arcasys.persona.model.Persona;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "donaciones")
@Where(clause = "deleted=false")
@SQLDelete(sql = "UPDATE donaciones SET deleted = true WHERE id=?")
public class Donacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "text")
    private String descripcion;

    @Min(0)
    private double cantidad;

    @NotNull
    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean deleted=Boolean.FALSE;

    /* ------------------------------------- Relaciones  ------------------------------------- */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona")
    private Persona persona;
}
