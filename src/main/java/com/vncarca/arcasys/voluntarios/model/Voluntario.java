package com.vncarca.arcasys.voluntarios.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vncarca.arcasys.persona.model.Persona;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Entity
@SQLDelete(sql = "UPDATE voluntarios SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@Table(name = "voluntarios")
public class Voluntario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String actividad;

    @Column(nullable = false)
    private String tipo;

    @NotNull
    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean deleted=Boolean.FALSE;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne()
    @JoinColumn(name="persona_id")
    private Persona persona;
}
