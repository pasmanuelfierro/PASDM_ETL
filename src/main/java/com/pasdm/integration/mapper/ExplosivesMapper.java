package com.pasdm.integration.mapper;

import com.pasdm.integration.model.Explosives;
import com.pasdm.integration.util.ExcelValueParser;
import com.pasdm.integration.util.HashUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;

@Slf4j
@Component
public class ExplosivesMapper {
    /* =========================
   COLUMNAS EXCEL (BASE 0)
   ========================= */

    private static final int COL_FECHA = 0;
    private static final int COL_MINA = 1;
    private static final int COL_MDO_MINADO = 2;
    private static final int COL_OBRA = 3;
    private static final int COL_LUGAR = 4;
    private static final int COL_MINERAL = 5;
    private static final int COL_UTILIZADO_POR = 6;
    private static final int COL_MARCA = 7;
    private static final int COL_TURNO = 8;
    private static final int COL_GRUPO = 9;
    private static final int COL_SUPERVISOR = 10;

    private static final int COL_COSTO = 11;
    private static final int COL_EQUIPO = 12;

    /* =========================
       EXPLOSIVOS
       ========================= */
    private static final int COL_E_ANFO_UG = 13;
    private static final int COL_D_ANFO_UG = 14;
    private static final int COL_E_ANFO_PREMIUM = 15;
    private static final int COL_D_ANFO_PREMIUM = 16;

    private static final int COL_E_1X8 = 17;
    private static final int COL_D_1X8 = 18;
    private static final int COL_E_114X16 = 19;
    private static final int COL_D_114X16 = 20;

    private static final int COL_E_T1 = 21;
    private static final int COL_D_T1 = 22;

    private static final int COL_E_112X39 = 23;
    private static final int COL_D_112X39 = 24;

    private static final int COL_E_2X16 = 25;
    private static final int COL_D_2X16 = 26;

    private static final int COL_E_CORDON = 27;
    private static final int COL_D_CORDON = 28;

    private static final int COL_E_BOOSTER = 29;
    private static final int COL_D_BOOSTER = 30;

    /* =========================
       SISTEMAS
       ========================= */
    private static final int COL_POWERSPLIT_E = 31;
    private static final int COL_POWERSPLIT_D = 32;

    private static final int COL_CABLE_HARNESS_E = 33;
    private static final int COL_CABLE_HARNESS_D = 34;

    private static final int COL_CORDON_SISMICO_E = 35;
    private static final int COL_CORDON_SISMICO_D = 36;

    private static final int COL_STINGER_20GR_E = 37;
    private static final int COL_STINGER_20GR_D = 38;

    private static final int COL_IKON_10FT_E = 39;
    private static final int COL_IKON_10FT_D = 40;

    private static final int COL_E_CANUELA = 41;
    private static final int COL_D_CANUELA = 42;
    private static final int COL_NONEL = 43;
    private static final int COL_FT_NONEL = 44;

    private static final int COL_N1_E = 45;
    private static final int COL_N1_D = 46;
    private static final int COL_N2_E = 47;
    private static final int COL_N2_D = 48;
    private static final int COL_N3_E = 49;
    private static final int COL_N3_D = 50;
    private static final int COL_N4_E = 51;
    private static final int COL_N4_D = 52;
    private static final int COL_N5_E = 53;
    private static final int COL_N5_D = 54;
    private static final int COL_N6_E = 55;
    private static final int COL_N6_D = 56;
    private static final int COL_N7_E = 57;
    private static final int COL_N7_D = 58;
    private static final int COL_N8_E = 59;
    private static final int COL_N8_D = 60;
    private static final int COL_N9_E = 61;
    private static final int COL_N9_D = 62;
    private static final int COL_N10_E = 63;
    private static final int COL_N10_D = 64;
    private static final int COL_N11_E = 65;
    private static final int COL_N11_D = 66;
    private static final int COL_N12_E = 67;
    private static final int COL_N12_D = 68;
    private static final int COL_N13_E = 69;
    private static final int COL_N13_D = 70;
    private static final int COL_N14_E = 71;
    private static final int COL_N14_D = 72;
    private static final int COL_N15_E = 73;
    private static final int COL_N15_D = 74;
    /* =========================
       FECHAS
       ========================= */
    private static final int COL_FECHA_OPERACION = 77;

    public Explosives mapEntity(Map<Integer, String> row) {
        try {
            LocalDate hoy = LocalDate.now();

            Explosives e = new Explosives();

            LocalDate fecha = ExcelValueParser.dateValidador(row.get(COL_FECHA));  // FECHA

            // if (fecha != null && (fecha.isBefore(hoy) || fecha.isEqual(hoy))) {
            if (fecha != null) {

               /* =========================
               IDENTIFICACIÓN
               ========================= */
                e.setFecha(ExcelValueParser.dateValidador(row.get(COL_FECHA)));
                e.setMina(ExcelValueParser.stringValidador(row.get(COL_MINA)));
                e.setMdoMinado(ExcelValueParser.stringValidador(row.get(COL_MDO_MINADO)));
                e.setObra(ExcelValueParser.stringValidador(row.get(COL_OBRA)));
                e.setLugar(ExcelValueParser.stringValidador(row.get(COL_LUGAR)));
                e.setMineral(ExcelValueParser.stringValidador(row.get(COL_MINERAL)));
                e.setUtilizadoPor(ExcelValueParser.stringValidador(row.get(COL_UTILIZADO_POR)));
                e.setMarca(ExcelValueParser.stringValidador(row.get(COL_MARCA)));
                e.setTurno(ExcelValueParser.stringValidador(row.get(COL_TURNO)));
                e.setGrupo(ExcelValueParser.stringValidador(row.get(COL_GRUPO)));
                e.setSupervisor(ExcelValueParser.stringValidador(row.get(COL_SUPERVISOR)));

                /* =========================
                   COSTOS Y EQUIPO
                   ========================= */
                e.setCosto(ExcelValueParser.stringValidador(row.get(COL_COSTO)));
                e.setEquipo(ExcelValueParser.stringValidador(row.get(COL_EQUIPO)));

                /* =========================
                   EXPLOSIVOS
                   ========================= */
                e.setEAnfoUg(ExcelValueParser.intValidador(row.get(COL_E_ANFO_UG)));
                e.setDAnfoUg(ExcelValueParser.intValidador(row.get(COL_D_ANFO_UG)));
                e.setEAnfoPremium(ExcelValueParser.intValidador(row.get(COL_E_ANFO_PREMIUM)));
                e.setDAnfoPremium(ExcelValueParser.intValidador(row.get(COL_D_ANFO_PREMIUM)));

                e.setE1x8(ExcelValueParser.intValidador(row.get(COL_E_1X8)));
                e.setD1x8(ExcelValueParser.intValidador(row.get(COL_D_1X8)));
                e.setE114x16(ExcelValueParser.intValidador(row.get(COL_E_114X16)));
                e.setD114x16(ExcelValueParser.intValidador(row.get(COL_D_114X16)));

                e.setET1(ExcelValueParser.intValidador(row.get(COL_E_T1)));
                e.setDT1(ExcelValueParser.intValidador(row.get(COL_D_T1)));

                e.setE112x39(ExcelValueParser.intValidador(row.get(COL_E_112X39)));
                e.setD112x39(ExcelValueParser.intValidador(row.get(COL_D_112X39)));

                e.setE2x16(ExcelValueParser.intValidador(row.get(COL_E_2X16)));
                e.setD2x16(ExcelValueParser.intValidador(row.get(COL_D_2X16)));

                e.setECordon(ExcelValueParser.intValidador(row.get(COL_E_CORDON)));
                e.setDCordon(ExcelValueParser.intValidador(row.get(COL_D_CORDON)));

                e.setEBooster(ExcelValueParser.intValidador(row.get(COL_E_BOOSTER)));
                e.setDBooster(ExcelValueParser.intValidador(row.get(COL_D_BOOSTER)));

                /* =========================
                   SISTEMAS
                   ========================= */
                e.setPowersplitE(ExcelValueParser.intValidador(row.get(COL_POWERSPLIT_E)));
                e.setPowersplitD(ExcelValueParser.intValidador(row.get(COL_POWERSPLIT_D)));

                e.setCableHarnessE(ExcelValueParser.intValidador(row.get(COL_CABLE_HARNESS_E)));
                e.setCableHarnessD(ExcelValueParser.intValidador(row.get(COL_CABLE_HARNESS_D)));

                e.setCordonSismicoE(ExcelValueParser.intValidador(row.get(COL_CORDON_SISMICO_E)));
                e.setCordonSismicoD(ExcelValueParser.intValidador(row.get(COL_CORDON_SISMICO_D)));

                e.setStinger20grE(ExcelValueParser.intValidador(row.get(COL_STINGER_20GR_E)));
                e.setStinger20grD(ExcelValueParser.intValidador(row.get(COL_STINGER_20GR_D)));

                e.setIkon10ftE(ExcelValueParser.intValidador(row.get(COL_IKON_10FT_E)));
                e.setIkon10ftD(ExcelValueParser.intValidador(row.get(COL_IKON_10FT_D)));
/* =========================
   CAÑUELA / NONEL
   ========================= */

                e.setECanuela(ExcelValueParser.intValidador(row.get(COL_E_CANUELA)));
                e.setDCanuela(ExcelValueParser.intValidador(row.get(COL_D_CANUELA)));
                e.setNonel(ExcelValueParser.stringValidador(row.get(COL_NONEL)));
                e.setFtNonel(ExcelValueParser.intValidador(row.get(COL_FT_NONEL)));

/* =========================
   RETARDOS N1 - N15
   ========================= */

                e.setN1E(ExcelValueParser.intValidador(row.get(COL_N1_E)));
                e.setN1D(ExcelValueParser.intValidador(row.get(COL_N1_D)));

                e.setN2E(ExcelValueParser.intValidador(row.get(COL_N2_E)));
                e.setN2D(ExcelValueParser.intValidador(row.get(COL_N2_D)));

                e.setN3E(ExcelValueParser.intValidador(row.get(COL_N3_E)));
                e.setN3D(ExcelValueParser.intValidador(row.get(COL_N3_D)));

                e.setN4E(ExcelValueParser.intValidador(row.get(COL_N4_E)));
                e.setN4D(ExcelValueParser.intValidador(row.get(COL_N4_D)));

                e.setN5E(ExcelValueParser.intValidador(row.get(COL_N5_E)));
                e.setN5D(ExcelValueParser.intValidador(row.get(COL_N5_D)));

                e.setN6E(ExcelValueParser.intValidador(row.get(COL_N6_E)));
                e.setN6D(ExcelValueParser.intValidador(row.get(COL_N6_D)));

                e.setN7E(ExcelValueParser.intValidador(row.get(COL_N7_E)));
                e.setN7D(ExcelValueParser.intValidador(row.get(COL_N7_D)));

                e.setN8E(ExcelValueParser.intValidador(row.get(COL_N8_E)));
                e.setN8D(ExcelValueParser.intValidador(row.get(COL_N8_D)));

                e.setN9E(ExcelValueParser.intValidador(row.get(COL_N9_E)));
                e.setN9D(ExcelValueParser.intValidador(row.get(COL_N9_D)));

                e.setN10E(ExcelValueParser.intValidador(row.get(COL_N10_E)));
                e.setN10D(ExcelValueParser.intValidador(row.get(COL_N10_D)));

                e.setN11E(ExcelValueParser.intValidador(row.get(COL_N11_E)));
                e.setN11D(ExcelValueParser.intValidador(row.get(COL_N11_D)));

                e.setN12E(ExcelValueParser.intValidador(row.get(COL_N12_E)));
                e.setN12D(ExcelValueParser.intValidador(row.get(COL_N12_D)));

                e.setN13E(ExcelValueParser.intValidador(row.get(COL_N13_E)));
                e.setN13D(ExcelValueParser.intValidador(row.get(COL_N13_D)));

                e.setN14E(ExcelValueParser.intValidador(row.get(COL_N14_E)));
                e.setN14D(ExcelValueParser.intValidador(row.get(COL_N14_D)));

                e.setN15E(ExcelValueParser.intValidador(row.get(COL_N15_E)));
                e.setN15D(ExcelValueParser.intValidador(row.get(COL_N15_D)));
                /* =========================
                   FECHAS
                   ========================= */
                e.setFechaOperacion(ExcelValueParser.dateValidadorDebug(row,row.get(COL_FECHA_OPERACION)));

                e.setRowHash(HashUtil.calculateRowHash(e.getFecha().toString(), e.getMina(), e.getObra(), e.getMdoMinado(), e.getLugar(), e.getSupervisor()));

                return e;
            } else {
                return null;
            }
        } catch (Exception ex) {
            log.error("Error procesando Excel OperacionExplosivos", ex);
            return null;
        }
    }
}

