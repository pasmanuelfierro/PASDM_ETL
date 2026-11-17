package com.pasdm.integration.dto;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Builder
@Data
public class ExecutionDetailDTO {
    private Long id;
    private String taskName;
    private String triggerType;
    private String status;
    private OffsetDateTime startedAt;
    private OffsetDateTime finishedAt;
    private Long recordsRead;
    private Long recordsInserted;
    private String errorMessage;
}
