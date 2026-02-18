package com.pasdm.integration.repository;

import com.pasdm.integration.model.EntradaAcero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EntradaAceroRepository
        extends JpaRepository<EntradaAcero, Long> {

    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO etl.entrada_acero (
                fecha,
                recibido_por,
                codigo,
                acero,
                nombre_acero,
                cantidad,
                row_hash
            )
            VALUES (
                :#{#e.fecha},
                :#{#e.recibidoPor},
                :#{#e.codigo},
                :#{#e.acero},
                :#{#e.nombreAcero},
                :#{#e.cantidad},
                :#{#e.rowHash}
            )
            ON CONFLICT (row_hash)
            DO UPDATE SET
                fecha = EXCLUDED.fecha,
                recibido_por = EXCLUDED.recibido_por,
                codigo = EXCLUDED.codigo,
                acero = EXCLUDED.acero,
                nombre_acero = EXCLUDED.nombre_acero,
                cantidad = EXCLUDED.cantidad
            """, nativeQuery = true)
    void upsert(@Param("e") EntradaAcero e);
}