package com.pasdm.integration.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(schema = "etl", name = "salida_acero",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_salida_acero_row_hash", columnNames = "row_hash")
        },
        indexes = {
                @Index(name = "ux_salida_acero_row_hash", columnList = "row_hash"),
        })
@Getter
@Setter
@NoArgsConstructor
public class SalidaAcero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* =========================
       IDENTIFICACIÓN
       ========================= */

    @Column(name = "fecha" )
    private LocalDate fecha;

    @Column(name = "turno", length = 20)
    private String turno;

    @Column(name = "grupo", length = 50)
    private String grupo;

    @Column(name = "codigo", length = 100)
    private String codigo;

    @Column(name = "acero", length = 100)
    private String acero;

    @Column(name = "cantidad")
    private Integer cantidad;

    /* =========================
       EQUIPO
       ========================= */

    @Column(name = "equipo_cod", length = 50)
    private String equipoCod;

    @Column(name = "equipo", length = 100)
    private String equipo;

    /* =========================
       OPERACIÓN
       ========================= */

    @Column(name = "nomina_operador", length = 50)
    private String nominaOperador;

    @Column(name = "operador", length = 150)
    private String operador;

    @Column(name = "nomina_supervisor", length = 50)
    private String nominaSupervisor;

    @Column(name = "supervisor", length = 150)
    private String supervisor;

    /* =========================
       CONTROL
       ========================= */

    @Column(name = "zona", length = 100)
    private String zona;

    @Column(name = "proveedor", length = 150)
    private String proveedor;

    @Column(name = "costo", precision = 14, scale = 2)
    private BigDecimal costo;

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
