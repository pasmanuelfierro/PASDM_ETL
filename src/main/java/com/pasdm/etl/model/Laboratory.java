package com.pasdm.etl.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(schema = "etl", name = "laboratory", uniqueConstraints = {
        @UniqueConstraint(name = "uk_laboratory_fecha_1", columnNames = "fecha_1")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Laboratory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* =========================
       FECHA
       ========================= */
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    /* =========================
       RECIBIDAS SULFUROS
       ========================= */
    @Column(name = "rec_sulf_muestras_mina")
    private Integer recSulfMuestrasMina;

    @Column(name = "rec_sulf_muestras_exploraciones")
    private Integer recSulfMuestrasExploraciones;

    @Column(name = "rec_sulf_muestras_planta")
    private Integer recSulfMuestrasPlanta;

    @Column(name = "rec_sulf_muestras_especiales")
    private Integer recSulfMuestrasEspeciales;

    @Column(name = "rec_sulf_muestras_metalurgia")
    private Integer recSulfMuestrasMetalurgia;

    @Column(name = "rec_sulf_muestras_comercializacion")
    private Integer recSulfMuestrasComercializacion;

    @Column(name = "rec_sulf_muestras_reensaye_qaqc_geologia")
    private Integer recSulfMuestrasReensayeQaQcGeologia;

    @Column(name = "rec_sulf_muestras_qaqc_laboratorio")
    private Integer recSulfMuestrasQaQcLaboratorio;

    /* =========================
       ANALIZADAS SULFUROS
       ========================= */
    @Column(name = "ana_sulf_muestras_mina")
    private Integer anaSulfMuestrasMina;

    @Column(name = "ana_sulf_muestras_exploraciones")
    private Integer anaSulfMuestrasExploraciones;

    @Column(name = "ana_sulf_muestras_planta")
    private Integer anaSulfMuestrasPlanta;

    @Column(name = "ana_sulf_muestras_especiales")
    private Integer anaSulfMuestrasEspeciales;

    @Column(name = "ana_sulf_muestras_metalurgia")
    private Integer anaSulfMuestrasMetalurgia;

    @Column(name = "ana_sulf_muestras_comercializacion")
    private Integer anaSulfMuestrasComercializacion;

    @Column(name = "ana_sulf_muestras_reensaye_qaqc_geologia")
    private Integer anaSulfMuestrasReensayeQaQcGeologia;

    @Column(name = "ana_sulf_muestras_qaqc_laboratorio")
    private Integer anaSulfMuestrasQaQcLaboratorio;

    /* =========================
       RECIBIDAS ÓXIDOS
       ========================= */
    @Column(name = "rec_oxi_muestras_mina")
    private Integer recOxiMuestrasMina;

    @Column(name = "rec_oxi_muestras_exploraciones")
    private Integer recOxiMuestrasExploraciones;

    @Column(name = "rec_oxi_muestras_planta")
    private Integer recOxiMuestrasPlanta;

    @Column(name = "rec_oxi_muestras_especiales")
    private Integer recOxiMuestrasEspeciales;

    @Column(name = "rec_oxi_muestras_metalurgia")
    private Integer recOxiMuestrasMetalurgia;

    @Column(name = "rec_oxi_muestras_comercializacion")
    private Integer recOxiMuestrasComercializacion;

    @Column(name = "rec_oxi_muestras_reensaye_qaqc_geologia")
    private Integer recOxiMuestrasReensayeQaQcGeologia;

    @Column(name = "rec_oxi_muestras_qaqc_laboratorio")
    private Integer recOxiMuestrasQaQcLaboratorio;

    /* =========================
       ANALIZADAS ÓXIDOS
       ========================= */
    @Column(name = "ana_oxi_muestras_mina")
    private Integer anaOxiMuestrasMina;

    @Column(name = "ana_oxi_muestras_exploraciones")
    private Integer anaOxiMuestrasExploraciones;

    @Column(name = "ana_oxi_muestras_planta")
    private Integer anaOxiMuestrasPlanta;

    @Column(name = "ana_oxi_muestras_especiales")
    private Integer anaOxiMuestrasEspeciales;

    @Column(name = "ana_oxi_muestras_metalurgia")
    private Integer anaOxiMuestrasMetalurgia;

    @Column(name = "ana_oxi_muestras_comercializacion")
    private Integer anaOxiMuestrasComercializacion;

    @Column(name = "ana_oxi_muestras_reensaye_qaqc_geologia")
    private Integer anaOxiMuestrasReensayeQaQcGeologia;

    @Column(name = "ana_oxi_muestras_qaqc_laboratorio")
    private Integer anaOxiMuestrasQaQcLaboratorio;

    /* =========================
       VERIFICACIONES
       ========================= */
    @Column(name = "verificacion_supervisor", length = 150)
    private String verificacionSupervisor;

    @Column(name = "verificacion_operativo", length = 150)
    private String verificacionOperativo;

    /* =========================
       AUDITORÍA
       ========================= */
    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;
}
