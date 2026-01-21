package com.pasdm.etl.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "etl", name = "laboratory_plant")
public class LaboratoryPlant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Num_Dia", length = 150)
    private String numDia;

    @Column(name = "Turno")
    private Integer turno;

    // BANDA
    @Column(name = "Ban_Au")
    private Integer banAu;

    @Column(name = "Ban_Ag")
    private Integer banAg;

    @Column(name = "Ban_Pb")
    private Integer banPb;

    @Column(name = "Ban_Zn")
    private Integer banZn;

    @Column(name = "Ban_Humedad", precision = 20, scale = 10)
    private BigDecimal banHumedad;

    // FINOS
    @Column(name = "Finos_Ag")
    private Integer finosAg;

    @Column(name = "Finos_Pb")
    private Integer finosPb;

    @Column(name = "Finos_Zn")
    private Integer finosZn;

    @Column(name = "row_hash", length = 64, nullable = false, unique = true)
    private String rowHash;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", updatable = true)
    private LocalDateTime updatedAt;


}
