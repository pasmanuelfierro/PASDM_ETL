package com.pasdm.etl.mapper;

import com.pasdm.etl.model.Geology;
import com.pasdm.etl.util.ExcelValueParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;

@Slf4j
@Component
public class GeologyMapper {


    public Geology mapEntity(Map<Integer, String> row) {
        try {

            LocalDate localDate = LocalDate.now();
            Geology e = new Geology();

        /* =======================
           PRIMER BLOQUE
           ======================= */

            if (ExcelValueParser.dateValidador(row.get(1)) != null) {

                if (ExcelValueParser.dateValidador(row.get(1)).isBefore(localDate) || ExcelValueParser.dateValidador(row.get(1)).isEqual(localDate)) {

                    e.setFecha1(ExcelValueParser.dateValidador(row.get(1)));
                    e.setSobredilucionRebajesPct(ExcelValueParser.decimalValidador(row.get(2)));
                    e.setSobredilucionBlPct(ExcelValueParser.decimalValidador(row.get(3)));
                    e.setSobredilucionBreastingPct(ExcelValueParser.decimalValidador(row.get(4)));
                    e.setSobredilucionCorteVerticalPct(ExcelValueParser.decimalValidador(row.get(5)));
                    e.setSobredilucionSillesPct(ExcelValueParser.decimalValidador(row.get(6)));

                    e.setNumeroSillesMineral(ExcelValueParser.decimalValidador(row.get(7)));
                    e.setNumeroSillesTepetate(ExcelValueParser.decimalValidador(row.get(8)));
                    e.setNumeroRebajesBl(ExcelValueParser.decimalValidador(row.get(9)));
                    e.setNumeroRebajesBrt(ExcelValueParser.decimalValidador(row.get(10)));

                    e.setAvanceSillesMineral(ExcelValueParser.decimalValidador(row.get(11)));
                    e.setAvanceSillesTepetate(ExcelValueParser.decimalValidador(row.get(12)));

                    e.setPerforacionOreControlMts(ExcelValueParser.decimalValidador(row.get(13)));
                    e.setPerforacionInfillMts(ExcelValueParser.decimalValidador(row.get(14)));
                    e.setPerforacionBrownfieldMts(ExcelValueParser.decimalValidador(row.get(15)));

                    e.setLcMaquinaDiamecOreControl(ExcelValueParser.decimalValidador(row.get(16)));
                    e.setLcMaquinaExplorerPlusOreControl(ExcelValueParser.decimalValidador(row.get(17)));
                    e.setLcMaquinaD130BrownfieldInfill(ExcelValueParser.decimalValidador(row.get(18)));
                    e.setLcMaquina1500BrownfieldInfill(ExcelValueParser.decimalValidador(row.get(19)));

                    e.setContratistaBrownfieldSubterranea(ExcelValueParser.decimalValidador(row.get(20)));
                    e.setContratistaBrownfieldSuperficie(ExcelValueParser.decimalValidador(row.get(21)));

        /* =======================
           SEGUNDO BLOQUE
           ======================= */

                    e.setFecha2(ExcelValueParser.dateValidador(row.get(23)));
                    e.setMineralSulfuros(ExcelValueParser.decimalValidador(row.get(24)));
                    e.setLeyAg(ExcelValueParser.decimalValidador(row.get(25)));
                    e.setLeyAu(ExcelValueParser.decimalValidador(row.get(26)));
                    e.setLeyPb(ExcelValueParser.decimalValidador(row.get(27)));
                    e.setLeyZn(ExcelValueParser.decimalValidador(row.get(28)));

                    e.setMineralOxidos(ExcelValueParser.decimalValidador(row.get(29)));
                    e.setLeyAg2(ExcelValueParser.decimalValidador(row.get(30)));
                    e.setLeyAu3(ExcelValueParser.decimalValidador(row.get(31)));
                    e.setLeyPb4(ExcelValueParser.decimalValidador(row.get(32)));
                    e.setLeyZn5(ExcelValueParser.decimalValidador(row.get(33)));
                    e.setLeyZn6(ExcelValueParser.decimalValidador(row.get(34)));

        /* =======================
           TERCER BLOQUE
           ======================= */

                    e.setFecha3(ExcelValueParser.dateValidador(row.get(35)));
                    e.setMineralExtraidoBlTm(ExcelValueParser.decimalValidador(row.get(36)));
                    e.setLeyAgGpt(ExcelValueParser.decimalValidador(row.get(37)));
                    e.setLeyAuGpt(ExcelValueParser.decimalValidador(row.get(38)));
                    e.setLeyPbPct(ExcelValueParser.decimalValidador(row.get(39)));
                    e.setLeyZnPct(ExcelValueParser.decimalValidador(row.get(40)));

                    e.setMineralExtraidoBreastingTm(ExcelValueParser.decimalValidador(row.get(41)));
                    e.setLeyAgGpt2(ExcelValueParser.decimalValidador(row.get(42)));
                    e.setLeyAuGpt3(ExcelValueParser.decimalValidador(row.get(43)));
                    e.setLeyPbPct4(ExcelValueParser.decimalValidador(row.get(44)));
                    e.setLeyZnPct5(ExcelValueParser.decimalValidador(row.get(45)));

                    e.setMineralExtraidoCorteVerticalTm(ExcelValueParser.decimalValidador(row.get(46)));
                    e.setLeyAgGpt3(ExcelValueParser.decimalValidador(row.get(47)));
                    e.setLeyAuGpt4(ExcelValueParser.decimalValidador(row.get(48)));
                    e.setLeyPbPct5(ExcelValueParser.decimalValidador(row.get(49)));
                    e.setLeyZnPct6(ExcelValueParser.decimalValidador(row.get(50)));

                    e.setMineralSillesTm(ExcelValueParser.decimalValidador(row.get(51)));
                    e.setLeyAgGpt7(ExcelValueParser.decimalValidador(row.get(52)));
                    e.setLeyAuGpt8(ExcelValueParser.decimalValidador(row.get(53)));
                    e.setLeyPbPct9(ExcelValueParser.decimalValidador(row.get(54)));
                    e.setLeyZnPct10(ExcelValueParser.decimalValidador(row.get(55)));
                    e.setLeyZnPct11(ExcelValueParser.decimalValidador(row.get(56)));

                    e.setMineralRebajes(ExcelValueParser.decimalValidador(row.get(57)));

                    return e;
                }
            }
        } catch (Exception e) {
            log.error("Error procesando Excel geologia", e);
        }
        return null;
    }

}