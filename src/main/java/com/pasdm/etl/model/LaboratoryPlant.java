package com.pasdm.etl.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Table(schema = "etl", name = "laboratory_plant", uniqueConstraints = {
        @UniqueConstraint(name = "uk_laboratory_plant_budget_row_hash", columnNames = "row_hash")
})
@Getter
@Setter
@NoArgsConstructor
public class LaboratoryPlant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num_dia", length = 150)
    private String numDia;

    @Column(name = "turno")
    private Integer turno;

    // BANDA
    @Column(name = "ban_au")
    private Integer banAu;

    @Column(name = "ban_ag")
    private Integer banAg;

    @Column(name = "ban_pb")
    private Integer banPb;

    @Column(name = "ban_zn")
    private Integer banZn;

    @Column(name = "ban_humedad", precision = 20, scale = 10)
    private BigDecimal banHumedad;

    // FINOS
    @Column(name = "finos_ag")
    private Integer finosAg;

    @Column(name = "finos_pb")
    private Integer finosPb;

    @Column(name = "finos_zn")
    private Integer finosZn;

    @Column(name = "row_hash", length = 64, nullable = false, unique = true)
    private String rowHash;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", updatable = true)
    private LocalDateTime updatedAt;


}
