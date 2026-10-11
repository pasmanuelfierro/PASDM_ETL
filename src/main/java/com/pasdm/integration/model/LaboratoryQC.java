package com.pasdm.integration.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(schema = "etl", name = "laboratory_qc")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LaboratoryQC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    private String lote;

    @Column(precision = 10, scale = 2)
    private BigDecimal plata;

    @Column(precision = 10, scale = 2)
    private BigDecimal minimo;

    @Column(precision = 10, scale = 2)
    private BigDecimal optimo;

    @Column(precision = 10, scale = 2)
    private BigDecimal maximo;

    @Column(precision = 10, scale = 2)
    private BigDecimal oro;

    @Column(precision = 10, scale = 2)
    private BigDecimal minimo2;

    @Column(precision = 10, scale = 2)
    private BigDecimal optimo3;

    @Column(precision = 10, scale = 2)
    private BigDecimal maximo4;

    @Column(precision = 10, scale = 2)
    private BigDecimal plomo;

    @Column(precision = 10, scale = 2)
    private BigDecimal minimos;

    @Column(precision = 10, scale = 2)
    private BigDecimal optimo6;

    @Column(precision = 10, scale = 2)
    private BigDecimal maximo7;

    @Column(precision = 10, scale = 2)
    private BigDecimal zinc;

    @Column(precision = 10, scale = 2)
    private BigDecimal minimo8;

    @Column(precision = 10, scale = 2)
    private BigDecimal optimo9;

    @Column(precision = 10, scale = 2)
    private BigDecimal maximo10;

    @Column(name = "created_at",updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    @Column(name = "row_hash", length = 64, nullable = false, unique = true)
    private String rowHash;
}
