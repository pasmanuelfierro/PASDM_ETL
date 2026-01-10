package com.pasdm.etl.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "security", schema = "etl",
        indexes = {
                @Index(name = "idx_security_row_hash", columnList = "row_hash", unique = true)
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Security {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* =======================
       CAMPOS DE NEGOCIO
       ======================= */
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "tipo", length = 50)
    private String tipo;

    @Column(name = "no", precision = 20, scale = 10)
    private BigDecimal no;

    @Column(name = "comentario", length = 500)
    private String comentario;

    /* =======================
       CONTROL / ETL
       ======================= */
    @Column(name = "row_hash", length = 64, nullable = false, unique = true)
    private String rowHash;

    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;
}
