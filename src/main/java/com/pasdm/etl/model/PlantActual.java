package com.pasdm.etl.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(schema = "etl", name = "plant_actual", uniqueConstraints = {
        @UniqueConstraint(name = "uk_plant_actual_row_hash", columnNames = "row_hash")
})
@Getter
@Setter
@NoArgsConstructor
public class PlantActual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* =========================
       IDENTIFICACIÓN
       ========================= */

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "turno", length = 20)
    private String turno;

    /* =========================
       ALIMENTACIÓN / CABEZA
       ========================= */

    @Column(name = "peso_seco_ton", precision = 20, scale = 10)
    private BigDecimal pesoSecoTon;

    @Column(name = "cabeza_ag_kg_t", precision = 20, scale = 10)
    private BigDecimal cabezaAgKgT;

    @Column(name = "cabeza_zn_pct", precision = 20, scale = 10)
    private BigDecimal cabezaZnPct;

    @Column(name = "cabeza_cu_pct", precision = 20, scale = 10)
    private BigDecimal cabezaCuPct;

    /* =========================
       CONCENTRADO PLOMO (Conc. Pb)
       ========================= */

    @Column(name = "conc_pb_au_gt", precision = 20, scale = 10)
    private BigDecimal concPbAuGt;

    @Column(name = "conc_pb_ag_kg_t", precision = 20, scale = 10)
    private BigDecimal concPbAgKgT;

    @Column(name = "conc_pb_pb_pct", precision = 20, scale = 10)
    private BigDecimal concPbPbPct;

    @Column(name = "conc_pb_zn_pct", precision = 20, scale = 10)
    private BigDecimal concPbZnPct;

    @Column(name = "conc_pb_cu_pct", precision = 20, scale = 10)
    private BigDecimal concPbCuPct;

    /* =========================
       CONCENTRADO ZINC (Conc. Zn)
       ========================= */

    @Column(name = "conc_zn_au_gt", precision = 20, scale = 10)
    private BigDecimal concZnAuGt;

    @Column(name = "conc_zn_ag_kg_t", precision = 20, scale = 10)
    private BigDecimal concZnAgKgT;

    @Column(name = "conc_zn_pb_pct", precision = 20, scale = 10)
    private BigDecimal concZnPbPct;

    @Column(name = "conc_zn_zn_pct", precision = 20, scale = 10)
    private BigDecimal concZnZnPct;

    @Column(name = "conc_zn_cu_pct", precision = 20, scale = 10)
    private BigDecimal concZnCuPct;

    /* =========================
       COLAS
       ========================= */

    @Column(name = "cola_au_gt", precision = 20, scale = 10)
    private BigDecimal colaAuGt;

    @Column(name = "cola_ag_kg_t", precision = 20, scale = 10)
    private BigDecimal colaAgKgT;

    @Column(name = "cola_pb_pct", precision = 20, scale = 10)
    private BigDecimal colaPbPct;

    @Column(name = "cola_zn_pct", precision = 20, scale = 10)
    private BigDecimal colaZnPct;

    @Column(name = "cola_cu_pct", precision = 20, scale = 10)
    private BigDecimal colaCuPct;

    /* =======================
       TONELAJES
       ======================= */

    @Column(name = "conc_pb_ton")
    private BigDecimal concPbTon;             // 21

    @Column(name = "conc_zn_ton")
    private BigDecimal concZnTon;             // 22

    @Column(name = "cola_ton")
    private BigDecimal colaTon;               // 23

    /* =======================
       CONTENIDOS – CABEZA
       ======================= */

    @Column(name = "cabeza_au")
    private BigDecimal cabezaAu;              // 24

    @Column(name = "cabeza_ag")
    private BigDecimal cabezaAg;              // 25

    @Column(name = "cabeza_pb")
    private BigDecimal cabezaPb;              // 26

    @Column(name = "cabeza_zn")
    private BigDecimal cabezaZn;              // 27

    @Column(name = "cabeza_cu")
    private BigDecimal cabezaCu;              // 28

    /* =======================
       CONTENIDOS – CONC Pb
       ======================= */

    @Column(name = "conc_pb_au")
    private BigDecimal concPbAu;               // 29

    @Column(name = "conc_pb_ag")
    private BigDecimal concPbAg;               // 30

    @Column(name = "conc_pb_pb")
    private BigDecimal concPbPb;               // 31

    @Column(name = "conc_pb_zn")
    private BigDecimal concPbZn;               // 32

    @Column(name = "conc_pb_cu")
    private BigDecimal concPbCu;               // 33

    /* =======================
       CONTENIDOS – CONC Zn
       ======================= */

    @Column(name = "conc_zn_au")
    private BigDecimal concZnAu;               // 34

    @Column(name = "conc_zn_ag")
    private BigDecimal concZnAg;               // 35

    @Column(name = "conc_zn_pb")
    private BigDecimal concZnPb;               // 36

    @Column(name = "conc_zn_zn")
    private BigDecimal concZnZn;               // 37

    @Column(name = "conc_zn_cu")
    private BigDecimal concZnCu;               // 38

    /* =======================
       CONTENIDOS – COLAS
       ======================= */

    @Column(name = "cola_au")
    private BigDecimal colaAu;                 // 39

    @Column(name = "cola_ag")
    private BigDecimal colaAg;                 // 40

    @Column(name = "cola_pb")
    private BigDecimal colaPb;                 // 41

    @Column(name = "cola_zn")
    private BigDecimal colaZn;                 // 42

    @Column(name = "cola_cu")
    private BigDecimal colaCu;                 // 43

    /* =========================
       AUDITORÍA
       ========================= */

    @Column(name = "row_hash", length = 64, nullable = false, unique = true)
    private String rowHash;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", updatable = true)
    private LocalDateTime updatedAt;
}