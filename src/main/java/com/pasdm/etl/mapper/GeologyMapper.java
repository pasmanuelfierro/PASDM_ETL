package com.pasdm.etl.mapper;

import com.pasdm.etl.model.Geology;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.apache.commons.math3.exception.util.LocalizedFormats.SCALE;

@Slf4j
@Component
public class GeologyMapper {

    private static final List<DateTimeFormatter> FORMATTERS = List.of(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("M/d/yy"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("dd-MMM-yy", new Locale("es", "MX")),
            DateTimeFormatter.ofPattern("dd-MMM-yyyy", new Locale("es", "MX"))
    );

    public Geology mapEntity(Map<Integer, String> row) {
        try {

            Geology e = new Geology();

        /* =======================
           PRIMER BLOQUE
           ======================= */

            e.setFecha1(dateValidador(row.get(0)));
            e.setSobredilucionRebajesPct(intValidador(row.get(1)));
            e.setSobredilucionBlPct(intValidador(row.get(2)));
            e.setSobredilucionBreastingPct(intValidador(row.get(3)));
            e.setSobredilucionCorteVerticalPct(intValidador(row.get(4)));
            e.setSobredilucionSillesPct(intValidador(row.get(5)));

            e.setNumeroSillesMineral(intValidador(row.get(6)));
            e.setNumeroSillesTepetate(intValidador(row.get(7)));
            e.setNumeroRebajesBl(intValidador(row.get(8)));
            e.setNumeroRebajesBrt(intValidador(row.get(9)));

            e.setAvanceSillesMineral(intValidador(row.get(10)));
            e.setAvanceSillesTepetate(intValidador(row.get(11)));

            e.setPerforacionOreControlMts(decimalValidador(row.get(12)));
            e.setPerforacionInfillMts(decimalValidador(row.get(13)));
            e.setPerforacionBrownfieldMts(decimalValidador(row.get(14)));

            e.setLcMaquinaDiamecOreControl(decimalValidador(row.get(15)));
            e.setLcMaquinaExplorerPlusOreControl(decimalValidador(row.get(16)));
            e.setLcMaquinaD130BrownfieldInfill(decimalValidador(row.get(17)));
            e.setLcMaquina1500BrownfieldInfill(decimalValidador(row.get(18)));

            e.setContratistaBrownfieldSubterranea(decimalValidador(row.get(19)));
            e.setContratistaBrownfieldSuperficie(decimalValidador(row.get(20)));

        /* =======================
           SEGUNDO BLOQUE
           ======================= */

            e.setFecha2(dateValidador(row.get(22)));
            e.setMineralSulfuros(intValidador(row.get(23)));
            e.setLeyAg(decimalValidador(row.get(24)));
            e.setLeyAu(decimalValidador(row.get(25)));
            e.setLeyPb(decimalValidador(row.get(26)));
            e.setLeyZn(decimalValidador(row.get(27)));

            e.setMineralOxidos(intValidador(row.get(28)));
            e.setLeyAg2(intValidador(row.get(29)));
            e.setLeyAu3(decimalValidador(row.get(30)));
            e.setLeyPb4(intValidador(row.get(31)));
            e.setLeyZn5(intValidador(row.get(32)));
            e.setLeyZn6(intValidador(row.get(33)));

        /* =======================
           TERCER BLOQUE
           ======================= */

            e.setFecha3(dateValidador(row.get(34)));
            e.setMineralExtraidoBlTm(decimalValidador(row.get(35)));
            e.setLeyAgGpt(decimalValidador(row.get(36)));
            e.setLeyAuGpt(decimalValidador(row.get(37)));
            e.setLeyPbPct(decimalValidador(row.get(38)));
            e.setLeyZnPct(decimalValidador(row.get(39)));

            e.setMineralExtraidoBreastingTm(decimalValidador(row.get(40)));
            e.setLeyAgGpt2(decimalValidador(row.get(41)));
            e.setLeyAuGpt3(decimalValidador(row.get(42)));
            e.setLeyPbPct4(decimalValidador(row.get(43)));
            e.setLeyZnPct5(decimalValidador(row.get(44)));

            e.setMineralExtraidoCorteVerticalTm(intValidador(row.get(45)));
            e.setLeyAgGpt3(intValidador(row.get(46)));
            e.setLeyAuGpt4(intValidador(row.get(47)));
            e.setLeyPbPct5(intValidador(row.get(48)));
            e.setLeyZnPct6(intValidador(row.get(49)));

            e.setMineralSillesTm(decimalValidador(row.get(50)));
            e.setLeyAgGpt7(decimalValidador(row.get(51)));
            e.setLeyAuGpt8(decimalValidador(row.get(52)));
            e.setLeyPbPct9(decimalValidador(row.get(53)));
            e.setLeyZnPct10(decimalValidador(row.get(54)));
            e.setLeyZnPct11(decimalValidador(row.get(55)));

            e.setMineralRebajes(decimalValidador(row.get(56)));

            return e;
        } catch (Exception e) {
            log.error("Error procesando Excel", e);
        }
        return null;
    }

    public BigDecimal decimalValidador(String value) {

        if (value == null || value.isBlank()) {
            return null;
        }

        String normalized = value
                .trim()
                .replace(",", " ")
                .replaceAll("[^0-9.-]", "");

        try {
            return new BigDecimal(normalized).setScale(SCALE.ordinal(), RoundingMode.HALF_UP);
        } catch (Exception e) {
            throw new IllegalArgumentException("Decimal inválido: " + value);
        }
    }

    public Integer intValidador(String value) {

        if (value == null || value.isBlank()) {
            return null;
        }

        String normalized = value
                .trim()
                .replace(",", "")
                .replaceAll("[^0-9.-]", "");

        try {
            if (normalized.contains(".")) {
                BigDecimal bd = new BigDecimal(normalized);
                return bd.intValueExact();
            }

            return Integer.valueOf(normalized);

        } catch (Exception e) {
            throw new IllegalArgumentException("Entero inválido: " + value);
        }
    }

    public LocalDate dateValidador(String value) {

        if (value == null || value.isBlank()) {
            return null;
        }

        String v = value.trim();

        if (v.matches("\\d+(\\.0)?")) {
            double excelDate = Double.parseDouble(v);
            return DateUtil.getLocalDateTime(excelDate).toLocalDate();
        }

        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return LocalDate.parse(v, formatter);
            } catch (Exception ignored) {
            }
        }

        throw new IllegalArgumentException("Fecha inválida: " + value);
    }

    /* =======================
       VARIANTE TOLERANTE
       ======================= */

    public LocalDate parserSafe(String value) {
        try {
            return dateValidador(value);
        } catch (Exception e) {
            return null;
        }
    }
}