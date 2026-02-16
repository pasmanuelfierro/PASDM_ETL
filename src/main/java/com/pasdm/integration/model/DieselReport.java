package com.pasdm.integration.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(schema = "etl", name = "diesel_report", uniqueConstraints = {
        @UniqueConstraint(name = "uk_diesel_report_row_hash", columnNames = "row_hash")
})
@Getter
@Setter
@NoArgsConstructor
public class DieselReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "estacion", length = 100)
    private String estacion;                 // 0

    @Column(name = "flota", length = 100)
    private String flota;                    // 1

    @Column(name = "area", length = 100)
    private String area;                     // 2

    @Column(name = "modelo", length = 100)
    private String modelo;                   // 3

    @Column(name = "dispositivo", length = 100)
    private String dispositivo;              // 4

    @Column(name = "cantidad")
    private Integer cantidad;                // 5

    @Column(name = "producto", length = 100)
    private String producto;                 // 6

    @Column(name = "fecha")
    private LocalDate fecha;                 // 7

    @Column(name = "interior_superficie", length = 50)
    private String interiorSuperficie;       // 8

    @Column(name = "familia", length = 100)
    private String familia;                  // 9

    @Column(name = "colorada_contratista", length = 100)
    private String coloradaContratista;      // 10

    @Column(name = "mes", length = 20)
    private String mes;                      // 11

    @Column(name = "row_hash", length = 64, nullable = false)
    private String rowHash;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
