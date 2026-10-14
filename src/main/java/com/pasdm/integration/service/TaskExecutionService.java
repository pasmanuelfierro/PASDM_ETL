package com.pasdm.integration.service;

import com.pasdm.integration.dto.TaskExecutionRequestDTO;
import com.pasdm.integration.model.SourceConfiguration;
import com.pasdm.integration.model.TaskExecution;
import com.pasdm.integration.repository.TaskExecutionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class TaskExecutionService {

    private final TaskExecutionRepository taskExecutionRepository;

    public TaskExecutionService(TaskExecutionRepository taskExecutionRepository) {
        this.taskExecutionRepository = taskExecutionRepository;
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

}
