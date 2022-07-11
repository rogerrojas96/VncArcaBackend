package com.vncarca.arcasys.serviciosarca.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "servicios")
@Where(clause = "deleted=false")
@SQLDelete(sql = "UPDATE servicios SET deleted = true WHERE id=?")

public class ServicioArca {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;

    @NotBlank
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "text")
    private String descripcion;

    @NotNull
    @Min(0)
    private double precio;

    @NotNull
    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean deleted=Boolean.FALSE;

    public ServicioArca(Long id, String nombre, String descripcion, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }
    
    /**
     * @param nombre
     * @param descripcion
     * @param precio
     */
    public ServicioArca(String nombre, String descripcion, double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }
    
    //soft delete cascade
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "servicioArca")
    private List<DetalleCita> detallesCitas;
}
