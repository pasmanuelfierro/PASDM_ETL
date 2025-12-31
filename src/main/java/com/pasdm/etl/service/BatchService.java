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

    private final GeologyRepository repository;

    @Transactional
    public void saveBatch(List<Geology> batch) {
        repository.saveAll(batch);
        repository.flush();
    }
}