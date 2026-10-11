package com.pasdm.integration.repository;

import com.pasdm.integration.model.Laboratory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LaboratoryRepository extends JpaRepository<Laboratory, Long> {

    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO etl.laboratory (
                               fecha, tipo, row_hash,
                               mina, exploracion, planta,
                               embarques_pb, embarques_zn,
                               muestras_especiales, metalurgias,
                               comercializacion, reensayes, qaqc
                           )
                           VALUES (
                               :#{#laboratory.fecha},
                               :#{#laboratory.tipo},
                               :#{#laboratory.rowHash},
            
                               :#{#laboratory.mina},
                               :#{#laboratory.exploracion},
                               :#{#laboratory.planta},
                               :#{#laboratory.embarquesPb},
                               :#{#laboratory.embarquesZn},
                               :#{#laboratory.muestrasEspeciales},
                               :#{#laboratory.metalurgias},
                               :#{#laboratory.comercializacion},
                               :#{#laboratory.reensayes},
                               :#{#laboratory.qaqc}
                           )
                           ON CONFLICT (row_hash)
                           DO UPDATE SET
                               mina                   = EXCLUDED.mina,
                               exploracion            = EXCLUDED.exploracion,
                               planta                 = EXCLUDED.planta,
                               embarques_pb           = EXCLUDED.embarques_pb,
                               embarques_zn           = EXCLUDED.embarques_zn,
                               muestras_especiales    = EXCLUDED.muestras_especiales,
                               metalurgias             = EXCLUDED.metalurgias,
                               comercializacion       = EXCLUDED.comercializacion,
                               reensayes              = EXCLUDED.reensayes,
                               qaqc                   = EXCLUDED.qaqc,
                               updated_at             = now();
            """, nativeQuery = true)
    void upsert(@Param("laboratory") Laboratory laboratory);

}
