package com.pasdm.etl.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(schema = "etl", name = "plant", uniqueConstraints = {
        @UniqueConstraint(name = "uk_plant_fecha_1", columnNames = "fecha_1")
})
@Getter
@Setter
@NoArgsConstructor
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_1")
    private LocalDate fecha1;

    /* =======================
       BLOQUE MINERAL OXIDOS PARTE 1
       ======================= */

    @Column(name = "toneladas_mil_oxidos", precision = 20, scale = 10)
    private BigDecimal toneladasMilOxidos;

    @Column(name = "ley_au_oxidos", precision = 20, scale = 10)
    private BigDecimal leyAuOxidos;

    @Column(name = "ley_ag_oxidos", precision = 20, scale = 10)
    private BigDecimal leyAgOxidos;

    @Column(name = "recuperacion_au_oxidos_pct", precision = 20, scale = 10)
    private BigDecimal recuperacionAuOxidosPct;

    @Column(name = "recuperacion_ag_oxidos_pct", precision = 20, scale = 10)
    private BigDecimal recuperacionAgOxidosPct;
    
    /* =======================
       BLOQUE MINERAL SULFUROS PARTE 1
       ======================= */

    @Column(name = "toneladas_mil_sulfuros", precision = 20, scale = 10)
    private BigDecimal toneladasMilSulfuros;

    @Column(name = "ley_au_sulfuros", precision = 20, scale = 10)
    private BigDecimal leyAuSulfuros;

    @Column(name = "ley_ag_sulfuros", precision = 20, scale = 10)
    private BigDecimal leyAgSulfuros;

    @Column(name = "ley_pb_sulfuros", precision = 20, scale = 10)
    private BigDecimal leyPbSulfuros;

    @Column(name = "ley_zn_sulfuros", precision = 20, scale = 10)
    private BigDecimal leyZnSulfuros;

    @Column(name = "recuperacion_au_sulfuros_pct", precision = 20, scale = 10)
    private BigDecimal recuperacionAuSulfurosPct;

    @Column(name = "recuperacion_ag_sulfuros_pct", precision = 20, scale = 10)
    private BigDecimal recuperacionAgSulfurosPct;

    @Column(name = "recuperacion_pb_sulfuros_pct", precision = 20, scale = 10)
    private BigDecimal recuperacionPbSulfurosPct;

    @Column(name = "recuperacion_zn_sulfuros_pct", precision = 20, scale = 10)
    private BigDecimal recuperacionZnSulfurosPct;

    /* =======================
       BLOQUE STOCK
       ======================= */

    @Column(name = "stock_sulfuros_tm", precision = 20, scale = 10)
    private BigDecimal stockSulfurosTm;

    @Column(name = "stock_oxidos_tm", precision = 20, scale = 10)
    private BigDecimal stockOxidosTm;

    @Column(name = "toneladas_mil_total", precision = 20, scale = 10)
    private BigDecimal toneladasMilTotal;

    /* =======================
       BLOQUE OXIDOS
       ======================= */

    @Column(name = "au_oxidos_oz", precision = 20, scale = 10)
    private BigDecimal auOxidosOz;

    @Column(name = "ag_oxidos_oz", precision = 20, scale = 10)
    private BigDecimal agOxidosOz;

    /* =======================
       BLOQUE SULFUROS
       ======================= */

    @Column(name = "au_sulfuros_oz", precision = 20, scale = 10)
    private BigDecimal auSulfurosOz;

    @Column(name = "ag_sulfuros_oz", precision = 20, scale = 10)
    private BigDecimal agSulfurosOz;

    @Column(name = "pb_sulfuros_t", precision = 20, scale = 10)
    private BigDecimal pbSulfurosT;

    @Column(name = "zn_sulfuros_t", precision = 20, scale = 10)
    private BigDecimal znSulfurosT;

    /* =======================
       BLOQUE CONSOLIDADO
       ======================= */

    @Column(name = "au_consolidado_oz", precision = 20, scale = 10)
    private BigDecimal auConsolidadoOz;

    @Column(name = "ag_consolidado_oz", precision = 20, scale = 10)
    private BigDecimal agConsolidadoOz;

    @Column(name = "pb_consolidado_t", precision = 20, scale = 10)
    private BigDecimal pbConsolidadoT;

    @Column(name = "zn_consolidado_t", precision = 20, scale = 10)
    private BigDecimal znConsolidadoT;
    /* =======================
       BLOQUE LEY CONSOLIDADO
       ======================= */

    @Column(name = "ley_au_consolidado", precision = 20, scale = 10)
    private BigDecimal leyAuConsolidado;

    @Column(name = "ley_ag_consolidado", precision = 20, scale = 10)
    private BigDecimal leyAgConsolidado;

    /* =======================
       BLOQUE RECUPERACION CONSOLIDADO
       ======================= */

    @Column(name = "recuperacion_au_consolidado_pct", precision = 20, scale = 10)
    private BigDecimal recuperacionAuConsolidadoPct;

    @Column(name = "recuperacion_ag_consolidado_pct", precision = 20, scale = 10)
    private BigDecimal recuperacionAgConsolidadoPct;

    /* =======================
       BLOQUE PROCESO
       ======================= */

    @Column(name = "cianuro_kg", precision = 20, scale = 10)
    private BigDecimal cianuroKg;

    @Column(name = "antiincrustante_kg", precision = 20, scale = 10)
    private BigDecimal antiincrustanteKg;

    @Column(name = "cal_kg", precision = 20, scale = 10)
    private BigDecimal calKg;

    @Column(name = "bolas_molienda_kg", precision = 20, scale = 10)
    private BigDecimal bolasMoliendaKg;

    @Column(name = "depre_zinc_kg", precision = 20, scale = 10)
    private BigDecimal depreZincKg;

    /* =======================
       BLOQUE SERVICIOS
       ======================= */

    @Column(name = "electricidad_kw", precision = 20, scale = 10)
    private BigDecimal electricidadKw;

    @Column(name = "agua_m3", precision = 20, scale = 10)
    private BigDecimal aguaM3;

    /* =======================
       BLOQUE DISPONIBILIDAD PLANTA
       ======================= */

    @Column(name = "disponibilidad_planta_oxidos_hrs", precision = 20, scale = 10)
    private BigDecimal disponibilidadPlantaOxidosHrs;

    @Column(name = "disponibilidad_planta_sulfuros_hrs", precision = 20, scale = 10)
    private BigDecimal disponibilidadPlantaSulfurosHrs;

    /* =======================
       BLOQUE USO PLANTA
       ======================= */
    
    @Column(name = "uso_planta_oxidos_hrs", precision = 20, scale = 10)
    private BigDecimal usoPlantaOxidosHrs;

    @Column(name = "uso_planta_sulfuros_hrs", precision = 20, scale = 10)
    private BigDecimal usoPlantaSulfurosHrs;

}
