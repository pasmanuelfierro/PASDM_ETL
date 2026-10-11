package com.pasdm.integration.repository;

import com.pasdm.integration.model.DailyCMDActual;
import com.pasdm.integration.model.PlantActual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface DailyCMDActualRepository extends JpaRepository<DailyCMDActual, Long> {
    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO etl.daily_cmd_actual (
                           fecha, row_hash,
                           frecuency_index, severity_index, lost_days, exposure_hours,
                           lt_hpif, hpi_con_lesion, hpi_sin_lesion, accidente_trayecto,
                           property_damage_incidents, lost_time_accidents, tarjeta_roja,
                           medical_attention, first_aid_accidents, fatal_accident,
                           daily_workers, total_workers, total_contractors_operativos,
                           total_workers_pasmex,
                           hm_laboured, hm_contractors, hm_training_sh,
                           flujo_patios,flujo_entrada_merril, au_gt, ag_gt,  au_oz, ag_oz
                       )
                       VALUES (
                           :#{#dailyIndicator.fecha},
                           :#{#dailyIndicator.rowHash},

                           :#{#dailyIndicator.frecuencyIndex},
                           :#{#dailyIndicator.severityIndex},
                           :#{#dailyIndicator.lostDays},
                           :#{#dailyIndicator.exposureHours},
                           :#{#dailyIndicator.ltHpif},
                           :#{#dailyIndicator.hpiConLesion},
                           :#{#dailyIndicator.hpiSinLesion},
                           :#{#dailyIndicator.accidenteTrayecto},

                           :#{#dailyIndicator.propertyDamageIncidents},
                           :#{#dailyIndicator.lostTimeAccidents},
                           :#{#dailyIndicator.tarjetaRoja},
                           :#{#dailyIndicator.medicalAttention},
                           :#{#dailyIndicator.firstAidAccidents},
                           :#{#dailyIndicator.fatalAccident},

                           :#{#dailyIndicator.dailyWorkers},
                           :#{#dailyIndicator.totalWorkers},
                           :#{#dailyIndicator.totalContractorsOperativos},
                           :#{#dailyIndicator.totalWorkersPasmex},

                           :#{#dailyIndicator.hmLaboured},
                           :#{#dailyIndicator.hmContractors},
                           :#{#dailyIndicator.hmTrainingSh},

                           :#{#dailyIndicator.flujoPatios},
                           :#{#dailyIndicator.flujoEntradaMerril},
                           :#{#dailyIndicator.auGt},
                           :#{#dailyIndicator.agGt},
                           :#{#dailyIndicator.auOz},
                           :#{#dailyIndicator.agOz}
                       )
                       ON CONFLICT (row_hash)
                       DO UPDATE SET
                           fecha                         = EXCLUDED.fecha,

                           frecuency_index              = EXCLUDED.frecuency_index,
                           severity_index               = EXCLUDED.severity_index,
                           lost_days                    = EXCLUDED.lost_days,
                           exposure_hours               = EXCLUDED.exposure_hours,
                           lt_hpif                      = EXCLUDED.lt_hpif,
                           hpi_con_lesion               = EXCLUDED.hpi_con_lesion,
                           hpi_sin_lesion               = EXCLUDED.hpi_sin_lesion,
                           accidente_trayecto           = EXCLUDED.accidente_trayecto,

                           property_damage_incidents    = EXCLUDED.property_damage_incidents,
                           lost_time_accidents          = EXCLUDED.lost_time_accidents,
                           tarjeta_roja                 = EXCLUDED.tarjeta_roja,
                           medical_attention            = EXCLUDED.medical_attention,
                           first_aid_accidents          = EXCLUDED.first_aid_accidents,
                           fatal_accident               = EXCLUDED.fatal_accident,

                           daily_workers                = EXCLUDED.daily_workers,
                           total_workers                = EXCLUDED.total_workers,
                           total_contractors_operativos = EXCLUDED.total_contractors_operativos,
                           total_workers_pasmex         = EXCLUDED.total_workers_pasmex,

                           hm_laboured                  = EXCLUDED.hm_laboured,
                           hm_contractors               = EXCLUDED.hm_contractors,
                           hm_training_sh               = EXCLUDED.hm_training_sh,

                           flujo_patios                 = EXCLUDED.flujo_patios,
                           flujo_entrada_merril         = EXCLUDED.flujo_entrada_merril,
                           au_gt                        = EXCLUDED.au_gt,
                           ag_gt                        = EXCLUDED.ag_gt,
                           au_oz                        = EXCLUDED.au_oz,
                           ag_oz                        = EXCLUDED.ag_oz,

                           updated_at                   = now();
        """, nativeQuery = true)
    void upsert(@Param("dailyIndicator") DailyCMDActual dailyIndicator);
}