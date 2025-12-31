package com.pasdm.etl.service;

import com.pasdm.etl.model.Geology;
import com.pasdm.etl.repository.GeologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BatchService {

    private final GeologyRepository repositoryGeology;

    @Transactional
    public void saveBatch(List<Geology> batch) {
        repositoryGeology.saveAll(batch);
        repositoryGeology.flush();
    }
}