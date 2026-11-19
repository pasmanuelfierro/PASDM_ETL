package com.pasdm.integration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LatestExecutionDTO {
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
