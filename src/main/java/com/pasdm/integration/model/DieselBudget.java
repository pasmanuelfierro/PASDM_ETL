package com.pasdm.integration.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(schema = "etl", name = "diesel_budget", uniqueConstraints = {
        @UniqueConstraint(name = "uk_diesel_budget_row_hash", columnNames = "row_hash")
})
@Getter
@Setter
@NoArgsConstructor
public class DieselBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* =========================
       IDENTIFICACIÓN
       ========================= */

    @Column(name = "equipo", length = 100, nullable = false)
    private String equipo;                     // COL_EQUIPO = 0

    @Column(name = "descripcion", length = 200)
    private String descripcion;                // 1

    @Column(name = "familia", length = 100)
    private String familia;                    // 2

    @Column(name = "lts_hra", precision = 20, scale = 10)
    private BigDecimal ltsHra;                 // 3

    /* =========================
       PERIODO
       ========================= */

    @Column(name = "mes", nullable = false)
    private Integer mes;

    @Column(name = "anio", nullable = false)
    private Integer anio;

    /* =========================
       AUDITORÍA
       ========================= */

    @Column(name = "row_hash", length = 64, nullable = false, unique = true)
    private String rowHash;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /* getters & setters */
}

