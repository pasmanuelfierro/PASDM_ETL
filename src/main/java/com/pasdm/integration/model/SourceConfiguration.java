package com.pasdm.integration.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private Boolean enabled;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

  //  @ManyToOne(fetch = FetchType.LAZY)
  //  @JoinColumn(name = "task_execution_id")
  //  private TaskExecution taskExecutionId;
    @OneToMany(mappedBy = "sourceConfiguration", fetch= FetchType.LAZY)
    private List<TaskExecution> taskExecutions = new ArrayList<>();

}
