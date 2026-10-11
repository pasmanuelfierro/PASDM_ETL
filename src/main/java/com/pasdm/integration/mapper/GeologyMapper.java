package com.pasdm.integration.mapper;

import com.pasdm.integration.model.Geology;
import com.pasdm.integration.util.ExcelValueParser;
import com.pasdm.integration.util.HashUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Map;

@Slf4j
@Component
public class GeologyMapper {
    // =======================
    // BLOQUE MINERAL SULFURO PARTE 1
    // =======================

    private static final int COL_FECHA_1 = 1;
    private static final int COL_SOBREDILUCION_REBAJES_PCT = 2;
    private static final int COL_SOBREDILUCION_BL_PCT = 3;
    private static final int COL_SOBREDILUCION_BREASTING_PCT = 4;
    private static final int COL_SOBREDILUCION_CORTE_VERTICAL_PCT = 5;
    private static final int COL_SOBREDILUCION_SILLES_PCT = 6;
    private static final int COL_NUMERO_SILLES_MINERAL = 7;
    private static final int COL_NUMERO_SILLES_TEPETATE = 8;
    private static final int COL_NUMERO_REBAJES_BL = 9;
    private static final int COL_NUMERO_REBAJES_BRT = 10;
    private static final int COL_AVANCE_SILLES_MINERAL = 11;
    private static final int COL_AVANCE_SILLES_TEPETATE = 12;
    private static final int COL_PERFORACION_ORE_CONTROL_MTS = 13;
    private static final int COL_PERFORACION_INFILL_MTS = 14;
    private static final int COL_PERFORACION_BROWNFIELD_MTS = 15;
    private static final int COL_LC_DIAMEC_ORE_CONTROL = 16;
    private static final int COL_LC_EXPLORER_PLUS_ORE_CONTROL = 17;
    private static final int COL_LC_D130_BROWNFIELD_INFILL = 18;
    private static final int COL_LC_1500_BROWNFIELD_INFILL = 19;
    private static final int COL_CONTRATISTA_BROWNFIELD_SUBTERRANEA = 20;
    private static final int COL_CONTRATISTA_BROWNFIELD_SUPERFICIE = 21;


    // =======================
    // BLOQUE MINERAL SULFURO PARTE 2
    // =======================

    private static final int COL_FECHA_2 = 23;
    private static final int COL_MINERAL_SULFUROS = 24;
    private static final int COL_LEY_AG = 25;
    private static final int COL_LEY_AU = 26;
    private static final int COL_LEY_PB = 27;
    private static final int COL_LEY_ZN = 28;


    // =======================
    // BLOQUE MINERAL SULFURO PARTE 3
    // =======================

    private static final int COL_FECHA_3 = 30;
    private static final int COL_MINERAL_EXTRAIDO_BL_TM = 31;
    private static final int COL_LEY_AG_GPT = 32;
    private static final int COL_LEY_AU_GPT = 33;
    private static final int COL_LEY_PB_PCT = 34;
    private static final int COL_LEY_ZN_PCT = 35;
    private static final int COL_MINERAL_EXTRAIDO_BREASTING_TM = 36;
    private static final int COL_LEY_AG_GPT_2 = 37;
    private static final int COL_LEY_AU_GPT_3 = 38;
    private static final int COL_LEY_PB_PCT_4 = 39;
    private static final int COL_LEY_ZN_PCT_5 = 40;
    private static final int COL_MINERAL_SILLES_TM = 41;
    private static final int COL_LEY_AG_GPT_7 = 42;
    private static final int COL_LEY_AU_GPT_8 = 43;
    private static final int COL_LEY_PB_PCT_9 = 44;
    private static final int COL_LEY_ZN_PCT_10 = 45;
    private static final int COL_MINERAL_REBAJES = 46;

    public Geology mapEntity(Map<Integer, String> row) {
        try {

            if (ExcelValueParser.dateValidador(row.get(COL_FECHA_1)) != null) {
                Geology geology = new Geology();

                geology.setFecha1(ExcelValueParser.dateValidador(row.get(COL_FECHA_1)));
                geology.setSobredilucionRebajesPct(ExcelValueParser.decimalValidador(row.get(COL_SOBREDILUCION_REBAJES_PCT)));
                geology.setSobredilucionBlPct(ExcelValueParser.decimalValidador(row.get(COL_SOBREDILUCION_BL_PCT)));
                geology.setSobredilucionBreastingPct(ExcelValueParser.decimalValidador(row.get(COL_SOBREDILUCION_BREASTING_PCT)));
                geology.setSobredilucionCorteVerticalPct(ExcelValueParser.decimalValidador(row.get(COL_SOBREDILUCION_CORTE_VERTICAL_PCT)));
                geology.setSobredilucionSillesPct(ExcelValueParser.decimalValidador(row.get(COL_SOBREDILUCION_SILLES_PCT)));
                geology.setNumeroSillesMineral(ExcelValueParser.decimalValidador(row.get(COL_NUMERO_SILLES_MINERAL)));
                geology.setNumeroSillesTepetate(ExcelValueParser.decimalValidador(row.get(COL_NUMERO_SILLES_TEPETATE)));
                geology.setNumeroRebajesBl(ExcelValueParser.decimalValidador(row.get(COL_NUMERO_REBAJES_BL)));
                geology.setNumeroRebajesBrt(ExcelValueParser.decimalValidador(row.get(COL_NUMERO_REBAJES_BRT)));
                geology.setAvanceSillesMineral(ExcelValueParser.decimalValidador(row.get(COL_AVANCE_SILLES_MINERAL)));
                geology.setAvanceSillesTepetate(ExcelValueParser.decimalValidador(row.get(COL_AVANCE_SILLES_TEPETATE)));
                geology.setPerforacionOreControlMts(ExcelValueParser.decimalValidador(row.get(COL_PERFORACION_ORE_CONTROL_MTS)));
                geology.setPerforacionInfillMts(ExcelValueParser.decimalValidador(row.get(COL_PERFORACION_INFILL_MTS)));
                geology.setPerforacionBrownfieldMts(ExcelValueParser.decimalValidador(row.get(COL_PERFORACION_BROWNFIELD_MTS)));
                geology.setLcMaquinaDiamecOreControl(ExcelValueParser.decimalValidador(row.get(COL_LC_DIAMEC_ORE_CONTROL)));
                geology.setLcMaquinaExplorerPlusOreControl(ExcelValueParser.decimalValidador(row.get(COL_LC_EXPLORER_PLUS_ORE_CONTROL)));
                geology.setLcMaquinaD130BrownfieldInfill(ExcelValueParser.decimalValidador(row.get(COL_LC_D130_BROWNFIELD_INFILL)));
                geology.setLcMaquina1500BrownfieldInfill(ExcelValueParser.decimalValidador(row.get(COL_LC_1500_BROWNFIELD_INFILL)));
                geology.setContratistaBrownfieldSubterranea(ExcelValueParser.decimalValidador(row.get(COL_CONTRATISTA_BROWNFIELD_SUBTERRANEA)));
                geology.setContratistaBrownfieldSuperficie(ExcelValueParser.decimalValidador(row.get(COL_CONTRATISTA_BROWNFIELD_SUPERFICIE)));

                geology.setMineralSulfuros(ExcelValueParser.decimalValidador(row.get(COL_MINERAL_SULFUROS)));
                geology.setLeyAg(ExcelValueParser.decimalValidador(row.get(COL_LEY_AG)));
                geology.setLeyAu(ExcelValueParser.decimalValidador(row.get(COL_LEY_AU)));
                geology.setLeyPb(ExcelValueParser.decimalValidador(row.get(COL_LEY_PB)));
                geology.setLeyZn(ExcelValueParser.decimalValidador(row.get(COL_LEY_ZN)));

                geology.setMineralExtraidoBlTm(ExcelValueParser.decimalValidador(row.get(COL_MINERAL_EXTRAIDO_BL_TM)));
                geology.setLeyAgGpt(ExcelValueParser.decimalValidador(row.get(COL_LEY_AG_GPT)));
                geology.setLeyAuGpt(ExcelValueParser.decimalValidador(row.get(COL_LEY_AU_GPT)));
                geology.setLeyPbPct(ExcelValueParser.decimalValidador(row.get(COL_LEY_PB_PCT)));
                geology.setLeyZnPct(ExcelValueParser.decimalValidador(row.get(COL_LEY_ZN_PCT)));
                geology.setMineralExtraidoBreastingTm(ExcelValueParser.decimalValidador(row.get(COL_MINERAL_EXTRAIDO_BREASTING_TM)));
                geology.setLeyAgGpt2(ExcelValueParser.decimalValidador(row.get(COL_LEY_AG_GPT_2)));
                geology.setLeyAuGpt3(ExcelValueParser.decimalValidador(row.get(COL_LEY_AU_GPT_3)));
                geology.setLeyPbPct4(ExcelValueParser.decimalValidador(row.get(COL_LEY_PB_PCT_4)));
                geology.setLeyZnPct5(ExcelValueParser.decimalValidador(row.get(COL_LEY_ZN_PCT_5)));
                geology.setMineralSillesTm(ExcelValueParser.decimalValidador(row.get(COL_MINERAL_SILLES_TM)));
                geology.setLeyAgGpt7(ExcelValueParser.decimalValidador(row.get(COL_LEY_AG_GPT_7)));
                geology.setLeyAuGpt8(ExcelValueParser.decimalValidador(row.get(COL_LEY_AU_GPT_8)));
                geology.setLeyPbPct9(ExcelValueParser.decimalValidador(row.get(COL_LEY_PB_PCT_9)));
                geology.setLeyZnPct10(ExcelValueParser.decimalValidador(row.get(COL_LEY_ZN_PCT_10)));
                geology.setMineralRebajes(ExcelValueParser.decimalValidador(row.get(COL_MINERAL_REBAJES)));

                geology.setRowHash(HashUtil.calculateRowHash(
                        geology.getFecha1().toString(),
                        "", "", "", "", "", "", ""
                ));
                geology.setCreatedAt(OffsetDateTime.now());
                geology.setUpdatedAt(OffsetDateTime.now());
                return geology;
            }
        } catch (Exception e) {
            log.error("Error procesando Excel geologia", e);
            return null;
        }
        return null;
    }

}