package com.pasdm.integration.dto;

import com.pasdm.integration.model.SourceConfiguration;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class SourceConfigurationResponseDTO {
    private Long id;
    private String name;
    private String host;
    private String directoryPath;
    private String filePattern;
    private String enabled;
    private OffsetDateTime updatedAt;

    public SourceConfigurationResponseDTO(SourceConfiguration sourceConfiguration) {
        this.id = sourceConfiguration.getId();
        this.name = sourceConfiguration.getName();
        this.host = sourceConfiguration.getHost();
        this.directoryPath = sourceConfiguration.getDirectoryPath();
        this.filePattern = sourceConfiguration.getFilePattern();
        this.enabled = sourceConfiguration.getEnabled();
        this.updatedAt = sourceConfiguration.getUpdatedAt();

    }
}
