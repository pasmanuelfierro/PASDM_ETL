package com.pasdm.integration.service;

import com.pasdm.integration.dto.SourceConfigurationRequestDTO;
import com.pasdm.integration.model.SourceConfiguration;
import com.pasdm.integration.repository.SourceConfigurationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class SourceConfigurationService {

    private final SourceConfigurationRepository sourceConfigurationRepository;

    public SourceConfigurationService(SourceConfigurationRepository sourceConfigurationRepository) {
        this.sourceConfigurationRepository = sourceConfigurationRepository;
    }

    @Transactional
    public SourceConfiguration createSourceConfiguration(SourceConfigurationRequestDTO request){
        OffsetDateTime now = OffsetDateTime.now();

        SourceConfiguration sourceConfiguration = new SourceConfiguration();

        sourceConfiguration.setName(request.getName());
        sourceConfiguration.setHost(request.getHost());
        sourceConfiguration.setDirectoryPath(request.getDirectoryPath());
        sourceConfiguration.setFilePattern(request.getFilePattern());
        sourceConfiguration.setEnabled(request.getEnabled());
        sourceConfiguration.setUpdatedAt(now);

        return sourceConfigurationRepository.save(sourceConfiguration);
    }


    @Transactional
    public SourceConfiguration updateSourceConfiguration(Long id, SourceConfigurationRequestDTO request) {
        OffsetDateTime now = OffsetDateTime.now();
        SourceConfiguration sourceConfiguration = sourceConfigurationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SourceConfiguration no encontrado id=" + id));

        sourceConfiguration.setName(request.getName());
        sourceConfiguration.setHost(request.getHost());
        sourceConfiguration.setDirectoryPath(request.getDirectoryPath());
        sourceConfiguration.setFilePattern(request.getFilePattern());
        sourceConfiguration.setEnabled(request.getEnabled());
        sourceConfiguration.setUpdatedAt(now);
        return sourceConfigurationRepository.save(sourceConfiguration);
    }
}
