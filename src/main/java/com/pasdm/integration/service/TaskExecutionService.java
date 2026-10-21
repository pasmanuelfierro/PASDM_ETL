package com.pasdm.integration.service;

import com.pasdm.integration.dto.SourceConfigurationDTO;
import com.pasdm.integration.dto.TaskExecutionDTO;
import com.pasdm.integration.dto.TaskExecutionRequestDTO;
import com.pasdm.integration.model.SourceConfiguration;
import com.pasdm.integration.model.TaskExecution;
import com.pasdm.integration.repository.SourceConfigurationRepository;
import com.pasdm.integration.repository.TaskExecutionRepository;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class TaskExecutionService {

    private final TaskExecutionRepository taskExecutionRepository;
    private final SourceConfigurationRepository sourceConfigurationRepository;

    public TaskExecutionService(TaskExecutionRepository taskExecutionRepository, SourceConfigurationRepository sourceConfigurationRepository) {
        this.taskExecutionRepository = taskExecutionRepository;
        this.sourceConfigurationRepository = sourceConfigurationRepository;
    }

    public List<TaskExecutionDTO> getTaskExecution() {
        return taskExecutionRepository
                .findAllByOrderByStartedAtDesc()
                .stream()
                .map(task -> {
                    SourceConfiguration configuration =
                            task.getSourceConfiguration();

                    SourceConfigurationDTO configurationDTO = null;

                    if (configuration != null) {
                        configurationDTO = new SourceConfigurationDTO(
                                configuration.getId(),
                                configuration.getName(),
                                configuration.getDirectoryPath()
                        );
                    }

                    return new TaskExecutionDTO(
                            task.getTaskName(),
                            task.getStatus(),
                            task.getTriggerType(),
                            task.getStartedAt(),
                            task.getFinishedAt(),
                            task.getRecordsRead(),
                            task.getRecordsInserted(),
                            task.getRecordsUpdated()
                    );
                })
                .toList();
    }


    @Transactional
    public TaskExecution createTaskExecution(TaskExecutionRequestDTO request) {

        OffsetDateTime now = OffsetDateTime.now();

        TaskExecution taskExecution = new TaskExecution();
        taskExecution.setId(request.getId());
        taskExecution.setTaskName(request.getTaskName());
        taskExecution.setTriggerType(request.getTriggerType());
        taskExecution.setStatus(request.getStatus());
        taskExecution.setStartedAt(now);
        taskExecution.setFinishedAt(now);
        taskExecution.setRecordsRead(request.getRecordsRead());
        taskExecution.setRecordsInserted(request.getRecordsInserted());
        taskExecution.setRecordsUpdated(request.getRecordsUpdated());
        taskExecution.setRecordsFailed(request.getRecordsFailed());
        taskExecution.setErrorMessage(request.getErrorMessage());

        return taskExecutionRepository.save(taskExecution);
    }

    @Transactional
    public TaskExecution updateTaskExecution(Long id, TaskExecutionRequestDTO request) {
        OffsetDateTime now = OffsetDateTime.now();

        TaskExecution taskExecution = taskExecutionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SourceConfiguration no encontrado id=" + id));

        ///TaskExecution taskExecution = new TaskExecution();
        taskExecution.setId(request.getId());
        taskExecution.setTaskName(request.getTaskName());
        taskExecution.setTriggerType(request.getTriggerType());
        taskExecution.setStatus(request.getStatus());
        taskExecution.setStartedAt(now);
        taskExecution.setFinishedAt(now);
        taskExecution.setRecordsRead(request.getRecordsRead());
        taskExecution.setRecordsInserted(request.getRecordsInserted());
        taskExecution.setRecordsUpdated(request.getRecordsUpdated());
        taskExecution.setRecordsFailed(request.getRecordsFailed());
        taskExecution.setErrorMessage(request.getErrorMessage());

        return taskExecutionRepository.save(taskExecution);
    }


/*
    public Page<TaskExecutionDTO> getTaskExecutionByFinishAt(
            OffsetDateTime finishedAt,
            Pageable pageable) {

        return taskExecutionRepository
                .findByFinishedAt(finishedAt, pageable)
                .map(task -> {
                    SourceConfiguration configuration =
                            task.getSourceConfiguration();

                    SourceConfigurationDTO configurationDTO = null;

                    if (configuration != null) {
                        configurationDTO = new SourceConfigurationDTO(
                                configuration.getId(),
                                configuration.getName(),
                                configuration.getDirectoryPath()
                        );
                    }

                    return new TaskExecutionDTO(
                            task.getTaskName(),
                            task.getStatus(),
                            task.getTriggerType(),
                            task.getStartedAt(),
                            task.getFinishedAt(),
                            task.getRecordsRead(),
                            task.getRecordsInserted(),
                            task.getRecordsUpdated()
                           // configurationDTO
                    );
                });
    }

 */

    public List<TaskExecutionDTO> getTaskExecutionOrigin(String triggerType) {
        if (!triggerType.equalsIgnoreCase("MANUAL") && !triggerType.equalsIgnoreCase("SCHEDULER")) {
            throw new IllegalArgumentException("Origen no válido" + triggerType);
        }
        return taskExecutionRepository.findByTriggerTypeIgnoreCase(triggerType)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private TaskExecutionDTO toDTO(TaskExecution task) {

        SourceConfiguration configuration = task.getSourceConfiguration();

        SourceConfigurationDTO configurationDTO = null;

        if (configuration != null) {
            configurationDTO = new SourceConfigurationDTO(
                    configuration.getId(),
                    configuration.getName(),
                    configuration.getDirectoryPath()
            );
        }

        return new TaskExecutionDTO(
                task.getTaskName(),
                task.getStatus(),
                task.getTriggerType(),
                task.getStartedAt(),
                task.getFinishedAt(),
                task.getRecordsRead(),
                task.getRecordsInserted(),
                task.getRecordsUpdated()
               // configurationDTO
        );
    }


}
