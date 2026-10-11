package com.pasdm.integration.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Data
@Table(name = "daily_cmd_actual",schema = "etl")
public class DailyCMDActual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    @Column(name = "row_hash", length = 64, nullable = false, unique = true)
    private String rowHash;

    // =======================
    // SAFETY
    // =======================
    @Column(name = "frecuency_index", precision = 10, scale = 4)
    private BigDecimal frecuencyIndex;

    @Column(name = "severity_index", precision = 10, scale = 4)
    private BigDecimal severityIndex;

    @Column(name = "lost_days", precision = 10, scale = 4)
    private BigDecimal lostDays;

    @Column(name = "exposure_hours", precision = 10, scale = 4)
    private BigDecimal exposureHours;

    @Column(name = "lt_hpif", precision = 10, scale = 4)
    private BigDecimal ltHpif;

    @Column(name = "hpi_con_lesion", precision = 10, scale = 4)
    private BigDecimal hpiConLesion;

    @Column(name = "hpi_sin_lesion", precision = 10, scale = 4)
    private BigDecimal hpiSinLesion;

    @Column(name = "accidente_trayecto", precision = 10, scale = 4)
    private BigDecimal accidenteTrayecto;

    @Column(name = "property_damage_incidents", precision = 10, scale = 4)
    private BigDecimal propertyDamageIncidents;

    @Column(name = "lost_time_accidents", precision = 10, scale = 4)
    private BigDecimal lostTimeAccidents;

    @Column(name = "tarjeta_roja", precision = 10, scale = 4)
    private BigDecimal tarjetaRoja;

    @Column(name = "medical_attention", precision = 10, scale = 4)
    private BigDecimal medicalAttention;

    @Column(name = "first_aid_accidents", precision = 10, scale = 4)
    private BigDecimal firstAidAccidents;

    @Column(name = "fatal_accident", precision = 10, scale = 4)
    private BigDecimal fatalAccident;

    // =======================
    // RH
    // =======================
    @Column(name = "daily_workers", precision = 10, scale = 4)
    private BigDecimal dailyWorkers;

    @Column(name = "total_workers", precision = 10, scale = 4)
    private BigDecimal totalWorkers;

    @Column(name = "total_contractors_operativos", precision = 10, scale = 4)
    private BigDecimal totalContractorsOperativos;

    @Column(name = "total_workers_pasmex", precision = 10, scale = 4)
    private BigDecimal totalWorkersPasmex;


    @Column(name = "hm_laboured", precision = 10, scale = 4)
    private BigDecimal hmLaboured;

    @Column(name = "hm_contractors", precision = 10, scale = 4)
    private BigDecimal hmContractors;

    @Column(name = "hm_training_sh", precision = 10, scale = 4)
    private BigDecimal hmTrainingSh;

    // =======================
    // PLANTA
    // =======================
    @Column(name = "flujo_patios", precision = 10, scale = 4)
    private BigDecimal flujoPatios;

    @Column(name = "flujo_entrada_merril", precision = 10, scale = 4)
    private BigDecimal flujoEntradaMerril;

    @Column(name = "au_gt", precision = 10, scale = 4)
    private BigDecimal auGt;

    @Column(name = "ag_gt", precision = 10, scale = 4)
    private BigDecimal agGt;

    @Column(name = "au_oz", precision = 10, scale = 4)
    private BigDecimal auOz;

    @Column(name = "ag_oz", precision = 10, scale = 4)
    private BigDecimal agOz;

    // =======================
    // Audit
    // =======================
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "created_by")
    private OffsetDateTime createdBy;

    @Column(name = "updated_by")
    private OffsetDateTime updatedBy;
}
