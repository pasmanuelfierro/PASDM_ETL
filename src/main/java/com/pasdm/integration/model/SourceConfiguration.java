package com.pasdm.integration.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Entity
@Table(schema = "manager", name = "source_configuration")
@Data
public class SourceConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "host")
    private String host;

    @Column(name = "directory_path")
    private String directoryPath;

    @Column(name = "file_pattern")
    private String filePattern;

    @Column(name = "enabled")
    private String enabled;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_execution_id")
    private TaskExecution taskExecutionId;

}
