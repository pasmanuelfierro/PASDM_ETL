package com.pasdm.etl.repository;

import com.pasdm.etl.model.LaboratoryPlant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LaboratoryPlantRepository extends JpaRepository<LaboratoryPlant, Long> {
    @Modifying
    @Transactional
    @Query(value = """
    INSERT INTO etl.laboratory_plant (
        num_dia,
        turno,
        ban_au,
        ban_ag,
        ban_pb,
        ban_zn,
        ban_humedad,
        finos_ag,
        finos_pb,
        finos_zn,
        row_hash,
        created_at,
        updated_at
    )
    VALUES (
        :#{#labPlan.numDia},
        :#{#labPlan.turno},
        :#{#labPlan.banAu},
        :#{#labPlan.banAg},
        :#{#labPlan.banPb},
        :#{#labPlan.banZn},
        :#{#labPlan.banHumedad},
        :#{#labPlan.finosAg},
        :#{#labPlan.finosPb},
        :#{#labPlan.finosZn},
        :#{#labPlan.rowHash},
        :#{#labPlan.createdAt},
        :#{#labPlan.updatedAt}
    )
    ON CONFLICT (num_dia, turno)
    DO UPDATE SET
        ban_au        = EXCLUDED.ban_au,
        ban_ag        = EXCLUDED.ban_ag,
        ban_pb        = EXCLUDED.ban_pb,
        ban_zn        = EXCLUDED.ban_zn,
        ban_humedad   = EXCLUDED.ban_humedad,
        finos_ag      = EXCLUDED.finos_ag,
        finos_pb      = EXCLUDED.finos_pb,
        finos_zn      = EXCLUDED.finos_zn,
        row_hash      = EXCLUDED.row_hash,
        updated_at    = EXCLUDED.updated_at
""", nativeQuery = true)
    void upsert(@Param("labPlan") LaboratoryPlant labPlan);

}
