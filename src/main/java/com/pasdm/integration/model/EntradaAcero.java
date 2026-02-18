package com.pasdm.integration.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(schema = "etl", name = "entrada_acero",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_entrada_acero_row_hash", columnNames = "row_hash")
        },
        indexes = {
                @Index(name = "ux_entrada_acero_row_hash", columnList = "row_hash"),
        })
@Getter
@Setter
@NoArgsConstructor
public class EntradaAcero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* =========================
       IDENTIFICACIÓN
       ========================= */

    @Column(name = "fecha" )
    private LocalDate fecha;

    @Column(name = "recibido_por", length = 150)
    private String recibidoPor;

    @Column(name = "codigo", length = 100)
    private String codigo;

    @Column(name = "acero", length = 100)
    private String acero;

    @Column(name = "nombre_acero", length = 150)
    private String nombreAcero;   // Columna1

    @Column(name = "cantidad")
    private Integer cantidad;

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
