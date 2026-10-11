package com.pasdm.integration.mapper;

import com.pasdm.integration.model.PlantActual;
import com.pasdm.integration.util.ExcelValueParser;
import com.pasdm.integration.util.HashUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Slf4j
@Component
public class PlantActualMapper {

    // =======================
    // IDENTIFICACIÓN
    // =======================
    private static final int COL_FECHA = 0;
    private static final int COL_TURNO = 1;
    private static final int COL_PESO_SECO_TON = 2;

    // =======================
    // CABEZA
    // =======================
    private static final int COL_CABEZA_AG_KGT = 3;
    private static final int COL_CABEZA_ZN_PCT = 4;
    private static final int COL_CABEZA_CU_PCT = 5;

    // =======================
    // CONC Pb
    // =======================
    private static final int COL_CONC_PB_AU_GT = 6;
    private static final int COL_CONC_PB_AG_KGT = 7;
    private static final int COL_CONC_PB_PB_PCT = 8;
    private static final int COL_CONC_PB_ZN_PCT = 9;
    private static final int COL_CONC_PB_CU_PCT = 10;

    // =======================
    // CONC Zn
    // =======================
    private static final int COL_CONC_ZN_AU_GT = 11;
    private static final int COL_CONC_ZN_AG_KGT = 12;
    private static final int COL_CONC_ZN_PB_PCT = 13;
    private static final int COL_CONC_ZN_ZN_PCT = 14;
    private static final int COL_CONC_ZN_CU_PCT = 15;

    // =======================
    // COLA
    // =======================
    private static final int COL_COLA_AU_GT = 16;
    private static final int COL_COLA_AG_KGT = 17;
    private static final int COL_COLA_PB_PCT = 18;
    private static final int COL_COLA_ZN_PCT = 19;
    private static final int COL_COLA_CU_PCT = 20;

    // =======================
    // TONELAJES
    // =======================
    private static final int COL_CONC_PB_TON = 22;
    private static final int COL_CONC_ZN_TON = 23;
    private static final int COL_COLA_TON = 24;

    // =======================
    // LEYES CALCULADAS - CABEZA
    // =======================
    private static final int COL_CAB_AU = 25;
    private static final int COL_CAB_AG = 26;
    private static final int COL_CAB_PB = 27;
    private static final int COL_CAB_ZN = 28;
    private static final int COL_CAB_CU = 29;

    // =======================
    // LEYES CALCULADAS - CONC Pb
    // =======================
    private static final int COL_CONC_PB_AU = 30;
    private static final int COL_CONC_PB_AG = 31;
    private static final int COL_CONC_PB_PB = 32;
    private static final int COL_CONC_PB_ZN = 33;
    private static final int COL_CONC_PB_CU = 34;

    // =======================
    // LEYES CALCULADAS - CONC Zn
    // =======================
    private static final int COL_CONC_ZN_AU = 35;
    private static final int COL_CONC_ZN_AG = 36;
    private static final int COL_CONC_ZN_PB = 37;
    private static final int COL_CONC_ZN_ZN = 38;
    private static final int COL_CONC_ZN_CU = 39;

    // =======================
    // LEYES CALCULADAS - COLA
    // =======================
    private static final int COL_COLA_AU = 40;
    private static final int COL_COLA_AG = 41;
    private static final int COL_COLA_PB = 42;
    private static final int COL_COLA_ZN = 43;
    private static final int COL_COLA_CU = 44;
    private static final int COL_SEQ = 45;

    /* =========================
       MAPEO
       ========================= */

    public PlantActual mapEntity(Map<Integer, String> row) {

        if (row.isEmpty()) return null;

        PlantActual p = new PlantActual();
        try {
            if (ExcelValueParser.dateValidador(row.get(COL_FECHA)) != null) {

                p.setFecha(ExcelValueParser.dateValidador(row.get(COL_FECHA)));
                p.setTurno(row.get(COL_TURNO));
                p.setPesoSecoTon(ExcelValueParser.decimalValidador(row.get(COL_PESO_SECO_TON)));

                p.setCabezaAgKgT(ExcelValueParser.decimalValidador(row.get(COL_CABEZA_AG_KGT)));
                p.setCabezaZnPct(ExcelValueParser.decimalValidador(row.get(COL_CABEZA_ZN_PCT)));
                p.setCabezaCuPct(ExcelValueParser.decimalValidador(row.get(COL_CABEZA_CU_PCT)));

                p.setConcPbAuGt(ExcelValueParser.decimalValidador(row.get(COL_CONC_PB_AU_GT)));
                p.setConcPbAgKgT(ExcelValueParser.decimalValidador(row.get(COL_CONC_PB_AG_KGT)));
                p.setConcPbPbPct(ExcelValueParser.decimalValidador(row.get(COL_CONC_PB_PB_PCT)));
                p.setConcPbZnPct(ExcelValueParser.decimalValidador(row.get(COL_CONC_PB_ZN_PCT)));
                p.setConcPbCuPct(ExcelValueParser.decimalValidador(row.get(COL_CONC_PB_CU_PCT)));

                p.setConcZnAuGt(ExcelValueParser.decimalValidador(row.get(COL_CONC_ZN_AU_GT)));
                p.setConcZnAgKgT(ExcelValueParser.decimalValidador(row.get(COL_CONC_ZN_AG_KGT)));
                p.setConcZnPbPct(ExcelValueParser.decimalValidador(row.get(COL_CONC_ZN_PB_PCT)));
                p.setConcZnZnPct(ExcelValueParser.decimalValidador(row.get(COL_CONC_ZN_ZN_PCT)));
                p.setConcZnCuPct(ExcelValueParser.decimalValidador(row.get(COL_CONC_ZN_CU_PCT)));

                p.setColaAuGt(ExcelValueParser.decimalValidador(row.get(COL_COLA_AU_GT)));
                p.setColaAgKgT(ExcelValueParser.decimalValidador(row.get(COL_COLA_AG_KGT)));
                p.setColaPbPct(ExcelValueParser.decimalValidador(row.get(COL_COLA_PB_PCT)));
                p.setColaZnPct(ExcelValueParser.decimalValidador(row.get(COL_COLA_ZN_PCT)));
                p.setColaCuPct(ExcelValueParser.decimalValidador(row.get(COL_COLA_CU_PCT)));

                p.setConcPbTon(ExcelValueParser.decimalValidador(row.get(COL_CONC_PB_TON)));
                p.setConcZnTon(ExcelValueParser.decimalValidador(row.get(COL_CONC_ZN_TON)));
                p.setColaTon(ExcelValueParser.decimalValidador(row.get(COL_COLA_TON)));

                p.setCabezaAu(ExcelValueParser.decimalValidador(row.get(COL_CAB_AU)));
                p.setCabezaAg(ExcelValueParser.decimalValidador(row.get(COL_CAB_AG)));
                p.setCabezaPb(ExcelValueParser.decimalValidador(row.get(COL_CAB_PB)));
                p.setCabezaZn(ExcelValueParser.decimalValidador(row.get(COL_CAB_ZN)));
                p.setCabezaCu(ExcelValueParser.decimalValidador(row.get(COL_CAB_CU)));

                p.setConcPbAu(ExcelValueParser.decimalValidador(row.get(COL_CONC_PB_AU)));
                p.setConcPbAg(ExcelValueParser.decimalValidador(row.get(COL_CONC_PB_AG)));
                p.setConcPbPb(ExcelValueParser.decimalValidador(row.get(COL_CONC_PB_PB)));
                p.setConcPbZn(ExcelValueParser.decimalValidador(row.get(COL_CONC_PB_ZN)));
                p.setConcPbCu(ExcelValueParser.decimalValidador(row.get(COL_CONC_PB_CU)));

                p.setConcZnAu(ExcelValueParser.decimalValidador(row.get(COL_CONC_ZN_AU)));
                p.setConcZnAg(ExcelValueParser.decimalValidador(row.get(COL_CONC_ZN_AG)));
                p.setConcZnPb(ExcelValueParser.decimalValidador(row.get(COL_CONC_ZN_PB)));
                p.setConcZnZn(ExcelValueParser.decimalValidador(row.get(COL_CONC_ZN_ZN)));
                p.setConcZnCu(ExcelValueParser.decimalValidador(row.get(COL_CONC_ZN_CU)));

                p.setColaAu(ExcelValueParser.decimalValidador(row.get(COL_COLA_AU)));
                p.setColaAg(ExcelValueParser.decimalValidador(row.get(COL_COLA_AG)));
                p.setColaPb(ExcelValueParser.decimalValidador(row.get(COL_COLA_PB)));
                p.setColaZn(ExcelValueParser.decimalValidador(row.get(COL_COLA_ZN)));
                p.setColaCu(ExcelValueParser.decimalValidador(row.get(COL_COLA_CU)));
                p.setColaCu(ExcelValueParser.decimalValidador(row.get(COL_COLA_CU)));
                BigDecimal seq = ExcelValueParser.decimalValidador(row.get(COL_SEQ));
              //  log.info("seq: {}", seq);
                p.setRowHash(HashUtil.calculateRowHash("", "", "", "", seq.toString(), "", "", ""));
               /* p.setRowHash(HashUtil.calculateRowHash(
                        p.getFecha().toString(),
                        p.getTurno(),
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""));*/
                return p;
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("Error procesando Excel PlantBudget", e);
            return null;
        }
    }
}
