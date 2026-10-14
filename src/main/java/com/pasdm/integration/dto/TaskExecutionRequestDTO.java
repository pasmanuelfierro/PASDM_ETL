package com.pasdm.integration.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data

public class TaskExecutionRequestDTO {
    private Long id;
    private String taskName;
    private String triggerType;
    private String status;
    private OffsetDateTime finishedAt;
    private String recordsRead;
    private String recordsInserted;
    private String recordsUpdated;
    private String recordsSkipped;
    private String recordsFailed;
    private String errorMessage;
}
