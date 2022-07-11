package com.vncarca.arcasys.tratamiento.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vncarca.arcasys.fichaclinica.model.FichaClinica;
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
@Table(name = "tratamientos")
@Where(clause = "deleted=false")
@SQLDelete(sql = "UPDATE tratamientos SET deleted = true WHERE id=?")
public class Tratamiento  implements Serializable{

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Guayaquil")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date fechaAplicacion;

	@NotBlank
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "text")
	private String descripcion;

	@NotBlank
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "text")
	private String indicaciones;

	@NotBlank
    @Column(nullable = false)
	private String estado;

	@NotNull
	@Column(nullable = false, columnDefinition = "tinyint(1) default 0")
	private Boolean deleted=Boolean.FALSE;

    @NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JoinColumn(name="id_ficha_clinica")
	private FichaClinica idFichaClinica;
}
