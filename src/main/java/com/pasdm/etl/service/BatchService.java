package com.pasdm.etl.service;

import com.pasdm.etl.model.Geology;
import com.pasdm.etl.model.Plant;
import com.pasdm.etl.repository.GeologyRepository;
import com.pasdm.etl.repository.PlantRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BatchService {

    private final GeologyRepository repositoryGeology;
    private final PlantRepository repositoryPlant;

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
}