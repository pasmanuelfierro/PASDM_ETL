package com.pasdm.integration.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(schema = "etl", name = "production")
@Getter
@Setter
@NoArgsConstructor
public class Production {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* =======================
       IDENTIFICACIÓN
       ======================= */

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "lote", length = 100)
    private String lote;

    @Column(name = "mina", length = 100)
    private String mina;

    @Column(name = "zona", length = 100)
    private String zona;

    @Column(name = "empresa", length = 150)
    private String empresa;

    @Column(name = "obra", length = 150)
    private String obra;

    @Column(name = "estatus", length = 50)
    private String estatus;

    @Column(name = "estructura", length = 150)
    private String estructura;

    @Column(name = "labor", length = 150)
    private String labor;

    /* =======================
       LEYES
       ======================= */

    @Column(name = "ley_ag", precision = 20, scale = 10)
    private BigDecimal leyAg;

    @Column(name = "ley_au", precision = 20, scale = 10)
    private BigDecimal leyAu;

    @Column(name = "ley_pb", precision = 20, scale = 10)
    private BigDecimal leyPb;

    @Column(name = "ley_zn", precision = 20, scale = 10)
    private BigDecimal leyZn;

    /* =======================
       VOLUMEN / PRODUCCIÓN
       ======================= */

    @Column(name = "vpt", precision = 20, scale = 10)
    private BigDecimal vpt;

    @Column(name = "tipo", length = 50)
    private String tipo;

    @Column(name = "tons", precision = 20, scale = 10)
    private BigDecimal tons;

    /* =======================
       SUMATORIAS
       ======================= */

    @Column(name = "sum_ag", precision = 20, scale = 10)
    private BigDecimal sumAg;

    @Column(name = "sum_au", precision = 20, scale = 10)
    private BigDecimal sumAu;

    @Column(name = "sum_pb", precision = 20, scale = 10)
    private BigDecimal sumPb;

    @Column(name = "sum_zn", precision = 20, scale = 10)
    private BigDecimal sumZn;

    @Column(name = "sum_vpt", precision = 20, scale = 10)
    private BigDecimal sumVpt;

    @Column(name = "row_hash", length = 64, nullable = false, unique = true)
    private String rowHash;

    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

}