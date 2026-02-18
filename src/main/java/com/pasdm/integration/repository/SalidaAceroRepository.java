package com.pasdm.integration.repository;

import com.pasdm.integration.model.SalidaAcero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SalidaAceroRepository
        extends JpaRepository<SalidaAcero, Long> {

    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO etl.salida_acero (
                fecha,
                turno,
                grupo,
                codigo,
                acero,
                cantidad,
                equipo_cod,
                equipo,
                nomina_operador,
                operador,
                nomina_supervisor,
                supervisor,
                zona,
                proveedor,
                costo,
                row_hash
            )
            VALUES (
                :#{#e.fecha},
                :#{#e.turno},
                :#{#e.grupo},
                :#{#e.codigo},
                :#{#e.acero},
                :#{#e.cantidad},
                :#{#e.equipoCod},
                :#{#e.equipo},
                :#{#e.nominaOperador},
                :#{#e.operador},
                :#{#e.nominaSupervisor},
                :#{#e.supervisor},
                :#{#e.zona},
                :#{#e.proveedor},
                :#{#e.costo},
                :#{#e.rowHash}
            )
            ON CONFLICT (row_hash)
            DO UPDATE SET
                fecha = EXCLUDED.fecha,
                turno = EXCLUDED.turno,
                grupo = EXCLUDED.grupo,
                codigo = EXCLUDED.codigo,
                acero = EXCLUDED.acero,
                cantidad = EXCLUDED.cantidad,
                equipo_cod = EXCLUDED.equipo_cod,
                equipo = EXCLUDED.equipo,
                nomina_operador = EXCLUDED.nomina_operador,
                operador = EXCLUDED.operador,
                nomina_supervisor = EXCLUDED.nomina_supervisor,
                supervisor = EXCLUDED.supervisor,
                zona = EXCLUDED.zona,
                proveedor = EXCLUDED.proveedor,
                costo = EXCLUDED.costo
                -- row_hash NO se actualiza
            """, nativeQuery = true)
    void upsert(@Param("e") SalidaAcero e);
}