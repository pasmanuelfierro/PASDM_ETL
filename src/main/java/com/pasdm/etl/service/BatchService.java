package com.pasdm.etl.service;

import com.pasdm.etl.model.*;
import com.pasdm.etl.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BatchService {

    private final GeologyRepository repositoryGeology;
    private final RRHHRepository repositoryRRHH;
    private final MTTORepository repositoryMTTO;
    private final ProductionRepository productionReposiroty;
    private final DevelopmentRepository developmentRepository;
    private final SecurityRepository securityRepository;
    private final PlantActualRepository plantActualRepository;
    private final NamedParameterJdbcTemplate jdbc;
    private final LaboratoryRepository laboratoryRepository;

/*    @Transactional
    public void saveBatchPlantV1(List<Plant> batch) {
        repositoryPlantV1.saveAll(batch);
        repositoryPlantV1.flush();
    }*/

    @Transactional
    public void saveBatchGeology(List<Geology> batch) {
        repositoryGeology.saveAll(batch);
        repositoryGeology.flush();
    }

    @Transactional
    public void saveBatchRRHH(List<RRHH> batch) {
        repositoryRRHH.saveAll(batch);
        repositoryRRHH.flush();
    }

    @Transactional
    public void saveBatchMTTO(List<MTTO> batch) {
        repositoryMTTO.saveAll(batch);
        repositoryMTTO.flush();
    }

    @Transactional
    public void saveBatchProduction(List<Production> batch) {
        log.info("Guardando batch de Production {}", batch.size());
        for (Production p : batch) {
            productionReposiroty.upsert(p);
        }
        productionReposiroty.flush();
    }

    @Transactional
    public void saveBatchDevelopment(List<Development> batch) {
        log.info("Guardando batch de {}", batch.size());
        developmentRepository.saveAll(batch);
        developmentRepository.flush();
    }

    @Transactional
    public void upsertBatchDevelopment(List<Development> batch) {
        log.info("Guardando batch de Development {}", batch.size());
        for (Development d : batch) {
            developmentRepository.upsert(d);
        }
    }

    @Transactional
    public void upsertBatchSecurity(List<Security> batch) {
        log.info("Guardando batch de Security {}", batch.size());
        for (Security s : batch) {
            securityRepository.upsert(s);
        }
    }

    @Transactional
    public void upsertBatchPlantActual(List<PlantActual> batch) {
        log.info("Guardando batch de plant  {}", batch.size());
        for (PlantActual planta : batch) {
            plantActualRepository.upsert(planta);
        }
    }

    @Transactional
    public void upsertBatchLaboratory(List<Laboratory> batch) {
        log.info("Guardando batch de laboratory {}", batch.size());
        for (Laboratory l : batch) {
//            laboratoryRepository.upsert(l);
        }
    }


}