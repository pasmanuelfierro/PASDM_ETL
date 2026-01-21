package com.pasdm.etl.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "geology_report", schema = "etl",
        uniqueConstraints = @UniqueConstraint(name = "uk_geology_report_row_hash", columnNames = "row_hash"))
@Getter
@Setter
@NoArgsConstructor
public class GeologyReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;                 // 0

    @Column(name = "turno", length = 20)
    private String turno;                    // 1

    @Column(name = "mina", length = 100)
    private String mina;                     // 2

    @Column(name = "zona", length = 100)
    private String zona;                     // 3

    @Column(name = "tipo", length = 50)
    private String tipo;                     // 4

    @Column(name = "veta", length = 100)
    private String veta;                     // 5

    @Column(name = "lugar", length = 100)
    private String lugar;                    // 6

    @Column(name = "estatus", length = 50)
    private String estatus;                  // 7

    @Column(name = "comentario", length = 500)
    private String comentario;               // 8

    @Column(name = "proy_mineral", length = 500)
    private String proyMineral;              // 9

    @Column(name = "linea_rez", precision = 20, scale = 10)
    private BigDecimal lineaRez;                  // 10

    @Column(name = "ancho_veta", precision = 20, scale = 10)
    private BigDecimal anchoVeta;             // 11

    @Column(name = "ancho_programado", precision = 20, scale = 10)
    private BigDecimal anchoProgramado;       // 12

    @Column(name = "ancho_obra", precision = 20, scale = 10)
    private BigDecimal anchoObra;             // 13

    @Column(name = "dilucion", precision = 20, scale = 10)
    private BigDecimal dilucion;              // 14

    @Column(name = "ag1", precision = 20, scale = 10)
    private BigDecimal ag1;                   // 15

    @Column(name = "au1", precision = 20, scale = 10)
    private BigDecimal au1;                   // 16

    @Column(name = "pb1", precision = 20, scale = 10)
    private BigDecimal pb1;                   // 17

    @Column(name = "zn1", precision = 20, scale = 10)
    private BigDecimal zn1;                   // 18

    @Column(name = "block_reservas", length = 100)
    private String blockReservas;             // 19

    @Column(name = "ley_block", precision = 20, scale = 10)
    private BigDecimal leyBlock;              // 20

    @Column(name = "ag", precision = 20, scale = 10)
    private BigDecimal ag;                    // 21

    @Column(name = "au", precision = 20, scale = 10)
    private BigDecimal au;                    // 22

    @Column(name = "pb", precision = 20, scale = 10)
    private BigDecimal pb;                    // 23

    @Column(name = "zn", precision = 20, scale = 10)
    private BigDecimal zn;                    // 24

    @Column(name = "vpt", precision = 20, scale = 10)
    private BigDecimal vpt;                   // 25

    @Column(name = "ag_plan_dil_0", precision = 20, scale = 10)
    private BigDecimal agPlanDil0;             // 26

    @Column(name = "dil_plan", precision = 20, scale = 10)
    private BigDecimal dilPlan;                // 27

    @Column(name = "dil_piso", precision = 20, scale = 10)
    private BigDecimal dilPiso;                // 28

    @Column(name = "dil_piso_2", precision = 20, scale = 10)
    private BigDecimal dilPiso2;               // 29

    /* =========================
       AUDITORÍA
       ========================= */

    @Column(name = "row_hash", length = 64, nullable = false)
    private String rowHash;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}

