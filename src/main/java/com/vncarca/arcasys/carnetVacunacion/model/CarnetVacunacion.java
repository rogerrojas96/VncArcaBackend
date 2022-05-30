package com.vncarca.arcasys.carnetVacunacion.model;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vncarca.arcasys.carnetVacunacion.vacuna.model.Vacuna;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "carnetVacunaciones")
public class CarnetVacunacion implements Serializable {
  private static final long serialVersionUID = 1;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Guayaquil")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Temporal(TemporalType.DATE)
  @Column(nullable = false)
  private Date fechaAplicacion;
  
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Guayaquil")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Temporal(TemporalType.DATE)
  @Column(nullable = false)
  private Date fechaProximaAplicacion;
  
  @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "vacuna_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Vacuna vacuna;

}
