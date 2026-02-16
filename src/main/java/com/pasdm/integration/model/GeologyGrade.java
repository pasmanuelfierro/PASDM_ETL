package com.pasdm.integration.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "geology_grade", schema = "etl",
        uniqueConstraints = @UniqueConstraint(name = "uk_geology_grade_row_hash", columnNames = "row_hash"))
@Getter
@Setter
@NoArgsConstructor
public class GeologyGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* =========================
       IDENTIFICACIÓN
       ========================= */

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;                     // COL_FECHA = 0

    @Column(name = "mina", length = 100)
    private String mina;                         // 1

    @Column(name = "area", length = 100)
    private String area;                         // 2

    @Column(name = "m_minado", length = 100)
    private String mMinado;                      // 3

    @Column(name = "mineral", length = 100)
    private String mineral;                      // 4

    @Column(name = "lugar", length = 100)
    private String lugar;                        // 5

    @Column(name = "ml", precision = 20, scale = 10)
    private BigDecimal ml;                       // 6

    @Column(name = "ancho_planeado", precision = 20, scale = 10)
    private BigDecimal anchoPlaneado;            // 7

    @Column(name = "plan", length = 50)
    private String plan;                         // 8

    @Column(name = "veta", length = 100)
    private String veta;                         // 9

    /* =========================
       PLAN
       ========================= */

    @Column(name = "ton_plan2", precision = 20, scale = 10)
    private BigDecimal tonPlan2;                 // 10

    @Column(name = "ton_plan3", precision = 20, scale = 10)
    private BigDecimal tonPlan3;                 // 11

    @Column(name = "concesion", length = 100)
    private String concesion;                    // 12

    @Column(name = "ag_plan", precision = 20, scale = 10)
    private BigDecimal agPlan;                   // 13

    @Column(name = "au_plan", precision = 20, scale = 10)
    private BigDecimal auPlan;                   // 14

    @Column(name = "pb_plan", precision = 20, scale = 10)
    private BigDecimal pbPlan;                   // 15

    @Column(name = "zn_plan", precision = 20, scale = 10)
    private BigDecimal znPlan;                   // 16

    /* =========================
       TONELADAS / ANCHOS
       ========================= */

    @Column(name = "ton_pintarron", precision = 20, scale = 10)
    private BigDecimal tonPintarron;              // 17

    @Column(name = "ton_manteo", precision = 20, scale = 10)
    private BigDecimal tonManteo;                 // 18

    @Column(name = "ancho_real", precision = 20, scale = 10)
    private BigDecimal anchoReal;                 // 19

    @Column(name = "ancho_muestreo", precision = 20, scale = 10)
    private BigDecimal anchoMuestreo;             // 20

    /* =========================
       LEYES
       ========================= */

    @Column(name = "ley_ag", precision = 20, scale = 10)
    private BigDecimal leyAg;                     // 21

    @Column(name = "ley_au", precision = 20, scale = 10)
    private BigDecimal leyAu;                     // 22

    @Column(name = "ley_pb", precision = 20, scale = 10)
    private BigDecimal leyPb;                     // 23

    @Column(name = "ley_zn", precision = 20, scale = 10)
    private BigDecimal leyZn;                     // 24

    /* =========================
       CLASIFICACIÓN / DILUCIÓN
       ========================= */

    @Column(name = "linea_rez", length = 50)
    private String lineaRez;                      // 25

    @Column(name = "class", length = 50)
    private String clase;                         // 26

    @Column(name = "dil_piso", precision = 20, scale = 10)
    private BigDecimal dilPiso;                   // 27

    @Column(name = "dilucion", precision = 20, scale = 10)
    private BigDecimal dilucion;                  // 28

    /* =========================
       DILUIDOS
       ========================= */

    @Column(name = "ag_dil", precision = 20, scale = 10)
    private BigDecimal agDil;                     // 29

    @Column(name = "au_dil", precision = 20, scale = 10)
    private BigDecimal auDil;                     // 30

    @Column(name = "pb_dil", precision = 20, scale = 10)
    private BigDecimal pbDil;                     // 31

    @Column(name = "zn_dil", precision = 20, scale = 10)
    private BigDecimal znDil;                     // 32

    @Column(name = "ag_ton_dil", precision = 20, scale = 10)
    private BigDecimal agTonDil;                  // 33

    @Column(name = "au_ton_dil", precision = 20, scale = 10)
    private BigDecimal auTonDil;                  // 34

    @Column(name = "pb_ton_dil", precision = 20, scale = 10)
    private BigDecimal pbTonDil;                  // 35

    @Column(name = "zn_ton_dil", precision = 20, scale = 10)
    private BigDecimal znTonDil;                  // 36

    /* =========================
           FACTORES 0.8
           ========================= */
    @Column(name = "ag_08_dil", precision = 20, scale = 10)
    private BigDecimal ag08Dil; // 367

    @Column(name = "au_08_dil", precision = 20, scale = 10)
    private BigDecimal au08Dil; // 38

    @Column(name = "pb_08_dil", precision = 20, scale = 10)
    private BigDecimal pb08Dil;// 39

    @Column(name = "zn_08_dil", precision = 20, scale = 10)
    private BigDecimal zn08Dil;// 40

    /* =========================
       VPT / CUMPLIMIENTO
       ========================= */

    @Column(name = "vpt_tab", precision = 20, scale = 10)
    private BigDecimal vptTab; // 41

    @Column(name = "vpt_tons", precision = 20, scale = 10)
    private BigDecimal vptTons; // 42

    @Column(name = "ton_plan_ag", precision = 20, scale = 10)
    private BigDecimal tonPlanAg; // 43

    @Column(name = "ton_plan_au", precision = 20, scale = 10)
    private BigDecimal tonPlanAu; // 44

    @Column(name = "ton_plan_pb", precision = 20, scale = 10)
    private BigDecimal tonPlanPb; // 45

    @Column(name = "ton_plan_zn", precision = 20, scale = 10)
    private BigDecimal tonPlanZn; // 46

    @Column(name = "cumpli_ley_ag", precision = 20, scale = 10)
    private BigDecimal cumpliLeyAg; // 47

    @Column(name = "vpt_plan", precision = 20, scale = 10)
    private BigDecimal vptPlan; // 48

    @Column(name = "vpt_tons_plan", precision = 20, scale = 10)
    private BigDecimal vptTonsPlan; // 49

    @Column(name = "cumplimiento", precision = 20, scale = 10)
    private BigDecimal cumplimiento; // 50

    /* =========================
       DIFERENCIAS
       ========================= */

    @Column(name = "fc", precision = 20, scale = 10)
    private BigDecimal fc; // 52

    @Column(name = "dif_ag", precision = 20, scale = 10)
    private BigDecimal difAg; // 53

    @Column(name = "dif_au", precision = 20, scale = 10)
    private BigDecimal difAu; // 54

    @Column(name = "dif_pb", precision = 20, scale = 10)
    private BigDecimal difPb; // 55

    @Column(name = "dif_zn", precision = 20, scale = 10)
    private BigDecimal difZn; // 56

    /* =========================
       OTROS
       ========================= */

    @Column(name = "ancho_programado", precision = 20, scale = 10)
    private BigDecimal anchoProgramado; // 57

    @Column(name = "dilpiso_ton", precision = 20, scale = 10)
    private BigDecimal dilPisoTon; // 58

    @Column(name = "dil_ton", precision = 20, scale = 10)
    private BigDecimal dilTon; // 59

    @Column(name = "dil_ancho_min", precision = 20, scale = 10)
    private BigDecimal dilAnchoMin; // 60

    @Column(name = "columna1")
    private String columna1; // 61

    @Column(name = "min_clave")
    private String minClave; // 62

    @Column(name = "tipo_minado")
    private String tipoMinado; // 63

    @Column(name = "clave")
    private String clave; // 64

    @Column(name = "obra_veta")
    private String obraVeta; // 65

    @Column(name = "m_real", precision = 20, scale = 10)
    private BigDecimal mReal; // 66

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

