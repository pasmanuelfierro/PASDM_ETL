package com.pasdm.integration.repository;

import com.pasdm.integration.model.PlantBudget;
import com.pasdm.integration.model.PlantConsumibles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PlantConsumiblesRepository extends JpaRepository<PlantConsumibles, Long> {
    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO etl.plant_consumibles (
                fecha,
                turno,
                cal,
                deprezinc,
                sulfato_cobre,
                floculante,
                aerofina_3418,
                espumante,
                ro_2,
                bisulfito_amonio,
                bola_2_5,
                xantato,
                anti_incrustante,
                agua_fresca,
                sol_ret,
                horas_operacion,
                horas_disponibles,
                horas_trituradas,
                granulometria_pct,
                row_hash,
                created_at
            )
            VALUES (
                :#{#entity.fecha},
                :#{#entity.turno},
                :#{#entity.cal},
                :#{#entity.deprezinc},
                :#{#entity.sulfatoCobre},
                :#{#entity.floculante},
                :#{#entity.aerofina3418},
                :#{#entity.espumante},
                :#{#entity.ro2},
                :#{#entity.bisulfitoAmonio},
                :#{#entity.bola25},
                :#{#entity.xantato},
                :#{#entity.antiIncrustante},
                :#{#entity.aguaFresca},
                :#{#entity.solRet},
                :#{#entity.horasOperacion},
                :#{#entity.horasDisponibles},
                :#{#entity.horasTrituradas},
                :#{#entity.granulometriaPct},
                :#{#entity.rowHash},
                now() 
            )
            ON CONFLICT (row_hash)
            DO UPDATE SET
                fecha = EXCLUDED.fecha,
                turno = EXCLUDED.turno,
                cal = EXCLUDED.cal,
                deprezinc = EXCLUDED.deprezinc,
                sulfato_cobre = EXCLUDED.sulfato_cobre,
                floculante = EXCLUDED.floculante,
                aerofina_3418 = EXCLUDED.aerofina_3418,
                espumante = EXCLUDED.espumante,
                ro_2 = EXCLUDED.ro_2,
                bisulfito_amonio = EXCLUDED.bisulfito_amonio,
                bola_2_5 = EXCLUDED.bola_2_5,
                xantato = EXCLUDED.xantato,
                anti_incrustante = EXCLUDED.anti_incrustante,
                agua_fresca = EXCLUDED.agua_fresca,
                sol_ret = EXCLUDED.sol_ret,
                horas_operacion = EXCLUDED.horas_operacion,
                horas_disponibles = EXCLUDED.horas_disponibles,
                horas_trituradas = EXCLUDED.horas_trituradas,
                granulometria_pct = EXCLUDED.granulometria_pct,
                updated_at = now();
            """, nativeQuery = true)
    void upsert(@Param("entity") PlantConsumibles entity);
}