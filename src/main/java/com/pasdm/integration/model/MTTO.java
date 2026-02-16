package com.pasdm.integration.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(schema = "etl", name = "mtto")
@NoArgsConstructor
public class MTTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    *
    *  BLOQUE 1 "DISPONIBILIDAD"
    *
    * */

    @Column(name = "fecha1", nullable = false)
    private LocalDate fecha1;

    @Column(name = "Jumbos_Tepetate_pct",precision = 20, scale = 10)
    private BigDecimal  jumbosTepetatePct;

    @Column(name = "Jumbo_Barrenacion_Larga_pct", precision = 20, scale = 10)
    private BigDecimal  jumboBarrenacionLargaPct;

    @Column(name = "Jumbos_Lineales_Silles_pct", precision = 20, scale = 10)
    private BigDecimal  jumbosLinealesSillesPct;

    @Column(name = "Jumbos_Mixtos_Silles_pct", precision = 20, scale = 10)
    private BigDecimal  jumbosMixtosSillesPct;

    @Column(name = "Jumbos_Acladores_Tepetate_pct", precision = 20, scale = 10)
    private BigDecimal  jumbosAcladoresTepetatePct;

    @Column(name = "Jumbos_Ancladores_Silles_pct", precision = 20, scale = 10)
    private BigDecimal  jumbosAncladoresSillesPct;

    @Column(name = "Scoops_LH-203_ControlRemoto_pct", precision = 20, scale = 10)
    private BigDecimal  scoopsLH203ControlRemotoPct;

    @Column(name = "Scoops_LH-203_Sin_ControlRemoto_pct", precision = 20, scale = 10)
    private BigDecimal  scoopsLH203SinControlRemotoPct;

    @Column(name = "Loaders-LH307_pct", precision = 20, scale = 10)
    private BigDecimal   loadersLH307Pct;

    @Column(name = "Scoops_St-1030_pct", precision = 20, scale = 10)
    private BigDecimal scoopsSt1030Pct;

    @Column(name = "Camiones(CBP)15Ton_pct", precision = 20, scale = 10)
    private BigDecimal  camionesCBP15tonPct;

    @Column(name = "Camiones(CBP)20Ton_pct", precision = 20, scale = 10)
    private BigDecimal  camionesCBP20tonPct;

    @Column(name = "Camiones(CBP)32Ton_pct", precision = 20, scale = 10)
    private BigDecimal  camionesCBP32tonPct;

    @Column(name = "Mixer_pct", precision = 20, scale = 10)
    private BigDecimal  mixerPct;

    @Column(name = "Lanzador_Alfa_pct", precision = 20, scale = 10)
    private BigDecimal  lanzadorAlfaPct;

    @Column(name = "Lanzador_Putzmeister_pct", precision = 20, scale = 10)
    private BigDecimal  lanzadorPutzmeisterPct;


    /*
    BLOQUE 2: HORAS DE MANTENIMIENTO POR FLOTA DE EQUIPOS
     */

    @Column(name = "Jumbos_Tepetate_Hrs", precision = 20, scale = 10)
    private BigDecimal  jumbosTepetateHrs;

    @Column(name = "Jumbo_Barrenacion_Larga_Hrs", precision = 20, scale = 10)
    private BigDecimal  jumboBarrenacionLarga_Hrs;

    @Column(name = "Jumbos_Lineales_Silles_Hrs", precision = 20, scale = 10)
    private BigDecimal  jumbosLinealesSilles_Hrs;

    @Column(name = "Jumbos_Mixtos_Silles_Hrs", precision = 20, scale = 10)
    private BigDecimal  jumbosMixtosSilles_Hrs;

    @Column(name = "Jumbos_Ancladores_Tepetate_Hrs", precision = 20, scale = 10)
    private BigDecimal  jumbosAncladoresTepetate_Hrs;

    @Column(name = "Jumbos_Ancladores_Silles_Hrs", precision = 20, scale = 10)
    private BigDecimal  jumbosAncladoresSilles_Hrs;

    @Column(name = "Scoops_LH-203_ControlRemoto_Hrs", precision = 20, scale = 10)
    private BigDecimal  scoopsLH203ControlRemoto_Hrs;

    @Column(name = "Scoops_LH-203_SinControlRemoto_Hrs", precision = 20, scale = 10)
    private BigDecimal  scoopsLH203SinControlRemoto_Hrs;

    @Column(name = "Scoops_LH-307_Hrs", precision = 20, scale = 10)
    private BigDecimal  scoopsLH307_Hrs;

    @Column(name = "Scoops_ST-1030_Hrs", precision = 20, scale = 10)
    private BigDecimal  scoopsST1030_Hrs;

    @Column(name = "Camiones(CBP)15Ton_Hrs", precision = 20, scale = 10)
    private BigDecimal  camionesCBP15ton_Hrs;

    @Column(name = "Camiones(CBP)20Ton_Hrs", precision = 20, scale = 10)
    private BigDecimal  camionesCBP20ton_Hrs;

    @Column(name ="Camiones(CBP)32Ton_Hrs", precision = 20, scale = 10)
    private BigDecimal  camionesCBP32ton_Hrs;

    @Column(name = "Mixer_Hrs", precision = 20, scale = 10)
    private BigDecimal  mixerHrs;

    @Column(name = "Lanzado_Alta_Hrs", precision = 20, scale = 10)
    private BigDecimal  lanzadoAlta_Hrs;

    @Column(name = "Mini_Mixer_Hrs", precision = 20, scale = 10)
    private BigDecimal  miniMixer_Hrs;

    @Column(name = "Lanzador_Putzmeister_Hrs", precision = 20, scale = 10)
    private BigDecimal  lanzadorPutzmeister_Hrs;

    /*
    BLOQUE 3: MTTB
     */

    @Column(name = "Jumbo_Tepetate_Hrs1", precision = 20, scale = 10)
    private BigDecimal  jumboTepetateHrs1;

    @Column(name = "Jumbo_Barrenacion_Larga_Hrs1", precision = 20, scale = 10)
    private BigDecimal  jumboBarrenacionLarga_Hrs1;

    @Column(name = "Jumbos_Lineales_Silles_Hrs_1", precision = 20, scale = 10)
    private BigDecimal  jumbosLinealesSilles_Hrs1;

    @Column(name = "Jumbos_Mixtos_Silles_Hrs_1", precision = 20, scale = 10)
    private BigDecimal  jumbosMixtosSilles_Hrs1;

    @Column(name = "Jumbos_Ancladores_Tepetate1", precision = 20, scale = 10)
    private BigDecimal  jumbosAncladoresTepetate_Hrs1;

    @Column(name = "Jumbos_Ancladores_Silles1", precision = 20, scale = 10)
    private BigDecimal  jumbosAncladoresSilles1_Hrs1;

    @Column(name = "Scoops_LH-203_ControlRemoto_Hrs1", precision = 20, scale = 10)
    private BigDecimal  scoopsLH203ControlRemoto_Hrs1;

    @Column(name = "Scoops_LH-203_SinControlRemoto_Hrs1", precision = 20, scale = 10)
    private BigDecimal  scoopsLH203SinControlRemoto_Hrs1;

    @Column(name = "Scoops_LH-307_Hrs1", precision = 20, scale = 10)
    private BigDecimal  scoopsLH307_Hrs1;

    @Column(name = "Camiones(CBP)15Ton_Hrs1", precision = 20, scale = 10)
    private BigDecimal  camionesCBP15ton_Hrs1;

    @Column(name = "Camiones(CBP)20Ton_Hrs1", precision = 20, scale = 10)
    private BigDecimal  camionesCBP20ton_Hrs1;

    @Column(name ="Camiones(CBP)32Ton_Hrs1", precision = 20, scale = 10)
    private BigDecimal  camionesCBP32ton_Hrs1;

    @Column(name = "MixerHrs_1", precision = 20, scale = 10)
    private BigDecimal  mixerHrs1;

    @Column(name = "LanzadorAlfaHrs_1", precision = 20, scale = 10)
    private BigDecimal  lanzadorAlfaHrs1;

    @Column(name = "MiniMixerHrs_1", precision = 20, scale = 10)
    private BigDecimal  miniMixerHrs1;

    @Column(name = "LanzadorPutzmeisterHrs_1", precision = 20, scale = 10)
    private  BigDecimal  lanzadorPutzmeisterHrs1;

    /*
    BLOQUE 4: NUMERO DE EVENTOS POR FALLAS DE EQUIPOS
     */

    @Column(name = "Jumbo_Tepetate2")
    private Integer  jumboTepetate2;

    @Column(name = "Jumbo_Barrenacion_Larga2")
    private Integer  jumboBarrenacionLarga2;

    @Column(name = "Jumbos_Lineales_Silles2")
    private Integer  jumbosLinealesSilles2;

    @Column(name = "Jumbos_Mixtos_Silles2")
    private Integer  jumbosMixtosSilles2;

    @Column(name = "Jumbos_Ancladores_TepetateHr2")
    private Integer  jumbosAncladoresTepetateHr2;

    @Column(name = "Jumbos_Ancladores_Silles2")
    private Integer  jumbosAncladoresSilles2;

    @Column(name = "Scoops_LH-203_ControlRemoto2")
    private Integer  scoopsLH203ControlRemoto2;

    @Column(name = "Scoops_LH-203_SinControlRemoto2")
    private Integer  scoopsLH203SinControlRemoto2;

    @Column(name = "Scoops_LH-307_2")
    private Integer  scoopsLH307_2;

    @Column(name = "Scoops_ST-1030_2")
    private Integer scoopsST1030_2;

    @Column(name = "Camiones(CBP)15Ton_2")
    private Integer  camionesCBP15ton_2;

    @Column(name = "Camiones(CBP)20Ton_2")
    private Integer  camionesCBP20ton_2;

    @Column(name ="Camiones(CBP)32Ton_2")
    private Integer  camionesCBP32ton_2;

    @Column(name = "Mixer2")
    private Integer  mixer2;

    @Column(name = "LanzadorAlfa2")
    private Integer  lanzadorAlfa2;

    @Column(name = "Mini_Mixer2")
    private Integer  miniMixer2;

    @Column(name = "Lanzador_Putzmeister2")
    private Integer  lanzadorPutzmeister2;

    /*
    BLOQUE 5: ENERGIA
     */

    @Column(name = "Consumo_EnergiaMina_Kw-hr", precision = 20, scale = 10)
    private BigDecimal  consumo_EnergiaMina_Kw_hr;

    /*
    BLOQUE 6: BOMBEO
     */

    @Column(name = "op_bombas_nivel315Hrs")
    private Integer  op_bombas_nivel315Hrs;

    @Column(name = "op_bombas_nivel618Hrs")
    private Integer  op_bombas_nivel618Hrs;

    @Column(name = "vol_agua_extraida_m3")
    private Integer  vol_agua_extraida_m3;

    @Column(name = "vol_agua_enviado_nivel_315m3")
    private Integer  vol_agua_enviado_nivel_315m3;

    //

    @Column(name = "fecha2", nullable = false)
    private LocalDate fecha2;

    /*
    BLOQUE 7: VERIFICACIONES CRM Mantenimiento MECANICO
     */

    @Column(name = "fecha3", nullable = false)
    private LocalDate  fecha3;

    @Column(name = "VerificacionSupervisor1")
    private Integer  verificacionSupervisor1;

    @Column(name = "VerificacionOperativo1")
    private Integer  verificacionOperativo1;

    /*
    BLOQUE 8: VERIFICACIONES CRM MANTENIMIENTO ELECTRICO
     */

    @Column(name = "fecha4", nullable = false)
    private LocalDate fecha4;

    @Column(name = "VerificacionSupervisor2")
    private Integer  verificacionSupervisor2;

    @Column(name = "VerificacionOperativo2")
    private Integer  verificacionOperativo2;

}
