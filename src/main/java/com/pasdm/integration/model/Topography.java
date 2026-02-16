package com.pasdm.integration.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "topography", schema = "etl",
        indexes = {
                @Index(name = "idx_topography_row_hash", columnList = "row_hash", unique = true)
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Topography {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* =======================
       CAMPOS DE NEGOCIO
       ======================= */

    @Column(name = "lugar_d", length = 50)
    private String lugarD;

    @Column(name = "obra", length = 50)
    private String obra;

    @Column(name = "tipo_minado", length = 50)
    private String tipoMinado;

    @Column(name = "zona", length = 50)
    private String zona;

    @Column(name = "veta", length = 50)
    private String veta;

    @Column(name = "tipo_material", length = 50)
    private String tipoMaterial;

    @Column(name = "prioridad", precision = 20, scale = 10)
    private BigDecimal prioridad;

    @Column(name = "lote", length = 50)
    private String lote;

    @Column(name = "empresa", length = 50)
    private String empresa;

    @Column(name = "cuenta", length = 50)
    private String cuenta;

    @Column(name = "grupo", length = 50)
    private String grupo;

    @Column(name = "area", length = 50)
    private String area;

    @Column(name = "ancho_plan", precision = 20, scale = 10)
    private BigDecimal anchoPlan;

    @Column(name = "alto_plan", precision = 20, scale = 10)
    private BigDecimal altoPlan;

    @Column(name = "lad", precision = 20, scale = 10)
    private BigDecimal lad;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "avance", precision = 20, scale = 10)
    private BigDecimal avance;

    @Column(name = "disp", precision = 20, scale = 10)
    private BigDecimal disp;

    @Column(name = "ancho", precision = 20, scale = 10)
    private BigDecimal ancho;

    @Column(name = "alto", precision = 20, scale = 10)
    private BigDecimal alto;

    @Column(name = "ton", precision = 20, scale = 10)
    private BigDecimal ton;

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
