package com.pasdm.integration.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class SourceConfigurationRequestDTO {
    private Long id;
    private String name;
    private String host;
    private String directoryPath;
    private String filePattern;
    private String enabled;
    private OffsetDateTime updatedAt;
}
