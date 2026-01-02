package com.pasdm.etl.mapper;

import com.pasdm.etl.model.Plant;
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

@Slf4j
@Component
public class PlantMapper {

    private static final List<DateTimeFormatter> FORMATTERS = List.of(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("d/M/yy"),
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("M/d/yy"),
            DateTimeFormatter.ofPattern("M/d/yyyy"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("dd-MMM-yy", new Locale("es", "MX")),
            DateTimeFormatter.ofPattern("dd-MMM-yyyy", new Locale("es", "MX"))
    );

    public Plant mapEntity(Map<Integer, String> row) {
        try {

            Plant e = new Plant();

            e.setFecha1(dateValidador(row.get(0)));

            /* =======================
               BLOQUE MINERAL OXIDOS PARTE 1
               ======================= */
            e.setToneladasMilOxidos(intValidador(row.get(1)));
            e.setLeyAuOxidos(decimalValidador(row.get(2)));
            e.setLeyAgOxidos(decimalValidador(row.get(3)));
            e.setRecuperacionAuOxidosPct(decimalValidador(row.get(4)));
            e.setRecuperacionAgOxidosPct(decimalValidador(row.get(5)));

            /* =======================
               BLOQUE MINERAL SULFUROS PARTE 1
               ======================= */
            e.setToneladasMilSulfuros(intValidador(row.get(6)));
            e.setLeyAuSulfuros(decimalValidador(row.get(7)));
            e.setLeyAgSulfuros(decimalValidador(row.get(8)));
            e.setLeyPbSulfuros(decimalValidador(row.get(9)));
            e.setLeyZnSulfuros(decimalValidador(row.get(10)));
            e.setRecuperacionAuSulfurosPct(decimalValidador(row.get(11)));
            e.setRecuperacionAgSulfurosPct(decimalValidador(row.get(12)));
            e.setRecuperacionPbSulfurosPct(decimalValidador(row.get(13)));
            e.setRecuperacionZnSulfurosPct(decimalValidador(row.get(14)));

            /* =======================
               BLOQUE STOCK
               ======================= */
            e.setStockSulfurosTm(intValidador(row.get(15)));
            e.setStockOxidosTm(intValidador(row.get(16)));
            e.setToneladasMilTotal(intValidador(row.get(17)));

            /* =======================
               BLOQUE OXIDOS
               ======================= */
            e.setAuOxidosOz(decimalValidador(row.get(18)));
            e.setAgOxidosOz(decimalValidador(row.get(19)));

            /* =======================
               BLOQUE SULFUROS
               ======================= */
            e.setAuSulfurosOz(decimalValidador(row.get(20)));
            e.setAgSulfurosOz(decimalValidador(row.get(21)));
            e.setPbSulfurosT(decimalValidador(row.get(22)));
            e.setZnSulfurosT(decimalValidador(row.get(23)));

            /* =======================
               BLOQUE CONSOLIDADO
               ======================= */
            e.setAuConsolidadoOz(decimalValidador(row.get(24)));
            e.setAgConsolidadoOz(decimalValidador(row.get(25)));
            e.setPbConsolidadoT(decimalValidador(row.get(26)));
            e.setZnConsolidadoT(decimalValidador(row.get(27)));

            /* =======================
               BLOQUE LEY CONSOLIDADO
               ======================= */
            e.setLeyAuConsolidado(decimalValidador(row.get(28)));
            e.setLeyAgConsolidado(decimalValidador(row.get(29)));

            /* =======================
               BLOQUE RECUPERACION CONSOLIDADO
               ======================= */
            e.setRecuperacionAuConsolidadoPct(decimalValidador(row.get(30)));
            e.setRecuperacionAgConsolidadoPct(decimalValidador(row.get(31)));

            /* =======================
               BLOQUE PROCESO
               ======================= */
            e.setCianuroKg(intValidador(row.get(32)));
            e.setAntiincrustanteKg(decimalValidador(row.get(33)));
            e.setCalKg(decimalValidador(row.get(34)));
            e.setBolasMoliendaKg(decimalValidador(row.get(35)));
            e.setDepreZincKg(decimalValidador(row.get(36)));

            /* =======================
               BLOQUE SERVICIOS
               ======================= */
            e.setElectricidadKw(decimalValidador(row.get(37)));
            e.setAguaM3(decimalValidador(row.get(38)));

            /* =======================
               BLOQUE DISPONIBILIDAD PLANTA
               ======================= */
            e.setDisponibilidadPlantaOxidosHrs(intValidador(row.get(39)));
            e.setDisponibilidadPlantaSulfurosHrs(intValidador(row.get(40)));

            /* =======================
               BLOQUE USO PLANTA
               ======================= */
            e.setUsoPlantaOxidosHrs(intValidador(row.get(41)));
            e.setUsoPlantaSulfurosHrs(intValidador(row.get(42)));

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
                .replace(",", "")
                .replaceAll("[^0-9.-Ee]", "");

        try {
            return new BigDecimal(normalized)
                    .setScale(10, RoundingMode.HALF_UP);
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
                .replaceAll("[^0-9.-Ee]", "");

        try {
            if (normalized.contains(".")) {
                BigDecimal bd = new BigDecimal(normalized);
                return bd.setScale(0, RoundingMode.HALF_UP).intValue();
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

    public LocalDate parserSafe(String value) {
        try {
            return dateValidador(value);
        } catch (Exception e) {
            return null;
        }
    }
}
