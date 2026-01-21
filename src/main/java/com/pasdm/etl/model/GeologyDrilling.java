package com.pasdm.etl.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(schema = "etl", name = "geology_drilling",
        uniqueConstraints = @UniqueConstraint(name = "uk_geology_drilling_row_hash", columnNames = "row_hash"))
@Getter
@Setter
@NoArgsConstructor
public class GeologyDrilling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "bno")
    private String bno;

    @Column(name = "empresa")
    private String empresa;

    @Column(name = "maquina")
    private String maquina;

    @Column(name = "fondo_dia_anterior_m", precision = 20, scale = 10)
    private BigDecimal fondoDiaAnteriorM;

    @Column(name = "fondo_actual_m", precision = 20, scale = 10)
    private BigDecimal fondoActualM;

    @Column(name = "avance_dia_m", precision = 20, scale = 10)
    private BigDecimal avanceDiaM;

    @Column(name = "status")
    private String status;

    @Column(name = "estacion")
    private String estacion;

    @Column(name = "jv_holes")
    private String jvHoles;

    @Column(name = "target")
    private String target;

    @Column(name = "target2")
    private String target2;

    @Column(name = "row_hash", length = 64, nullable = false, unique = true)
    private String rowHash;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", updatable = true)
    private LocalDateTime updatedAt;

}
