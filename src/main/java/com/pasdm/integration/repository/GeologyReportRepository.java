package com.pasdm.integration.repository;

import com.pasdm.integration.model.GeologyReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface GeologyReportRepository extends JpaRepository<GeologyReport, Long> {
    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO etl.geology_report (
                                      fecha, turno, mina, zona, tipo, veta, lugar, estatus, comentario,
                                      proy_mineral, linea_rez, ancho_veta, ancho_programado, ancho_obra,
                                      dilucion, ag1, au1, pb1, zn1, block_reservas, ley_block,
                                      ag, au, pb, zn, vpt, ag_plan_dil_0, dil_plan, dil_piso, dil_piso_2,
                                      row_hash
                                  ) VALUES (
                                      :#{#geologyReport.fecha},
                                      :#{#geologyReport.turno},
                                      :#{#geologyReport.mina},
                                      :#{#geologyReport.zona},
                                      :#{#geologyReport.tipo},
                                      :#{#geologyReport.veta},
                                      :#{#geologyReport.lugar},
                                      :#{#geologyReport.estatus},
                                      :#{#geologyReport.comentario},
                                      :#{#geologyReport.proyMineral},
                                      :#{#geologyReport.lineaRez},
                                      :#{#geologyReport.anchoVeta},
                                      :#{#geologyReport.anchoProgramado},
                                      :#{#geologyReport.anchoObra},
                                      :#{#geologyReport.dilucion},
                                      :#{#geologyReport.ag1},
                                      :#{#geologyReport.au1},
                                      :#{#geologyReport.pb1},
                                      :#{#geologyReport.zn1},
                                      :#{#geologyReport.blockReservas},
                                      :#{#geologyReport.leyBlock},
                                      :#{#geologyReport.ag},
                                      :#{#geologyReport.au},
                                      :#{#geologyReport.pb},
                                      :#{#geologyReport.zn},
                                      :#{#geologyReport.vpt},
                                      :#{#geologyReport.agPlanDil0},
                                      :#{#geologyReport.dilPlan},
                                      :#{#geologyReport.dilPiso},
                                      :#{#geologyReport.dilPiso2},
                                      :#{#geologyReport.rowHash}
                                  )
                                  ON CONFLICT (row_hash)
                                  DO UPDATE SET
                                      turno = EXCLUDED.turno,
                                      mina = EXCLUDED.mina,
                                      zona = EXCLUDED.zona,
                                      tipo = EXCLUDED.tipo,
                                      veta = EXCLUDED.veta,
                                      lugar = EXCLUDED.lugar,
                                      estatus = EXCLUDED.estatus,
                                      comentario = EXCLUDED.comentario,
                                      proy_mineral = EXCLUDED.proy_mineral,
                                      linea_rez = EXCLUDED.linea_rez,
                                      ancho_veta = EXCLUDED.ancho_veta,
                                      ancho_programado = EXCLUDED.ancho_programado,
                                      ancho_obra = EXCLUDED.ancho_obra,
                                      dilucion = EXCLUDED.dilucion,
                                      ag1 = EXCLUDED.ag1,
                                      au1 = EXCLUDED.au1,
                                      pb1 = EXCLUDED.pb1,
                                      zn1 = EXCLUDED.zn1,
                                      block_reservas = EXCLUDED.block_reservas,
                                      ley_block = EXCLUDED.ley_block,
                                      ag = EXCLUDED.ag,
                                      au = EXCLUDED.au,
                                      pb = EXCLUDED.pb,
                                      zn = EXCLUDED.zn,
                                      vpt = EXCLUDED.vpt,
                                      ag_plan_dil_0 = EXCLUDED.ag_plan_dil_0,
                                      dil_plan = EXCLUDED.dil_plan,
                                      dil_piso = EXCLUDED.dil_piso,
                                      dil_piso_2 = EXCLUDED.dil_piso_2,
                                      updated_at         = now();
            """, nativeQuery = true)
    void upsert(@Param("geologyReport") GeologyReport geologyReport);
}