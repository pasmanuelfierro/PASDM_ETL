package com.pasdm.etl.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(schema = "etl", name = "development", uniqueConstraints = {
        @UniqueConstraint(name = "uk_dev_row_hash", columnNames = "row_hash")
})
@Getter
@Setter
@NoArgsConstructor
public class Development {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* =======================
       DATOS GENERALES
       ======================= */

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "grupo", length = 100)
    private String grupo;

    @Column(name = "prioridad")
    private Integer prioridad;

    @Column(name = "lote", length = 100)
    private String lote;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "mina", length = 100)
    private String mina;

    @Column(name = "zona", length = 100)
    private String zona;

    /* =======================
       COSTOS / RESPONSABLE
       ======================= */

    @Column(name = "costos", length = 50)
    private String costos;

    @Column(name = "elaborado_por", length = 150)
    private String elaboradoPor;

    @Column(name = "min_tep", length = 50)
    private String minTep;

    /* =======================
       UBICACIÓN / OBRA
       ======================= */

    @Column(name = "obra", length = 150)
    private String obra;

    @Column(name = "estructura", length = 150)
    private String estructura;

    @Column(name = "lugar", length = 150)
    private String lugar;

    /* =======================
       MÉTRICAS
       ======================= */

    @Column(name = "metros", precision = 20, scale = 10)
    private BigDecimal metros;

    @Column(name = "row_hash", length = 64, nullable = false, unique = true)
    private String rowHash;

    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;
}
