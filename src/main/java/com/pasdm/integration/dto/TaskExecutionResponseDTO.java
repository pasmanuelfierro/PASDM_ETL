package com.pasdm.integration.dto;

import com.pasdm.integration.model.TaskExecution;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class TaskExecutionResponseDTO {
    private Long id;
    private String taskName;
    private String triggerType;
    private String status;
    private OffsetDateTime finishedAt;
    private Long recordsRead;
    private Long recordsInserted;
    private Long recordsUpdated;
    private Long recordsSkipped;
    private Long recordsFailed;
    private String errorMessage;

    public TaskExecutionResponseDTO(TaskExecution taskExecution) {
        this.id = taskExecution.getId();
        this.taskName = taskExecution.getTaskName();
        this.triggerType = taskExecution.getTriggerType();
        this.status = taskExecution.getStatus();
        this.finishedAt = taskExecution.getFinishedAt();
        this.recordsRead = taskExecution.getRecordsRead();
        this.recordsInserted = taskExecution.getRecordsInserted();
        this.recordsUpdated = taskExecution.getRecordsUpdated();
        this.recordsSkipped = taskExecution.getRecordsSkipped();
        this.recordsFailed = taskExecution.getRecordsFailed();
        this.errorMessage = taskExecution.getErrorMessage();

    }
}
