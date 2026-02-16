package com.pasdm.integration.repository;

import com.pasdm.integration.model.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProductionRepository extends JpaRepository<Production, Long> {

    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO etl.production (
                fecha, lote, mina, zona, empresa, obra, estatus, estructura, labor,
                ley_ag, ley_au, ley_pb, ley_zn, vpt, tipo, tons,
                sum_ag, sum_au, sum_pb, sum_zn, sum_vpt, row_hash, created_at
            )
            VALUES (
                :#{#p.fecha}, :#{#p.lote}, :#{#p.mina}, :#{#p.zona}, :#{#p.empresa}, :#{#p.obra}, :#{#p.estatus}, :#{#p.estructura}, :#{#p.labor},
                :#{#p.leyAg}, :#{#p.leyAu}, :#{#p.leyPb}, :#{#p.leyZn}, :#{#p.vpt}, :#{#p.tipo}, :#{#p.tons},
                :#{#p.sumAg}, :#{#p.sumAu}, :#{#p.sumPb}, :#{#p.sumZn}, :#{#p.sumVpt}, :#{#p.rowHash}, now()
            )
            ON CONFLICT (row_hash)
            DO UPDATE SET
                fecha = EXCLUDED.fecha,
                lote = EXCLUDED.lote,
                mina = EXCLUDED.mina,
                zona = EXCLUDED.zona,
                empresa = EXCLUDED.empresa,
                obra = EXCLUDED.obra,
                estatus = EXCLUDED.estatus,
                estructura = EXCLUDED.estructura,
                labor = EXCLUDED.labor,
                ley_ag = EXCLUDED.ley_ag,
                ley_au = EXCLUDED.ley_au,
                ley_pb = EXCLUDED.ley_pb,
                ley_zn = EXCLUDED.ley_zn,
                vpt = EXCLUDED.vpt,
                tipo = EXCLUDED.tipo,
                tons = EXCLUDED.tons,
                sum_ag = EXCLUDED.sum_ag,
                sum_au = EXCLUDED.sum_au,
                sum_pb = EXCLUDED.sum_pb,
                sum_zn = EXCLUDED.sum_zn,
                sum_vpt = EXCLUDED.sum_vpt,
                updated_at = now();
            """, nativeQuery = true)
    void upsert(@Param("p") Production p);
}

