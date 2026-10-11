package com.pasdm.integration.repository;

import com.pasdm.integration.model.DailyCMDActual;
import com.pasdm.integration.model.Geology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface GeologyRepository extends JpaRepository<Geology, Long> {

    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO etl.geology (
                fecha_1,
                sobredilucion_rebajes_pct,
                sobredilucion_bl_pct,
                sobredilucion_breasting_pct,
                sobredilucion_corte_vertical_pct,
                sobredilucion_silles_pct,
                numero_silles_mineral,
                numero_silles_tepetate,
                numero_rebajes_bl,
                numero_rebajes_brt,
                avance_silles_mineral,
                avance_silles_tepetate,
                perforacion_ore_control_mts,
                perforacion_infill_mts,
                perforacion_brownfield_mts,
                lc_diamec_ore_control,
                lc_explorer_plus_ore_control,
                lc_d130_brownfield_infill,
                lc_1500_brownfield_infill,
                contratista_brownfield_subterranea,
                contratista_brownfield_superficie,
            
                fecha_2,
                mineral_sulfuros,
                ley_ag,
                ley_au,
                ley_pb,
                ley_zn,
            
                fecha_3,
                mineral_extraido_bl_tm,
                ley_ag_gpt,
                ley_au_gpt,
                ley_pb_pct,
                ley_zn_pct,
                mineral_extraido_breasting_tm,
                ley_ag_gpt_2,
                ley_au_gpt_3,
                ley_pb_pct_4,
                ley_zn_pct_5,
                mineral_silles_tm,
                ley_ag_gpt_7,
                ley_au_gpt_8,
                ley_pb_pct_9,
                ley_zn_pct_10,
                mineral_rebajes,
            
                row_hash,
                created_at,
                updated_at
            )
            VALUES (
                :#{#geology.fecha1},
                :#{#geology.sobredilucionRebajesPct},
                :#{#geology.sobredilucionBlPct},
                :#{#geology.sobredilucionBreastingPct},
                :#{#geology.sobredilucionCorteVerticalPct},
                :#{#geology.sobredilucionSillesPct},
                :#{#geology.numeroSillesMineral},
                :#{#geology.numeroSillesTepetate},
                :#{#geology.numeroRebajesBl},
                :#{#geology.numeroRebajesBrt},
                :#{#geology.avanceSillesMineral},
                :#{#geology.avanceSillesTepetate},
                :#{#geology.perforacionOreControlMts},
                :#{#geology.perforacionInfillMts},
                :#{#geology.perforacionBrownfieldMts},
                :#{#geology.lcMaquinaDiamecOreControl},
                :#{#geology.lcMaquinaExplorerPlusOreControl},
                :#{#geology.lcMaquinaD130BrownfieldInfill},
                :#{#geology.lcMaquina1500BrownfieldInfill},
                :#{#geology.contratistaBrownfieldSubterranea},
                :#{#geology.contratistaBrownfieldSuperficie},
            
                :#{#geology.fecha2},
                :#{#geology.mineralSulfuros},
                :#{#geology.leyAg},
                :#{#geology.leyAu},
                :#{#geology.leyPb},
                :#{#geology.leyZn},
            
                :#{#geology.fecha3},
                :#{#geology.mineralExtraidoBlTm},
                :#{#geology.leyAgGpt},
                :#{#geology.leyAuGpt},
                :#{#geology.leyPbPct},
                :#{#geology.leyZnPct},
                :#{#geology.mineralExtraidoBreastingTm},
                :#{#geology.leyAgGpt2},
                :#{#geology.leyAuGpt3},
                :#{#geology.leyPbPct4},
                :#{#geology.leyZnPct5},
                :#{#geology.mineralSillesTm},
                :#{#geology.leyAgGpt7},
                :#{#geology.leyAuGpt8},
                :#{#geology.leyPbPct9},
                :#{#geology.leyZnPct10},
                :#{#geology.mineralRebajes},
            
                :#{#geology.rowHash},
                CURRENT_TIMESTAMP,
                CURRENT_TIMESTAMP
            )
            ON CONFLICT (row_hash)
            DO UPDATE SET
                fecha_1 = EXCLUDED.fecha_1,
                sobredilucion_rebajes_pct = EXCLUDED.sobredilucion_rebajes_pct,
                sobredilucion_bl_pct = EXCLUDED.sobredilucion_bl_pct,
                sobredilucion_breasting_pct = EXCLUDED.sobredilucion_breasting_pct,
                sobredilucion_corte_vertical_pct = EXCLUDED.sobredilucion_corte_vertical_pct,
                sobredilucion_silles_pct = EXCLUDED.sobredilucion_silles_pct,
                numero_silles_mineral = EXCLUDED.numero_silles_mineral,
                numero_silles_tepetate = EXCLUDED.numero_silles_tepetate,
                numero_rebajes_bl = EXCLUDED.numero_rebajes_bl,
                numero_rebajes_brt = EXCLUDED.numero_rebajes_brt,
                avance_silles_mineral = EXCLUDED.avance_silles_mineral,
                avance_silles_tepetate = EXCLUDED.avance_silles_tepetate,
                perforacion_ore_control_mts = EXCLUDED.perforacion_ore_control_mts,
                perforacion_infill_mts = EXCLUDED.perforacion_infill_mts,
                perforacion_brownfield_mts = EXCLUDED.perforacion_brownfield_mts,
                lc_diamec_ore_control = EXCLUDED.lc_diamec_ore_control,
                lc_explorer_plus_ore_control = EXCLUDED.lc_explorer_plus_ore_control,
                lc_d130_brownfield_infill = EXCLUDED.lc_d130_brownfield_infill,
                lc_1500_brownfield_infill = EXCLUDED.lc_1500_brownfield_infill,
                contratista_brownfield_subterranea = EXCLUDED.contratista_brownfield_subterranea,
                contratista_brownfield_superficie = EXCLUDED.contratista_brownfield_superficie,
            
                fecha_2 = EXCLUDED.fecha_2,
                mineral_sulfuros = EXCLUDED.mineral_sulfuros,
                ley_ag = EXCLUDED.ley_ag,
                ley_au = EXCLUDED.ley_au,
                ley_pb = EXCLUDED.ley_pb,
                ley_zn = EXCLUDED.ley_zn,
            
                fecha_3 = EXCLUDED.fecha_3,
                mineral_extraido_bl_tm = EXCLUDED.mineral_extraido_bl_tm,
                ley_ag_gpt = EXCLUDED.ley_ag_gpt,
                ley_au_gpt = EXCLUDED.ley_au_gpt,
                ley_pb_pct = EXCLUDED.ley_pb_pct,
                ley_zn_pct = EXCLUDED.ley_zn_pct,
                mineral_extraido_breasting_tm = EXCLUDED.mineral_extraido_breasting_tm,
                ley_ag_gpt_2 = EXCLUDED.ley_ag_gpt_2,
                ley_au_gpt_3 = EXCLUDED.ley_au_gpt_3,
                ley_pb_pct_4 = EXCLUDED.ley_pb_pct_4,
                ley_zn_pct_5 = EXCLUDED.ley_zn_pct_5,
                mineral_silles_tm = EXCLUDED.mineral_silles_tm,
                ley_ag_gpt_7 = EXCLUDED.ley_ag_gpt_7,
                ley_au_gpt_8 = EXCLUDED.ley_au_gpt_8,
                ley_pb_pct_9 = EXCLUDED.ley_pb_pct_9,
                ley_zn_pct_10 = EXCLUDED.ley_zn_pct_10,
                mineral_rebajes = EXCLUDED.mineral_rebajes,
            
                row_hash = EXCLUDED.row_hash,
                updated_at = CURRENT_TIMESTAMP
            """, nativeQuery = true)
    void upsert(@Param("geology") Geology geology);
}