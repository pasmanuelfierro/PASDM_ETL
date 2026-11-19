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
    private Long recordsRead;
    private Long recordsInserted;
    private Long recordsUpdated;
    private Long recordsSkipped;
    private Long recordsFailed;
    private String errorMessage;
}
