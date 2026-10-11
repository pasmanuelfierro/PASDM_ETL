package com.pasdm.integration.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Table(schema = "etl", name = "laboratory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Laboratory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    private String tipo;
    @Column(precision = 10, scale = 2)
    private BigDecimal mina;

    @Column(precision = 10, scale = 2)
    private BigDecimal exploracion;

    @Column(precision = 10, scale = 2)
    private BigDecimal planta;

    @Column(precision = 10, scale = 2)
    private BigDecimal embarquesPb;

    @Column(precision = 10, scale = 2)
    private BigDecimal embarquesZn;

    @Column(precision = 10, scale = 2)
    private BigDecimal muestrasEspeciales;

    @Column(precision = 10, scale = 2)
    private BigDecimal metalurgias;

    @Column(precision = 10, scale = 2)
    private BigDecimal comercializacion;

    @Column(precision = 10, scale = 2)
    private BigDecimal reensayes;

    @Column(precision = 10, scale = 2)
    private BigDecimal qaqc;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    @Column(name = "row_hash", length = 64, nullable = false, unique = true)
    private String rowHash;
}
