package com.pasdm.integration.repository;

import com.pasdm.integration.model.Security;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface SecurityRepository extends JpaRepository<Security, Long> {

    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO etl.security (
                fecha, tipo, no, comentario, row_hash, created_at
            )
            VALUES (
                :#{#s.fecha}, :#{#s.tipo}, :#{#s.no}, :#{#s.comentario}, :#{#s.rowHash}, now()
            )
            ON CONFLICT (row_hash)
            DO UPDATE SET
                fecha = EXCLUDED.fecha,
                tipo = EXCLUDED.tipo,
                no = EXCLUDED.no,
                comentario = EXCLUDED.comentario,
                updated_at = now();
            """, nativeQuery = true)
    void upsert(@Param("s") Security s);
}
