package com.pasdm.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pasdm.integration.model.Topography;

public interface TopographyRepository extends JpaRepository<Topography, Long> {

    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO etl.topography (
                lugar_d, obra, tipo_minado, zona, veta, tipo_material, prioridad, lote, empresa, 
                cuenta, grupo, area, ancho_plan, alto_plan, lad, fecha, avance, disp,
                ancho, alto, ton, row_hash, created_at, updated_at
            )
            VALUES (
                :#{#t.lugarD}, :#{#t.obra}, :#{#t.tipoMinado}, :#{#t.zona}, :#{#t.veta}, :#{#t.tipoMaterial}, :#{#t.prioridad}, :#{#t.lote}, :#{#t.empresa}, 
                :#{#t.cuenta}, :#{#t.grupo}, :#{#t.area}, :#{#t.anchoPlan}, :#{#t.altoPlan}, :#{#t.lad}, :#{#t.fecha}, :#{#t.avance}, :#{#t.disp},
                :#{#t.ancho}, :#{#t.alto}, :#{#t.ton}, :#{#t.rowHash}, now(), now()
            )
            ON CONFLICT (row_hash)
            DO UPDATE SET
                lugar_d = EXCLUDED.lugar_d,
                obra = EXCLUDED.obra,
                tipo_minado = EXCLUDED.tipo_minado,
                zona = EXCLUDED.zona,
                veta = EXCLUDED.veta,
                tipo_material = EXCLUDED.tipo_material,
                prioridad = EXCLUDED.prioridad,
                lote = EXCLUDED.lote,
                empresa = EXCLUDED.empresa,
                cuenta = EXCLUDED.cuenta,
                grupo = EXCLUDED.grupo,
                area = EXCLUDED.area,
                ancho_plan = EXCLUDED.ancho_plan,
                alto_plan = EXCLUDED.alto_plan,
                lad = EXCLUDED.lad,
                fecha = EXCLUDED.fecha,
                avance = EXCLUDED.avance,
                disp = EXCLUDED.disp,
                ancho = EXCLUDED.ancho,
                alto = EXCLUDED.alto,
                ton = EXCLUDED.ton,
                row_hash = EXCLUDED.row_hash,
                created_at = EXCLUDED.created_at,
                updated_at = now();
            """, nativeQuery = true)
    void upsert(@Param("t") Topography t);
    
}
