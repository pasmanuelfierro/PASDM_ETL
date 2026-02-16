package com.pasdm.integration.repository;

import com.pasdm.integration.model.PlantActual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PlantActualRepository extends JpaRepository<PlantActual, Long> {
    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO etl.plant_actual (
                               fecha, turno,
                               peso_seco_ton, cabeza_ag_kg_t, cabeza_zn_pct, cabeza_cu_pct,
                               conc_pb_au_gt, conc_pb_ag_kg_t, conc_pb_pb_pct, conc_pb_zn_pct, conc_pb_cu_pct,
                               conc_zn_au_gt, conc_zn_ag_kg_t, conc_zn_pb_pct, conc_zn_zn_pct, conc_zn_cu_pct,
                               cola_au_gt, cola_ag_kg_t, cola_pb_pct, cola_zn_pct, cola_cu_pct,
                               conc_pb_ton, conc_zn_ton, cola_ton,
                               cabeza_au, cabeza_ag, cabeza_pb, cabeza_zn, cabeza_cu,
                               conc_pb_au, conc_pb_ag, conc_pb_pb, conc_pb_zn, conc_pb_cu,
                               conc_zn_au, conc_zn_ag, conc_zn_pb, conc_zn_zn, conc_zn_cu,
                               cola_au, cola_ag, cola_pb, cola_zn, cola_cu,
                               row_hash
                           )
                           VALUES (
                               :#{#plantActual.fecha},
                               :#{#plantActual.turno},
            
                               :#{#plantActual.pesoSecoTon},
                               :#{#plantActual.cabezaAgKgT},
                               :#{#plantActual.cabezaZnPct},
                               :#{#plantActual.cabezaCuPct},
            
                               :#{#plantActual.concPbAuGt},
                               :#{#plantActual.concPbAgKgT},
                               :#{#plantActual.concPbPbPct},
                               :#{#plantActual.concPbZnPct},
                               :#{#plantActual.concPbCuPct},
            
                               :#{#plantActual.concZnAuGt},
                               :#{#plantActual.concZnAgKgT},
                               :#{#plantActual.concZnPbPct},
                               :#{#plantActual.concZnZnPct},
                               :#{#plantActual.concZnCuPct},
            
                               :#{#plantActual.colaAuGt},
                               :#{#plantActual.colaAgKgT},
                               :#{#plantActual.colaPbPct},
                               :#{#plantActual.colaZnPct},
                               :#{#plantActual.colaCuPct},
            
                               :#{#plantActual.concPbTon},
                               :#{#plantActual.concZnTon},
                               :#{#plantActual.colaTon},
            
                               :#{#plantActual.cabezaAu},
                               :#{#plantActual.cabezaAg},
                               :#{#plantActual.cabezaPb},
                               :#{#plantActual.cabezaZn},
                               :#{#plantActual.cabezaCu},
            
                               :#{#plantActual.concPbAu},
                               :#{#plantActual.concPbAg},
                               :#{#plantActual.concPbPb},
                               :#{#plantActual.concPbZn},
                               :#{#plantActual.concPbCu},
            
                               :#{#plantActual.concZnAu},
                               :#{#plantActual.concZnAg},
                               :#{#plantActual.concZnPb},
                               :#{#plantActual.concZnZn},
                               :#{#plantActual.concZnCu},
            
                               :#{#plantActual.colaAu},
                               :#{#plantActual.colaAg},
                               :#{#plantActual.colaPb},
                               :#{#plantActual.colaZn},
                               :#{#plantActual.colaCu},
            
                               :#{#plantActual.rowHash}
                           )
                           ON CONFLICT (row_hash)
                           DO UPDATE SET
                               turno              = EXCLUDED.turno,
                               peso_seco_ton      = EXCLUDED.peso_seco_ton,
                               cabeza_ag_kg_t      = EXCLUDED.cabeza_ag_kg_t,
                               cabeza_zn_pct      = EXCLUDED.cabeza_zn_pct,
                               cabeza_cu_pct      = EXCLUDED.cabeza_cu_pct,
            
                               conc_pb_au_gt      = EXCLUDED.conc_pb_au_gt,
                               conc_pb_ag_kg_t     = EXCLUDED.conc_pb_ag_kg_t,
                               conc_pb_pb_pct     = EXCLUDED.conc_pb_pb_pct,
                               conc_pb_zn_pct     = EXCLUDED.conc_pb_zn_pct,
                               conc_pb_cu_pct     = EXCLUDED.conc_pb_cu_pct,
            
                               conc_zn_au_gt      = EXCLUDED.conc_zn_au_gt,
                               conc_zn_ag_kg_t     = EXCLUDED.conc_zn_ag_kg_t,
                               conc_zn_pb_pct     = EXCLUDED.conc_zn_pb_pct,
                               conc_zn_zn_pct     = EXCLUDED.conc_zn_zn_pct,
                               conc_zn_cu_pct     = EXCLUDED.conc_zn_cu_pct,
            
                               cola_au_gt         = EXCLUDED.cola_au_gt,
                               cola_ag_kg_t       = EXCLUDED.cola_ag_kg_t,
                               cola_pb_pct        = EXCLUDED.cola_pb_pct,
                               cola_zn_pct        = EXCLUDED.cola_zn_pct,
                               cola_cu_pct        = EXCLUDED.cola_cu_pct,
            
                               conc_pb_ton        = EXCLUDED.conc_pb_ton,
                               conc_zn_ton        = EXCLUDED.conc_zn_ton,
                               cola_ton           = EXCLUDED.cola_ton,
            
                               cabeza_au          = EXCLUDED.cabeza_au,
                               cabeza_ag          = EXCLUDED.cabeza_ag,
                               cabeza_pb          = EXCLUDED.cabeza_pb,
                               cabeza_zn          = EXCLUDED.cabeza_zn,
                               cabeza_cu          = EXCLUDED.cabeza_cu,
            
                               conc_pb_au         = EXCLUDED.conc_pb_au,
                               conc_pb_ag         = EXCLUDED.conc_pb_ag,
                               conc_pb_pb         = EXCLUDED.conc_pb_pb,
                               conc_pb_zn         = EXCLUDED.conc_pb_zn,
                               conc_pb_cu         = EXCLUDED.conc_pb_cu,
            
                               conc_zn_au         = EXCLUDED.conc_zn_au,
                               conc_zn_ag         = EXCLUDED.conc_zn_ag,
                               conc_zn_pb         = EXCLUDED.conc_zn_pb,
                               conc_zn_zn         = EXCLUDED.conc_zn_zn,
                               conc_zn_cu         = EXCLUDED.conc_zn_cu,
            
                               cola_au            = EXCLUDED.cola_au,
                               cola_ag            = EXCLUDED.cola_ag,
                               cola_pb            = EXCLUDED.cola_pb,
                               cola_zn            = EXCLUDED.cola_zn,
                               cola_cu            = EXCLUDED.cola_cu,
            
                               updated_at         = now();
            """, nativeQuery = true)
    void upsert(@Param("plantActual") PlantActual plantActual);
}