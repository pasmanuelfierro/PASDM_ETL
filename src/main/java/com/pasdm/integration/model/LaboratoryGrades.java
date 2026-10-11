package com.pasdm.integration.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Table(schema = "etl", name = "laboratory_grades")
@Getter
@Setter
@NoArgsConstructor
public class LaboratoryGrades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    @Column(name = "turno")
    private Integer turno;

    // BANDA
    @Column(name = "ban_au", precision = 20, scale = 4)
    private BigDecimal banAu;

    @Column(name = "ban_ag", precision = 20, scale = 4)
    private BigDecimal banAg;

    @Column(name = "ban_pb", precision = 20, scale = 4)
    private BigDecimal banPb;

    @Column(name = "ban_zn", precision = 20, scale = 4)
    private BigDecimal banZn;

    @Column(name = "ban_humedad", precision = 20, scale = 4)
    private BigDecimal banHumedad;

    // FINOS
    @Column(name = "finos_ag", precision = 20, scale = 4)
    private BigDecimal finosAg;

    @Column(name = "finos_pb", precision = 20, scale = 4)
    private BigDecimal finosPb;

    @Column(name = "finos_zn", precision = 20, scale = 4)
    private BigDecimal finosZn;

    @Column(name = "row_hash", length = 64, nullable = false, unique = true)
    private String rowHash;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", updatable = true)
    private LocalDateTime updatedAt;


}
