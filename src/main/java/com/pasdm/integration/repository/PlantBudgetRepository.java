package com.pasdm.integration.repository;

import com.pasdm.integration.model.PlantBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PlantBudgetRepository extends JpaRepository<PlantBudget, Long> {
    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO etl.plant_budget (
                fecha,
                ore_total_t,
                tonnes_milled,
                au_gt,
                ag_gt,
                pb_pct,
                zn_pct,
                rec_au_pct,
                rec_ag_pct,
                rec_pb_pct,
                rec_zn_pct,
                au_oz,
                ag_oz,
                pb_t,
                zn_t,
                prod_kg_ag,
                prod_grs_au,
                prod_tons_pb,
                prod_tons_zn,
                conc_pb_tons_pb,
                conc_pb_ag_kg_ton,
                conc_pb_au_grs_ton,
                conc_pb_pb_pct,
                conc_zn_tons_zn,
                conc_zn_ag_kg_ton,
                conc_zn_zn_pct,
                ley_conc_au,
                ley_conc_ag_pb,
                ley_conc_ag_zn,
                ley_conc_ag,
                ley_conc_pb,
                ley_conc_zn,
                row_hash
            )
            VALUES (
                :#{#budget.fecha},
                :#{#budget.oreTotalT},
                :#{#budget.tonnesMilled},
                :#{#budget.auGt},
                :#{#budget.agGt},
                :#{#budget.pbPct},
                :#{#budget.znPct},
                :#{#budget.recAuPct},
                :#{#budget.recAgPct},
                :#{#budget.recPbPct},
                :#{#budget.recZnPct},
                :#{#budget.auOz},
                :#{#budget.agOz},
                :#{#budget.pbT},
                :#{#budget.znT},
                :#{#budget.prodKgAg},
                :#{#budget.prodGrsAu},
                :#{#budget.prodTonsPb},
                :#{#budget.prodTonsZn},
                :#{#budget.concPbTonsPb},
                :#{#budget.concPbAgKgTon},
                :#{#budget.concPbAuGrsTon},
                :#{#budget.concPbPbPct},
                :#{#budget.concZnTonsZn},
                :#{#budget.concZnAgKgTon},
                :#{#budget.concZnZnPct},
                :#{#budget.leyConcAu},
                :#{#budget.leyConcAgPb},
                :#{#budget.leyConcAgZn},
                :#{#budget.leyConcAg},
                :#{#budget.leyConcPb},
                :#{#budget.leyConcZn},
                :#{#budget.rowHash}
            )
            ON CONFLICT (row_hash)
            DO UPDATE SET
                ore_total_t        = EXCLUDED.ore_total_t,
                tonnes_milled      = EXCLUDED.tonnes_milled,
                au_gt              = EXCLUDED.au_gt,
                ag_gt              = EXCLUDED.ag_gt,
                pb_pct             = EXCLUDED.pb_pct,
                zn_pct             = EXCLUDED.zn_pct,
                rec_au_pct         = EXCLUDED.rec_au_pct,
                rec_ag_pct         = EXCLUDED.rec_ag_pct,
                rec_pb_pct         = EXCLUDED.rec_pb_pct,
                rec_zn_pct         = EXCLUDED.rec_zn_pct,
                au_oz              = EXCLUDED.au_oz,
                ag_oz              = EXCLUDED.ag_oz,
                pb_t               = EXCLUDED.pb_t,
                zn_t               = EXCLUDED.zn_t,
                prod_kg_ag         = EXCLUDED.prod_kg_ag,
                prod_grs_au        = EXCLUDED.prod_grs_au,
                prod_tons_pb       = EXCLUDED.prod_tons_pb,
                prod_tons_zn       = EXCLUDED.prod_tons_zn,
                conc_pb_tons_pb    = EXCLUDED.conc_pb_tons_pb,
                conc_pb_ag_kg_ton  = EXCLUDED.conc_pb_ag_kg_ton,
                conc_pb_au_grs_ton = EXCLUDED.conc_pb_au_grs_ton,
                conc_pb_pb_pct     = EXCLUDED.conc_pb_pb_pct,
                conc_zn_tons_zn    = EXCLUDED.conc_zn_tons_zn,
                conc_zn_ag_kg_ton  = EXCLUDED.conc_zn_ag_kg_ton,
                conc_zn_zn_pct     = EXCLUDED.conc_zn_zn_pct,
                ley_conc_au        = EXCLUDED.ley_conc_au,
                ley_conc_ag_pb     = EXCLUDED.ley_conc_ag_pb,
                ley_conc_ag_zn     = EXCLUDED.ley_conc_ag_zn,
                ley_conc_ag        = EXCLUDED.ley_conc_ag,
                ley_conc_pb        = EXCLUDED.ley_conc_pb,
                ley_conc_zn        = EXCLUDED.ley_conc_zn,
                updated_at         = now();
            """, nativeQuery = true)
    void upsert(@Param("budget") PlantBudget budget);
}