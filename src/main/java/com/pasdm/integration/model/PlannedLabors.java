package com.pasdm.integration.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(schema = "etl", name = "planned_labors")
@Data
public class PlannedLabors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    private String zona;
    private String obra;
    private String estructura;
    private String labor;

    @Column(name = "created_at",updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at" )
    private LocalDateTime updatedAt;

    @Column(name = "row_hash", length = 64, nullable = false, unique = true)
    private String rowHash;
}
