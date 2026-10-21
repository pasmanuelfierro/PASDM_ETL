package com.pasdm.integration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;

@Data

public class SourceConfigurationDTO {
    private Long id;
    private String name;
    private String host;
    private String directoryPath;
    private String filePattern;
    private String enabled;
    private OffsetDateTime updatedAt;
    private TaskExecutionDTO taskExecution;

    public SourceConfigurationDTO(Long id,
                                  String directoryPath,
                                  String name,
                                  String host,
                                  String filePattern,
                                  String enabled,
                                  OffsetDateTime updatedAt,
                                  TaskExecutionDTO taskDTO) {
        this.id = id;
        this.directoryPath = directoryPath;
        this.name = name;
        this.host = host;
        this.filePattern = filePattern;
        this.enabled = enabled;
        this.updatedAt = updatedAt;
        this.taskExecution = taskDTO;
    }


    public SourceConfigurationDTO(Long id,
                                  String name,
                                  String directoryPath) {
        this.id = id;
        this.directoryPath = directoryPath;
        this.name = name;
    }
}
