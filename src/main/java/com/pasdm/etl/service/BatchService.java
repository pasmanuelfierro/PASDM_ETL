package com.pasdm.etl.service;

import com.pasdm.etl.model.*;
import com.pasdm.etl.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BatchService {

    private final GeologyRepository repositoryGeology;
    private final PlantRepository repositoryPlant;
    private final RRHHRepository repositoryRRHH;
    private final MTTORepository repositoryMTTO;
    private final ProductionRepository productionReposiroty;
    private final DevelopmentRepository developmentRepository;
    private final SecurityRepository securityRepository;

    @Transactional
    public void saveBatchGeology(List<Geology> batch) {
        repositoryGeology.saveAll(batch);
        repositoryGeology.flush();
    }

    @Transactional
    public void saveBatchPlant(List<Plant> batch) {
        repositoryPlant.saveAll(batch);
        repositoryPlant.flush();
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
}