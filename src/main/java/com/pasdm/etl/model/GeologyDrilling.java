package com.pasdm.etl.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(schema = "etl", name = "geology_drilling")
public class GeologyDrilling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_1", nullable = false)
    private LocalDate fecha1;

    @Column(name = "Bno")
    private Integer bno;

    @Column(name = "Empresa")
    private Integer empresa;

    @Column(name = "Maquina")
    private String maquina;

    @Column(name = "FondoDia_Anterior", precision = 20, scale = 10)
    private BigDecimal fondoDiaAnterior;

    @Column(name = "FondoActual_M", precision = 20, scale = 10)
    private  BigDecimal fondoActualM;

    @Column(name = "AvanceDia_M", precision = 20, scale = 10)
    private  BigDecimal avanceDiaM;

    @Column(name = "Status")
    private Integer status;

    @Column(name = "Estacion")
    private Integer estacion;

    @Column(name = "JV_Holes")
    private String jvHoles;

    @Column(name = "Target")
    private String target;

    @Column(name = "Target2")
    private String target2;

    @Column(name = "row_hash", length = 64, nullable = false, unique = true)
    private String rowHash;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", updatable = true)
    private LocalDateTime updatedAt;

}
