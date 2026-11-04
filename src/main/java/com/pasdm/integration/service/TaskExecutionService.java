package com.pasdm.integration.service;

import com.pasdm.integration.dto.*;
import com.pasdm.integration.enums.SheetType;
import com.pasdm.integration.model.SourceConfiguration;
import com.pasdm.integration.model.TaskExecution;
import com.pasdm.integration.repository.SourceConfigurationRepository;
import com.pasdm.integration.repository.TaskExecutionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class TaskExecutionService {

    private final TaskExecutionRepository taskExecutionRepository;
    private final SourceConfigurationRepository sourceConfigurationRepository;
    private final ETLAsyncProcessorService asyncProcessorService;

    public TaskExecutionService(TaskExecutionRepository taskExecutionRepository, SourceConfigurationRepository sourceConfigurationRepository, ExcelStreamingService excelStreamingService, ETLAsyncProcessorService asyncProcessorService) {
        this.taskExecutionRepository = taskExecutionRepository;
        this.sourceConfigurationRepository = sourceConfigurationRepository;
        this.asyncProcessorService = asyncProcessorService;

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

    @Transactional
    public ExecutionResponseDTO executeTask(ExecuteRequestDTO request) {

        SourceConfiguration configuration =
                sourceConfigurationRepository
                        .findById(request.getSourceConfigurationId())
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "No existe la configuración de la tarea"
                                )
                        );

        if (!"true".equalsIgnoreCase(configuration.getEnabled())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "La configuración está deshabilitada"
            );
        }

        SheetType sheetType = resolveSheetType(configuration.getName());
        TaskExecution execution = new TaskExecution();

        execution.setSourceConfiguration(configuration);
        execution.setTaskName(sheetType.name());
        execution.setStatus("RUNNING");
        execution.setStartedAt(OffsetDateTime.now());
        execution.setTriggerType(
                String.valueOf(request.getTriggerType())
        );

        //  contadores
        execution.setRecordsRead(String.valueOf(0L));
        execution.setRecordsInserted(String.valueOf(0L));

        TaskExecution savedExecution = taskExecutionRepository.save(execution);

        asyncProcessorService.processTask(
                savedExecution.getId(),
                configuration.getId(),
                sheetType.name()
        );

        return new ExecutionResponseDTO(
                savedExecution.getId(),
                "RUNNING",
                "La ejecución fue aceptada"
        );
    }

    private SheetType resolveSheetType(String taskName) {

        if (taskName == null || taskName.isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre de la tarea no puede estar vacío"
            );
        }

        return switch (taskName.trim().toUpperCase()) {
            case "GEOLOGIA" -> SheetType.GEOLOGY;
            case "GRADOS DE LABORATORIO" -> SheetType.LABORATORY;
            case "PLANTA" -> SheetType.PLANT;

            default -> throw new IllegalArgumentException(
                    "No existe un tipo de tarea para: " + taskName
            );
        };
    }

}
