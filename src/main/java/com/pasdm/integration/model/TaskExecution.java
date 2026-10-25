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

    @Column(name = "started_at", nullable = false)
    private OffsetDateTime startedAt;

    @Column(name = "finished_at", nullable = false)
    private OffsetDateTime finishedAt;

    @Column(name = "records_read")
    private String recordsRead;

    @Column(name = "records_inserted")
    private String recordsInserted;

    @Column(name = "records_updated")
    private String recordsUpdated;

    @Column(name = "records_skipped")
    private String recordsSkipped;

    @Column(name = "records_failed")
    private String recordsFailed;

    @Column(name = "error_message")
    private String errorMessage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "source_configuration_id")
    private SourceConfiguration sourceConfiguration;
}
