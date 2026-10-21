package com.pasdm.integration.service;

import com.pasdm.integration.dto.SourceConfigurationDTO;
import com.pasdm.integration.dto.SourceConfigurationRequestDTO;
import com.pasdm.integration.dto.TaskExecutionDTO;
import com.pasdm.integration.model.SourceConfiguration;
import com.pasdm.integration.model.TaskExecution;
import com.pasdm.integration.repository.SourceConfigurationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class SourceConfigurationService {

    private final SourceConfigurationRepository sourceConfigurationRepository;

    public SourceConfigurationService(SourceConfigurationRepository sourceConfigurationRepository) {
        this.sourceConfigurationRepository = sourceConfigurationRepository;
    }
    public List<SourceConfigurationDTO> getSourceConfigurations() {

        return sourceConfigurationRepository.findAll()
                .stream()
                .map(source -> {
                    TaskExecution task = source.getTaskExecutionId();
                    TaskExecutionDTO taskDTO = task != null
                            ? new TaskExecutionDTO(
                            task.getTaskName(),
                            task.getStatus(),
                            task.getTriggerType(),
                            task.getStartedAt(),
                            task.getFinishedAt(),
                            task.getRecordsRead(),
                            task.getRecordsInserted(),
                            task.getRecordsUpdated()

                    )
                            : null;

                    return new SourceConfigurationDTO(
                            source.getId(),
                            source.getDirectoryPath(),
                            source.getName(),
                            source.getHost(),
                            source.getFilePattern(),
                            source.getEnabled(),
                            source.getUpdatedAt(),
                            taskDTO
                    );
                })
                .toList();
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
