package com.pasdm.integration.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Entity
@Table(schema = "manager", name = "task_execution")
@Data
public class TaskExecution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "trigger_type")
    private String triggerType;

    @Column(name = "status")
    private String status;

    @Column(name = "started_at", nullable = true)
    private OffsetDateTime startedAt;

    @Column(name = "finished_at", nullable = true)
    private OffsetDateTime finishedAt;

    @Column(name = "records_read")
    private Long recordsRead;

    @Column(name = "records_inserted")
    private Long recordsInserted;

    @Column(name = "records_updated")
    private Long recordsUpdated;

    @Column(name = "records_skipped")
    private Long recordsSkipped;

    @Column(name = "records_failed")
    private Long recordsFailed;

    @Column(name = "error_message")
    private String errorMessage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_configuration_id", nullable = false)
    private SourceConfiguration sourceConfiguration;
}
