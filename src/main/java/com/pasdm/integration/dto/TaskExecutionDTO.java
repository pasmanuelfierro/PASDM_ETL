package com.pasdm.integration.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data

public class TaskExecutionDTO {
    private Long id;
    private String taskName;
    private String status;
    private String triggerType;
    private OffsetDateTime startedAt;
    private OffsetDateTime finishedAt;
    private Long recordsRead;
    private Long recordsInserted;
    private Long recordsUpdated;
    private SourceConfigurationDTO sourceConfigurationDTO;

    public TaskExecutionDTO(
            Long id,
            String taskName,
            String status,
            String triggerType,
            OffsetDateTime startedAt,
            OffsetDateTime finishedAt,
            Long recordsRead,
            Long recordsInserted,
            Long recordsUpdated,
            SourceConfigurationDTO sourceConfigurationDTO) {
        this.id = id;
        this.taskName = taskName;
        this.status = status;
        this.triggerType = triggerType;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.recordsRead = recordsRead;
        this.recordsInserted = recordsInserted;
        this.recordsUpdated = recordsUpdated;
        this.sourceConfigurationDTO = sourceConfigurationDTO;
    }



    public TaskExecutionDTO(String taskName,
                            String status,
                            String triggerType,
                            OffsetDateTime startedAt,
                            OffsetDateTime finishedAt,
                            Long recordsRead,
                            Long recordsInserted,
                            Long recordsUpdated) {
        this.taskName = taskName;
        this.status = status;
        this.triggerType = triggerType;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.recordsRead = recordsRead;
        this.recordsInserted = recordsInserted;
        this.recordsUpdated = recordsUpdated;

    }


}
