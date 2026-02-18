package com.pasdm.integration.repository;

import com.pasdm.integration.model.Explosives;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ExplosivesRepository
        extends JpaRepository<Explosives, Long> {

    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO etl.explosives (
                fecha, mina, mdo_minado, obra, lugar, mineral, utilizado_por,
                marca, turno, grupo, supervisor, costo, equipo,
                e_anfo_ug, d_anfo_ug, e_anfo_premium, d_anfo_premium,
                e_1x8, d_1x8, e_114x16, d_114x16,
                e_t1, d_t1, e_112x39, d_112x39, e_2x16, d_2x16,
                e_cordon, d_cordon, e_booster, d_booster,
                powersplit_e, powersplit_d,
                cable_harness_e, cable_harness_d,
                cordon_sismico_e, cordon_sismico_d,
                stinger_20gr_e, stinger_20gr_d,
                ikon_10ft_e, ikon_10ft_d,
                fecha_operacion,
                row_hash
            ) VALUES (
                :#{#explosive.fecha}, :#{#explosive.mina}, :#{#explosive.mdoMinado}, :#{#explosive.obra}, :#{#explosive.lugar}, :#{#explosive.mineral}, :#{#explosive.utilizadoPor},
                :#{#explosive.marca}, :#{#explosive.turno}, :#{#explosive.grupo}, :#{#explosive.supervisor}, :#{#explosive.costo}, :#{#explosive.equipo},
                :#{#explosive.eAnfoUg}, :#{#explosive.dAnfoUg}, :#{#explosive.eAnfoPremium}, :#{#explosive.dAnfoPremium},
                :#{#explosive.e1x8}, :#{#explosive.d1x8}, :#{#explosive.e114x16}, :#{#explosive.d114x16},
                :#{#explosive.eT1}, :#{#explosive.dT1}, :#{#explosive.e112x39}, :#{#explosive.d112x39}, :#{#explosive.e2x16}, :#{#explosive.d2x16},
                :#{#explosive.eCordon}, :#{#explosive.dCordon}, :#{#explosive.eBooster}, :#{#explosive.dBooster},
                :#{#explosive.powersplitE}, :#{#explosive.powersplitD},
                :#{#explosive.cableHarnessE}, :#{#explosive.cableHarnessD},
                :#{#explosive.cordonSismicoE}, :#{#explosive.cordonSismicoD},
                :#{#explosive.stinger20grE}, :#{#explosive.stinger20grD},
                :#{#explosive.ikon10ftE}, :#{#explosive.ikon10ftD},
                :#{#explosive.fechaOperacion},
                :#{#explosive.rowHash}
            )
            ON CONFLICT (row_hash)
            DO UPDATE SET
                mina = EXCLUDED.mina,
                mdo_minado = EXCLUDED.mdo_minado,
                obra = EXCLUDED.obra,
                lugar = EXCLUDED.lugar,
                mineral = EXCLUDED.mineral,
                utilizado_por = EXCLUDED.utilizado_por,
                marca = EXCLUDED.marca,
                turno = EXCLUDED.turno,
                grupo = EXCLUDED.grupo,
                supervisor = EXCLUDED.supervisor,
                costo = EXCLUDED.costo,
                equipo = EXCLUDED.equipo,
            
                e_anfo_ug = EXCLUDED.e_anfo_ug,
                d_anfo_ug = EXCLUDED.d_anfo_ug,
                e_anfo_premium = EXCLUDED.e_anfo_premium,
                d_anfo_premium = EXCLUDED.d_anfo_premium,
            
                e_1x8 = EXCLUDED.e_1x8,
                d_1x8 = EXCLUDED.d_1x8,
                e_114x16 = EXCLUDED.e_114x16,
                d_114x16 = EXCLUDED.d_114x16,
                            
                e_t1 = EXCLUDED.e_t1,
                d_t1 = EXCLUDED.d_t1,
                e_112x39 = EXCLUDED.e_112x39,
                d_112x39 = EXCLUDED.d_112x39,
                e_2x16 = EXCLUDED.e_2x16,
                d_2x16 = EXCLUDED.d_2x16,
            
                e_cordon = EXCLUDED.e_cordon,
                d_cordon = EXCLUDED.d_cordon,
                e_booster = EXCLUDED.e_booster,
                d_booster = EXCLUDED.d_booster,
            
                powersplit_e = EXCLUDED.powersplit_e,
                powersplit_d = EXCLUDED.powersplit_d,
                cable_harness_e = EXCLUDED.cable_harness_e,
                cable_harness_d = EXCLUDED.cable_harness_d,
                cordon_sismico_e = EXCLUDED.cordon_sismico_e,
                cordon_sismico_d = EXCLUDED.cordon_sismico_d,
                stinger_20gr_e = EXCLUDED.stinger_20gr_e,
                stinger_20gr_d = EXCLUDED.stinger_20gr_d,
                ikon_10ft_e = EXCLUDED.ikon_10ft_e,
                ikon_10ft_d = EXCLUDED.ikon_10ft_d,
            
                fecha_operacion = EXCLUDED.fecha_operacion
            """, nativeQuery = true)
    void upsert(Explosives explosive);
}