package com.pasdm.integration.service;

import com.pasdm.integration.model.*;
import com.pasdm.integration.repository.*;
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
    private final ProductionRepository productionRepository;
    private final DevelopmentRepository developmentRepository;
    private final SecurityRepository securityRepository;
    private final PlantActualRepository plantActualRepository;
    private final PlantBudgetRepository plantBudgetRepository;
    private final NamedParameterJdbcTemplate jdbc;
    private final LaboratoryRepository laboratoryRepository;
    private final GeologyDrillingRepository geologyDrillingRepository;
    private final LaboratoryPlantRepository laboratoryPlantRepository;
    private final GeologyGradeRepository geologyGradeRepository;
    private final GeologyReportRepository geologyReportRepository;
    private final DieselReportRepository dieselReportRepository;
    private final TopographyRepository topographyRepository;
    private final ExplosivesRepository explosivesRepository;
    private final EntradaAceroRepository entradaAceroRepository;
    private final SalidaAceroRepository salidaAceroRepository;


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
            productionRepository.upsert(p);
        }
        productionRepository.flush();
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
        developmentRepository.flush();
    }

    @Transactional
    public void upsertBatchSecurity(List<Security> batch) {
        log.info("Guardando batch de Security {}", batch.size());
        for (Security s : batch) {
            securityRepository.upsert(s);
        }
        securityRepository.flush();
    }

    @Transactional
    public void upsertBatchPlantActual(List<PlantActual> batch) {
        log.info("Guardando batch de plant  {}", batch.size());
        for (PlantActual planta : batch) {
            plantActualRepository.upsert(planta);
        }
        plantActualRepository.flush();
    }

    @Transactional
    public void upsertBatchPlantBudget(List<PlantBudget> batch) {
        log.info("Guardando batch de plant  {}", batch.size());
        for (PlantBudget plantaBudget : batch) {
            plantBudgetRepository.upsert(plantaBudget);
        }
        plantBudgetRepository.flush();
    }

    @Transactional
    public void upsertBatchLaboratory(List<Laboratory> batch) {
        log.info("Guardando batch de plant  {}", batch.size());
        for (Laboratory lab : batch) {
            //  laboratoryRepository.upsert(lab);
        }
    }

    @Transactional
    public void upsertBatchGeologyDrilling(List<GeologyDrilling> batch) {
        log.info("Guardando batch de GeologyDrilling {}", batch.size());
        for (GeologyDrilling geologyDrilling : batch) {
            geologyDrillingRepository.upsert(geologyDrilling);
        }
    }

    @Transactional
    public void upsertBatchGeologyGrade(List<GeologyGrade> batch) {
        log.info("Guardando batch de geology grade  {}", batch.size());
        for (GeologyGrade geologyGrade : batch) {
            geologyGradeRepository.upsert(geologyGrade);
        }
    }

    @Transactional
    public void upsertBatchLaboratoryPlant(List<LaboratoryPlant> batch) {
        log.info("Guardando batch de LaboratoryPlant {}", batch.size());

        for (LaboratoryPlant labPlan : batch) {
            laboratoryPlantRepository.upsert(labPlan);
        }
    }

    @Transactional
    public void upsertBatchGeologyReport(List<GeologyReport> batch) {
        log.info("Guardando batch de geology report  {}", batch.size());
        for (GeologyReport geologyReport : batch) {
            geologyReportRepository.upsert(geologyReport);
        }
    }

    @Transactional
    public void upsertBatchDieselReport(List<DieselReport> batch) {
        log.info("Guardando batch de diesel report  {}", batch.size());
        for (DieselReport dieselReport : batch) {
            dieselReportRepository.upsert(dieselReport);
        }
    }

    @Transactional
    public void upsertBatchTopography(List<Topography> batch) {
        log.info("Guardando batch de topography  {}", batch.size());
        for (Topography topography : batch) {
            topographyRepository.upsert(topography);
        }
    }

    @Transactional
    public void upsertBatchExplosives(List<Explosives> batch) {
        log.info("Guardando batch de explosivos  {}", batch.size());
        for (Explosives explosives : batch) {
            explosivesRepository.upsert(explosives);
        }
    }

    @Transactional
    public void upsertBatchEntradaAcero(List<EntradaAcero> batch) {
        log.info("Guardando batch de ENTRADA ACERO  {}", batch.size());
        for (EntradaAcero in : batch) {
            entradaAceroRepository.upsert(in);
        }
    }

    @Transactional
    public void upsertBatchSalidaAcero(List<SalidaAcero> batch) {
        log.info("Guardando batch de SALIDA ACERO  {}", batch.size());
        for (SalidaAcero out : batch) {
            salidaAceroRepository.upsert(out);
        }
    }
}