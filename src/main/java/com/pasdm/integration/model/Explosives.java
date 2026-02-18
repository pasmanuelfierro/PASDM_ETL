package com.pasdm.integration.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(schema = "etl", name = "explosives",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_explosives_row_hash", columnNames = "row_hash")
        },
        indexes = {
                @Index(name = "ux_explosive_row_hash", columnList = "row_hash"),
         })
@Getter
@Setter
@NoArgsConstructor
public class Explosives {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* =========================
       IDENTIFICACIÓN
       ========================= */

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "mina", length = 100)
    private String mina;

    @Column(name = "mdo_minado", length = 100)
    private String mdoMinado;

    @Column(name = "obra", length = 100)
    private String obra;

    @Column(name = "lugar", length = 100)
    private String lugar;

    @Column(name = "mineral", length = 100)
    private String mineral;

    @Column(name = "utilizado_por", length = 100)
    private String utilizadoPor;

    @Column(name = "marca", length = 50)
    private String marca;

    @Column(name = "turno", length = 20)
    private String turno;

    @Column(name = "grupo", length = 50)
    private String grupo;

    @Column(name = "supervisor", length = 100)
    private String supervisor;

    /* =========================
       COSTOS Y EQUIPO
       ========================= */

    @Column(name = "costo")
    private String costo;

    @Column(name = "equipo", length = 100)
    private String equipo;

    /* =========================
       EXPLOSIVOS
       ========================= */

    @Column(name = "e_anfo_ug")
    private Integer eAnfoUg;
    @Column(name = "d_anfo_ug")
    private Integer dAnfoUg;
    @Column(name = "e_anfo_premium")
    private Integer eAnfoPremium;
    @Column(name = "d_anfo_premium")
    private Integer dAnfoPremium;

    @Column(name = "e_1x8")
    private Integer e1x8;
    @Column(name = "d_1x8")
    private Integer d1x8;
    @Column(name = "e_114x16")
    private Integer e114x16;
    @Column(name = "d_114x16")
    private Integer d114x16;

    @Column(name = "e_t1")
    private Integer eT1;
    @Column(name = "d_t1")
    private Integer dT1;

    @Column(name = "e_112x39")
    private Integer e112x39;
    @Column(name = "d_112x39")
    private Integer d112x39;

    @Column(name = "e_2x16")
    private Integer e2x16;
    @Column(name = "d_2x16")
    private Integer d2x16;

    @Column(name = "e_cordon")
    private Integer eCordon;
    @Column(name = "d_cordon")
    private Integer dCordon;

    @Column(name = "e_booster")
    private Integer eBooster;
    @Column(name = "d_booster")
    private Integer dBooster;

    /* =========================
       SISTEMAS
       ========================= */

    @Column(name = "powersplit_e")
    private Integer powersplitE;
    @Column(name = "powersplit_d")
    private Integer powersplitD;

    @Column(name = "cable_harness_e")
    private Integer cableHarnessE;
    @Column(name = "cable_harness_d")
    private Integer cableHarnessD;

    @Column(name = "cordon_sismico_e")
    private Integer cordonSismicoE;
    @Column(name = "cordon_sismico_d")
    private Integer cordonSismicoD;

    @Column(name = "stinger_20gr_e")
    private Integer stinger20grE;
    @Column(name = "stinger_20gr_d")
    private Integer stinger20grD;

    @Column(name = "ikon_10ft_e")
    private Integer ikon10ftE;
    @Column(name = "ikon_10ft_d")
    private Integer ikon10ftD;

    /* =========================
   CAÑUELA / NONEL
   ========================= */

    @Column(name = "e_canuela")
    private Integer eCanuela;

    @Column(name = "d_canuela")
    private Integer dCanuela;

    @Column(name = "nonel")
    private String nonel;

    @Column(name = "ft_nonel")
    private Integer ftNonel;

/* =========================
   RETARDOS (N1 - N15)
   ========================= */

    @Column(name = "n1_e")
    private Integer n1E;
    @Column(name = "n1_d")
    private Integer n1D;

    @Column(name = "n2_e")
    private Integer n2E;
    @Column(name = "n2_d")
    private Integer n2D;

    @Column(name = "n3_e")
    private Integer n3E;
    @Column(name = "n3_d")
    private Integer n3D;

    @Column(name = "n4_e")
    private Integer n4E;
    @Column(name = "n4_d")
    private Integer n4D;

    @Column(name = "n5_e")
    private Integer n5E;
    @Column(name = "n5_d")
    private Integer n5D;

    @Column(name = "n6_e")
    private Integer n6E;
    @Column(name = "n6_d")
    private Integer n6D;

    @Column(name = "n7_e")
    private Integer n7E;
    @Column(name = "n7_d")
    private Integer n7D;

    @Column(name = "n8_e")
    private Integer n8E;
    @Column(name = "n8_d")
    private Integer n8D;

    @Column(name = "n9_e")
    private Integer n9E;
    @Column(name = "n9_d")
    private Integer n9D;

    @Column(name = "n10_e")
    private Integer n10E;
    @Column(name = "n10_d")
    private Integer n10D;

    @Column(name = "n11_e")
    private Integer n11E;
    @Column(name = "n11_d")
    private Integer n11D;

    @Column(name = "n12_e")
    private Integer n12E;
    @Column(name = "n12_d")
    private Integer n12D;

    @Column(name = "n13_e")
    private Integer n13E;
    @Column(name = "n13_d")
    private Integer n13D;

    @Column(name = "n14_e")
    private Integer n14E;
    @Column(name = "n14_d")
    private Integer n14D;

    @Column(name = "n15_e")
    private Integer n15E;
    @Column(name = "n15_d")
    private Integer n15D;

    /* =========================
       FECHAS
       ========================= */
    @Column(name = "fecha_operacion")
    private LocalDate fechaOperacion;

    /* =======================
   CONTROL / ETL
   ======================= */
    @Column(name = "row_hash", length = 64, nullable = false, unique = true)
    private String rowHash;

    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

}
