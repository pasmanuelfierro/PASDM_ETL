package com.pasdm.integration.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(schema = "etl", name = "plant_budget", uniqueConstraints = {
        @UniqueConstraint(name = "uk_plant_budget_row_hash", columnNames = "row_hash")
})
@Getter
@Setter
@NoArgsConstructor
public class PlantBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* =========================
       IDENTIFICACIÓN
       ========================= */

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;                       // 0

    /* =========================
       ALIMENTACIÓN
       ========================= */

    @Column(name = "ore_total_t", precision = 20, scale = 10)
    private BigDecimal oreTotalT;                  // 1

    @Column(name = "tonnes_milled", precision = 20, scale = 10)
    private BigDecimal tonnesMilled;               // 2

    @Column(name = "au_gt", precision = 20, scale = 10)
    private BigDecimal auGt;                       // 3

    @Column(name = "ag_gt", precision = 20, scale = 10)
    private BigDecimal agGt;                       // 4

    @Column(name = "pb_pct", precision = 20, scale = 10)
    private BigDecimal pbPct;                      // 5

    @Column(name = "zn_pct", precision = 20, scale = 10)
    private BigDecimal znPct;                      // 6

    /* =========================
       RECUPERACIONES
       ========================= */

    @Column(name = "rec_au_pct", precision = 20, scale = 10)
    private BigDecimal recAuPct;                   // 7

    @Column(name = "rec_ag_pct", precision = 20, scale = 10)
    private BigDecimal recAgPct;                   // 8

    @Column(name = "rec_pb_pct", precision = 20, scale = 10)
    private BigDecimal recPbPct;                   // 9

    @Column(name = "rec_zn_pct", precision = 20, scale = 10)
    private BigDecimal recZnPct;                   // 10

    /* =========================
       PRODUCCIÓN
       ========================= */

    @Column(name = "au_oz", precision = 20, scale = 10)
    private BigDecimal auOz;                       // 11

    @Column(name = "ag_oz", precision = 20, scale = 10)
    private BigDecimal agOz;                       // 12

    @Column(name = "pb_t", precision = 20, scale = 10)
    private BigDecimal pbT;                        // 13

    @Column(name = "zn_t", precision = 20, scale = 10)
    private BigDecimal znT;                        // 14

    @Column(name = "prod_kg_ag", precision = 20, scale = 10)
    private BigDecimal prodKgAg;                   // 15

    @Column(name = "prod_grs_au", precision = 20, scale = 10)
    private BigDecimal prodGrsAu;                  // 16

    @Column(name = "prod_tons_pb", precision = 20, scale = 10)
    private BigDecimal prodTonsPb;                 // 17

    @Column(name = "prod_tons_zn", precision = 20, scale = 10)
    private BigDecimal prodTonsZn;                 // 18

    /* =========================
       CONCENTRADOS Pb
       ========================= */

    @Column(name = "conc_pb_tons_pb", precision = 20, scale = 10)
    private BigDecimal concPbTonsPb;               // 19

    @Column(name = "conc_pb_ag_kg_ton", precision = 20, scale = 10)
    private BigDecimal concPbAgKgTon;              // 20

    @Column(name = "conc_pb_au_grs_ton", precision = 20, scale = 10)
    private BigDecimal concPbAuGrsTon;             // 21

    @Column(name = "conc_pb_pb_pct", precision = 20, scale = 10)
    private BigDecimal concPbPbPct;                // 22

    /* =========================
       CONCENTRADOS Zn
       ========================= */

    @Column(name = "conc_zn_tons_zn", precision = 20, scale = 10)
    private BigDecimal concZnTonsZn;               // 23

    @Column(name = "conc_zn_ag_kg_ton", precision = 20, scale = 10)
    private BigDecimal concZnAgKgTon;              // 24

    @Column(name = "conc_zn_zn_pct", precision = 20, scale = 10)
    private BigDecimal concZnZnPct;                // 25

    /* =========================
       LEYES CONCENTRADOS
       ========================= */

    @Column(name = "ley_conc_au", precision = 20, scale = 10)
    private BigDecimal leyConcAu;                  // 26

    @Column(name = "ley_conc_ag_pb", precision = 20, scale = 10)
    private BigDecimal leyConcAgPb;                // 27

    @Column(name = "ley_conc_ag_zn", precision = 20, scale = 10)
    private BigDecimal leyConcAgZn;                // 28

    @Column(name = "ley_conc_ag", precision = 20, scale = 10)
    private BigDecimal leyConcAg;                  // 29

    @Column(name = "ley_conc_pb", precision = 20, scale = 10)
    private BigDecimal leyConcPb;                  // 30

    @Column(name = "ley_conc_zn", precision = 20, scale = 10)
    private BigDecimal leyConcZn;                  // 31

    /* =========================
       AUDITORÍA
       ========================= */

    @Column(name = "row_hash", nullable = false, length = 64)
    private String rowHash;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;
}