package com.pasdm.integration.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(schema = "etl", name = "plant_consumibles", uniqueConstraints = {
        @UniqueConstraint(name = "uk_plant_consumibles_row_hash", columnNames = "row_hash")
})
@Getter
@Setter
@NoArgsConstructor
public class PlantConsumibles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "turno", length = 30)
    private String turno;

    @Column(name = "cal", precision = 10, scale = 2)
    private BigDecimal cal;

    @Column(name = "deprezinc", precision = 10, scale = 2)
    private BigDecimal deprezinc;

    @Column(name = "sulfato_cobre", precision = 10, scale = 2)
    private BigDecimal sulfatoCobre;

    @Column(name = "floculante", precision = 10, scale = 2)
    private BigDecimal floculante;

    @Column(name = "aerofina_3418", precision = 10, scale = 2)
    private BigDecimal aerofina3418;

    @Column(name = "espumante", precision = 10, scale = 2)
    private BigDecimal espumante;

    @Column(name = "ro_2", precision = 10, scale = 2)
    private BigDecimal ro2;

    @Column(name = "bisulfito_amonio", precision = 10, scale = 2)
    private BigDecimal bisulfitoAmonio;

    @Column(name = "bola_2_5", precision = 10, scale = 2)
    private BigDecimal bola25;

    @Column(name = "xantato", precision = 10, scale = 2)
    private BigDecimal xantato;

    @Column(name = "anti_incrustante", precision = 10, scale = 2)
    private BigDecimal antiIncrustante;

    @Column(name = "agua_fresca", precision = 10, scale = 2)
    private BigDecimal aguaFresca;

    @Column(name = "sol_ret", precision = 10, scale = 2)
    private BigDecimal solRet;

    @Column(name = "horas_operacion", precision = 10, scale = 2)
    private BigDecimal horasOperacion;

    @Column(name = "horas_disponibles", precision = 10, scale = 2)
    private BigDecimal horasDisponibles;

    @Column(name = "horas_trituradas", precision = 10, scale = 2)
    private BigDecimal horasTrituradas;

    @Column(name = "granulometria_pct", precision = 10, scale = 2)
    private BigDecimal granulometriaPct;

    /* =========================
       AUDITORÍA
       ========================= */

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    @Column(name = "row_hash", length = 64, nullable = false, unique = true)
    private String rowHash;
}