package com.pasdm.integration.service;

import com.pasdm.integration.dto.*;
import com.pasdm.integration.enums.SheetType;
import com.pasdm.integration.model.SourceConfiguration;
import com.pasdm.integration.model.TaskExecution;
import com.pasdm.integration.repository.SourceConfigurationRepository;
import com.pasdm.integration.repository.TaskExecutionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.apache.commons.collections4.MapUtils.getLongValue;

@Slf4j
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
        return taskExecutionRepository.findAllByOrderByStartedAtDesc()
                .stream()
                .map(task -> {
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


    public ExecutionDetailDTO getExecutionById(Long id) {

        TaskExecution execution = taskExecutionRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "No existe la ejecución: " + id
                        )
                );

        return ExecutionDetailDTO.builder()
                .id(execution.getId())
                .taskName(execution.getTaskName())
                .triggerType(execution.getTriggerType())
                .status(execution.getStatus())
                .startedAt(execution.getStartedAt())
                .finishedAt(execution.getFinishedAt())
                .recordsRead(execution.getRecordsRead())
                .recordsInserted(execution.getRecordsInserted())
                .errorMessage(execution.getErrorMessage())
                .build();
    }


    public LatestExecutionDTO getLatestExecution(Long configurationId) {
        Optional<TaskExecution> result = taskExecutionRepository.findFirstBySourceConfiguration_IdOrderByStartedAtDescIdDesc(configurationId);

        if (result.isEmpty()) {
            return null;
        }
        TaskExecution execution = result.get();
        return LatestExecutionDTO.builder()
                .id(execution.getId())
                .taskName(execution.getTaskName())
                .triggerType(execution.getTriggerType())
                .status(execution.getStatus())
                .startedAt(execution.getStartedAt())
                .finishedAt(execution.getFinishedAt())
                .recordsRead(execution.getRecordsRead())
                .recordsInserted(execution.getRecordsInserted())
                .errorMessage(execution.getErrorMessage())
                .build();
    }


    public ExecutionResponseDTO executeTask(ExecuteRequestDTO request) {

        SourceConfiguration configuration = sourceConfigurationRepository.findById(request.getSourceConfigurationId())
                .orElseThrow(() -> new EntityNotFoundException("No existe la configuración")
                );

        if (!"true".equalsIgnoreCase(String.valueOf(configuration.getEnabled()))) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "La configuración está deshabilitada"
            );
        }

        SheetType sheetType = resolveSheetType(configuration.getName());

        TaskExecution execution = new TaskExecution();

        execution.setSourceConfiguration(configuration);
        execution.setTaskName(configuration.getName());
        execution.setTriggerType(String.valueOf(request.getTriggerType()));
        execution.setStatus("RUNNING");
        execution.setStartedAt(OffsetDateTime.now());

        execution.setRecordsRead(0L);
        execution.setRecordsInserted(0L);

        TaskExecution savedExecution = taskExecutionRepository.save(execution);

        asyncProcessorService.processTask(
                savedExecution,
                configuration,
                sheetType
        );

        return new ExecutionResponseDTO(savedExecution.getId(), "RUNNING", "La ejecución fue aceptada"
        );
    }

    private SheetType resolveSheetType(String taskName) {

        if (taskName == null || taskName.isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre de la tarea no puede estar vacío"
            );
        }

        /*
         Faltan los demas types
         */
        return switch (taskName.trim().toUpperCase()) {
            case "GEOLOGIA" -> SheetType.GEOLOGY;
            case "GRADOS DE LABORATORIO" -> SheetType.LABORATORY;
            case "PLANTA" -> SheetType.PLANT;
            case "PRODUCCION", "PRODUCCIÓN" -> SheetType.PRODUCTION;
            case "PLANT_CONSUMIBLES" -> SheetType.PLANT_CONSUMIBLES;
            case "PLANT_BUDGET" -> SheetType.PLANT_BUDGET;
            case "RRHH" -> SheetType.RRHH;
            case "MTTO" -> SheetType.MTTO;
            case "PRODUCTION" -> SheetType.PRODUCTION;
            case "TEST" -> SheetType.TEST;
            case "SECURITY","SEGURIDAD" -> SheetType.SECURITY;
            // case "DEVELOPMENT" -> SheetType.DEVELOPMENT;
            case "DESARROLLO", "DEVELOPMENT" -> SheetType.DEVELOPMENT;
            case "LABORATORIO", "LABORATORY" -> SheetType.LABORATORY;
            case "LABORATORY_GRADES" -> SheetType.LABORATORY_GRADES;
            case "LABORATORY_QC" -> SheetType.LABORATORY_QC;
            case "GEOLOGY_DRILLING" -> SheetType.GEOLOGY_DRILLING;
            case "GEOLOGY_GRADE" -> SheetType.GEOLOGY_GRADE;
            case "GEOLOGY_REPORT" -> SheetType.GEOLOGY_REPORT;
            case "DIESEL_REPORT" -> SheetType.DIESEL_REPORT;
            case "TOPOGRAPHY" -> SheetType.TOPOGRAPHY;
            case "EXPLOSIVES" -> SheetType.EXPLOSIVES;
            case "ENTRADA_ACERO" -> SheetType.ENTRADA_ACERO;
            case "SALIDA_ACERO" -> SheetType.SALIDA_ACERO;
            case "TCD_CMD_ACTUAL" -> SheetType.TCD_CMD_ACTUAL;
            case "PLAN_PROD" -> SheetType.PLAN_PROD;
            case "PLAN_DEV" -> SheetType.PLAN_DEV;
            case "TCD_CMD_BUDGET" -> SheetType.TCD_CMD_BUDGET;

            default -> throw new IllegalArgumentException(
                    "No existe un tipo de tarea para: " + taskName
            );
        };
    }


    public DashboardContaadoresDTO getDashboardContadores() {
        Map<String, Object> contadores = taskExecutionRepository.getDashboardContadores();
        return DashboardContaadoresDTO.builder()
                .totalExecutions(getLongValue(contadores, "totalexecutions"))
                .successfulExecutions(getLongValue(contadores, "successfulexecutions"))
                .failedExecutions(getLongValue(contadores, "failedexecutions"))
                .runningExecutions(getLongValue(contadores, "runningexecutions"))
                .totalRecordsRead(getLongValue(contadores, "totalrecordsread"))
                .totalRecordsInserted(getLongValue(contadores, "totalrecordsinserted"))
                .totalRecordsUpdated(getLongValue(contadores, "totalrecordsupdated"))
                .build();
    }

}
