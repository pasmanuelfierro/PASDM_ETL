package com.pasdm.etl.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(schema = "etl", name = "geology")
@Getter
@Setter
@NoArgsConstructor
public class Geology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* =======================
       BLOQUE MINERAL SULFURO PARTE 1
       ======================= */

    @Column(name = "fecha_1")
    private LocalDate fecha1;

    @Column(name = "sobredilucion_rebajes_pct")
    private Integer sobredilucionRebajesPct;

    @Column(name = "sobredilucion_bl_pct")
    private Integer sobredilucionBlPct;

    @Column(name = "sobredilucion_breasting_pct")
    private Integer sobredilucionBreastingPct;

    @Column(name = "sobredilucion_corte_vertical_pct")
    private Integer sobredilucionCorteVerticalPct;

    @Column(name = "sobredilucion_silles_pct")
    private Integer sobredilucionSillesPct;

    @Column(name = "numero_silles_mineral")
    private Integer numeroSillesMineral;

    @Column(name = "numero_silles_tepetate")
    private Integer numeroSillesTepetate;

    @Column(name = "numero_rebajes_bl")
    private Integer numeroRebajesBl;

    @Column(name = "numero_rebajes_brt")
    private Integer numeroRebajesBrt;

    @Column(name = "avance_silles_mineral")
    private Integer avanceSillesMineral;

    @Column(name = "avance_silles_tepetate")
    private Integer avanceSillesTepetate;

    @Column(name = "perforacion_ore_control_mts", precision = 20, scale = 10)
    private BigDecimal perforacionOreControlMts;

    @Column(name = "perforacion_infill_mts", precision = 20, scale = 10)
    private BigDecimal perforacionInfillMts;

    @Column(name = "perforacion_brownfield_mts", precision = 20, scale = 10)
    private BigDecimal perforacionBrownfieldMts;

    @Column(name = "lc_diamec_ore_control")
    private Integer lcMaquinaDiamecOreControl;

    @Column(name = "lc_explorer_plus_ore_control")
    private Integer lcMaquinaExplorerPlusOreControl;

    @Column(name = "lc_d130_brownfield_infill")
    private Integer lcMaquinaD130BrownfieldInfill;

    @Column(name = "lc_1500_brownfield_infill")
    private Integer lcMaquina1500BrownfieldInfill;

    @Column(name = "contratista_brownfield_subterranea")
    private Integer contratistaBrownfieldSubterranea;

    @Column(name = "contratista_brownfield_superficie")
    private Integer contratistaBrownfieldSuperficie;

    /* =======================
       BLOQUE MINERAL SULFURO PARTE 2
       ======================= */

    @Column(name = "fecha_2")
    private LocalDate fecha2;

    @Column(name = "mineral_sulfuros")
    private Integer mineralSulfuros;

    @Column(name = "ley_ag", precision = 20, scale = 10)
    private BigDecimal leyAg;

    @Column(name = "ley_au", precision = 20, scale = 10)
    private BigDecimal leyAu;

    @Column(name = "ley_pb", precision = 20, scale = 10)
    private BigDecimal leyPb;

    @Column(name = "ley_zn", precision = 20, scale = 10)
    private BigDecimal leyZn;

    @Column(name = "mineral_oxidos")
    private Integer mineralOxidos;

    @Column(name = "ley_ag_2")
    private Integer leyAg2;

    @Column(name = "ley_au_3", precision = 20, scale = 10)
    private BigDecimal leyAu3;

    @Column(name = "ley_pb_4")
    private Integer leyPb4;

    @Column(name = "ley_zn_5")
    private Integer leyZn5;

    @Column(name = "ley_zn_6")
    private Integer leyZn6;

    /* =======================
       BLOQUE MINERAL SULFURO PARTE 3
       ======================= */

    @Column(name = "fecha_3")
    private LocalDate fecha3;

    @Column(name = "mineral_extraido_bl_tm", precision = 20, scale = 10)
    private BigDecimal mineralExtraidoBlTm;

    @Column(name = "ley_ag_gpt", precision = 20, scale = 10)
    private BigDecimal leyAgGpt;

    @Column(name = "ley_au_gpt", precision = 20, scale = 10)
    private BigDecimal leyAuGpt;

    @Column(name = "ley_pb_pct", precision = 20, scale = 10)
    private BigDecimal leyPbPct;

    @Column(name = "ley_zn_pct", precision = 20, scale = 10)
    private BigDecimal leyZnPct;

    @Column(name = "mineral_extraido_breasting_tm", precision = 20, scale = 10)
    private BigDecimal mineralExtraidoBreastingTm;

    @Column(name = "ley_ag_gpt_2", precision = 20, scale = 10)
    private BigDecimal leyAgGpt2;

    @Column(name = "ley_au_gpt_3", precision = 20, scale = 10)
    private BigDecimal leyAuGpt3;

    @Column(name = "ley_pb_pct_4", precision = 20, scale = 10)
    private BigDecimal leyPbPct4;

    @Column(name = "ley_zn_pct_5")
    private Integer leyZnPct5;

    @Column(name = "mineral_extraido_corte_vertical_tm")
    private Integer mineralExtraidoCorteVerticalTm;

    @Column(name = "ley_ag_gpt_3")
    private Integer leyAgGpt3;

    @Column(name = "ley_au_gpt_4")
    private Integer leyAuGpt4;

    @Column(name = "ley_pb_pct_5")
    private Integer leyPbPct5;

    @Column(name = "ley_zn_pct_6")
    private Integer leyZnPct6;

    @Column(name = "mineral_silles_tm", precision = 20, scale = 10)
    private BigDecimal mineralSillesTm;

    @Column(name = "ley_ag_gpt_7", precision = 20, scale = 10)
    private BigDecimal leyAgGpt7;

    @Column(name = "ley_au_gpt_8", precision = 20, scale = 10)
    private BigDecimal leyAuGpt8;

    @Column(name = "ley_pb_pct_9", precision = 20, scale = 10)
    private BigDecimal leyPbPct9;

    @Column(name = "ley_zn_pct_10", precision = 20, scale = 10)
    private BigDecimal leyZnPct10;

    @Column(name = "ley_zn_pct_11", precision = 20, scale = 10)
    private BigDecimal leyZnPct11;

    @Column(name = "mineral_rebajes", precision = 20, scale = 10)
    private BigDecimal mineralRebajes;
}
