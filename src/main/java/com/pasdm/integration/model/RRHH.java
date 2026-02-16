package com.pasdm.integration.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(schema = "etl", name = "RRHH", uniqueConstraints = {
        @UniqueConstraint(name = "uk_rrhh_fecha", columnNames = "fecha")
})
@Getter
@Setter
@NoArgsConstructor
public class RRHH {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "asistencia_personal")
    private Integer asistenciaPersonal;

    @Column(name = "vacaciones")
    private Integer vacaciones;

    @Column(name = "descanso")
    private Integer descanso;

    @Column(name = "falta")
    private Integer falta;

    @Column(name = "incapacidad")
    private Integer incapacidad;

    @Column(name = "permiso_con_goce")
    private Integer permisoConGoce;

    @Column(name = "permiso_sin_goce")
    private Integer permisoSinGoce;

    @Column(name = "asistencia_contratistas")
    private Integer asistenciaContratistas;

    @Column(name = "operacion_mina")
    private Integer operacionMina;

    @Column(name = "otras_areas")
    private Integer otrasAreas;

    @Column(name = "induccion")
    private Integer induccion;

    @Column(name = "fecha_verificacion")
    private LocalDate fechaVerificacion;

    @Column(name = "verificacion_supervisor")
    private Integer verificacionSupervisor;

    @Column(name = "verificacion_operativo")
    private Integer verificacionOperativo;

    @Column(name = "fecha_crm")
    private LocalDate fechaCrm;

    @Column(name = "comentario", columnDefinition = "TEXT")
    private String comentario;

    @Column(name = "imagenes")
    private String imagenes;
}
