package com.pasdm.etl.mapper;

import com.pasdm.etl.model.PlantBudget;
import com.pasdm.etl.util.ExcelValueParser;
import com.pasdm.etl.util.HashUtil;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PlantBudgetMapper {

    /* =========================
       ÍNDICES EXCEL (0-based)
       ========================= */

    private static final int COL_FECHA = 0;
    private static final int COL_ORE_TOTAL_T = 1;
    private static final int COL_TONNES_MILLED = 2;
    private static final int COL_AU_GT = 3;
    private static final int COL_AG_GT = 4;
    private static final int COL_PB_PCT = 5;
    private static final int COL_ZN_PCT = 6;

    private static final int COL_REC_AU_PCT = 7;
    private static final int COL_REC_AG_PCT = 8;
    private static final int COL_REC_PB_PCT = 9;
    private static final int COL_REC_ZN_PCT = 10;

    private static final int COL_AU_OZ = 11;
    private static final int COL_AG_OZ = 12;
    private static final int COL_PB_T = 13;
    private static final int COL_ZN_T = 14;

    private static final int COL_PROD_KG_AG = 15;
    private static final int COL_PROD_GRS_AU = 16;
    private static final int COL_PROD_TONS_PB = 17;
    private static final int COL_PROD_TONS_ZN = 18;

    private static final int COL_CONC_PB_TONS_PB = 19;
    private static final int COL_CONC_PB_AG_KG_TON = 20;
    private static final int COL_CONC_PB_AU_GRS_TON = 21;
    private static final int COL_CONC_PB_PB_PCT = 22;

    private static final int COL_CONC_ZN_TONS_ZN = 23;
    private static final int COL_CONC_ZN_AG_KG_TON = 24;
    private static final int COL_CONC_ZN_ZN_PCT = 25;

    private static final int COL_LEY_CONC_AU = 26;
    private static final int COL_LEY_CONC_AG_PB = 27;
    private static final int COL_LEY_CONC_AG_ZN = 28;
    private static final int COL_LEY_CONC_AG = 29;
    private static final int COL_LEY_CONC_PB = 30;
    private static final int COL_LEY_CONC_ZN = 31;

    /* =========================
       MAPEO
       ========================= */

    public PlantBudget mapEntity(Map<Integer, String> row) {
        try {
            if (ExcelValueParser.dateValidador(row.get(COL_FECHA)) != null) {

                PlantBudget e = new PlantBudget();

                e.setFecha(ExcelValueParser.dateValidador(row.get(COL_FECHA)));

                e.setOreTotalT(ExcelValueParser.decimalValidador(row.get(COL_ORE_TOTAL_T)));
                e.setTonnesMilled(ExcelValueParser.decimalValidador(row.get(COL_TONNES_MILLED)));
                e.setAuGt(ExcelValueParser.decimalValidador(row.get(COL_AU_GT)));
                e.setAgGt(ExcelValueParser.decimalValidador(row.get(COL_AG_GT)));
                e.setPbPct(ExcelValueParser.decimalValidador(row.get(COL_PB_PCT)));
                e.setZnPct(ExcelValueParser.decimalValidador(row.get(COL_ZN_PCT)));

                e.setRecAuPct(ExcelValueParser.decimalValidador(row.get(COL_REC_AU_PCT)));
                e.setRecAgPct(ExcelValueParser.decimalValidador(row.get(COL_REC_AG_PCT)));
                e.setRecPbPct(ExcelValueParser.decimalValidador(row.get(COL_REC_PB_PCT)));
                e.setRecZnPct(ExcelValueParser.decimalValidador(row.get(COL_REC_ZN_PCT)));

                e.setAuOz(ExcelValueParser.decimalValidador(row.get(COL_AU_OZ)));
                e.setAgOz(ExcelValueParser.decimalValidador(row.get(COL_AG_OZ)));
                e.setPbT(ExcelValueParser.decimalValidador(row.get(COL_PB_T)));
                e.setZnT(ExcelValueParser.decimalValidador(row.get(COL_ZN_T)));

                e.setProdKgAg(ExcelValueParser.decimalValidador(row.get(COL_PROD_KG_AG)));
                e.setProdGrsAu(ExcelValueParser.decimalValidador(row.get(COL_PROD_GRS_AU)));
                e.setProdTonsPb(ExcelValueParser.decimalValidador(row.get(COL_PROD_TONS_PB)));
                e.setProdTonsZn(ExcelValueParser.decimalValidador(row.get(COL_PROD_TONS_ZN)));

                e.setConcPbTonsPb(ExcelValueParser.decimalValidador(row.get(COL_CONC_PB_TONS_PB)));
                e.setConcPbAgKgTon(ExcelValueParser.decimalValidador(row.get(COL_CONC_PB_AG_KG_TON)));
                e.setConcPbAuGrsTon(ExcelValueParser.decimalValidador(row.get(COL_CONC_PB_AU_GRS_TON)));
                e.setConcPbPbPct(ExcelValueParser.decimalValidador(row.get(COL_CONC_PB_PB_PCT)));

                e.setConcZnTonsZn(ExcelValueParser.decimalValidador(row.get(COL_CONC_ZN_TONS_ZN)));
                e.setConcZnAgKgTon(ExcelValueParser.decimalValidador(row.get(COL_CONC_ZN_AG_KG_TON)));
                e.setConcZnZnPct(ExcelValueParser.decimalValidador(row.get(COL_CONC_ZN_ZN_PCT)));

                e.setLeyConcAu(ExcelValueParser.decimalValidador(row.get(COL_LEY_CONC_AU)));
                e.setLeyConcAgPb(ExcelValueParser.decimalValidador(row.get(COL_LEY_CONC_AG_PB)));
                e.setLeyConcAgZn(ExcelValueParser.decimalValidador(row.get(COL_LEY_CONC_AG_ZN)));
                e.setLeyConcAg(ExcelValueParser.decimalValidador(row.get(COL_LEY_CONC_AG)));
                e.setLeyConcPb(ExcelValueParser.decimalValidador(row.get(COL_LEY_CONC_PB)));
                e.setLeyConcZn(ExcelValueParser.decimalValidador(row.get(COL_LEY_CONC_ZN)));

                e.setRowHash(HashUtil.calculateRowHash(e.getFecha().toString(), e.getOreTotalT().toString(), e.getTonnesMilled().toString(), e.getProdKgAg().toString()));

                return e;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
