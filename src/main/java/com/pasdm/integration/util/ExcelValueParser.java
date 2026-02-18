package com.pasdm.integration.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DateUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.apache.commons.math3.exception.util.LocalizedFormats.SCALE;

@Slf4j
public class ExcelValueParser {
    private static final DateTimeFormatter D_MMM_EN =
            new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern("d-MMM")
                    .parseDefaulting(ChronoField.YEAR, LocalDate.now().getYear())
                    .toFormatter(Locale.ENGLISH);

    private static final DateTimeFormatter DD_MMM_EN =
            new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern("dd-MMM")
                    .parseDefaulting(ChronoField.YEAR, LocalDate.now().getYear())
                    .toFormatter(Locale.ENGLISH);

    private static final DateTimeFormatter DD_MM_YYY =
            new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern("dd/MM/yyyy")
                    .parseDefaulting(ChronoField.YEAR, LocalDate.now().getYear())
                    .toFormatter(Locale.ENGLISH);

    private static final DateTimeFormatter FECHA_ES =
            new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern("d-MMM-yy")
                    .toFormatter(new Locale("es", "MX"));

    private static final DateTimeFormatter DD_MMM_ES =
            new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern("d-MMM")   // 👈 clave: d en lugar de dd
                    .parseDefaulting(ChronoField.YEAR, LocalDate.now().getYear())
                    .toFormatter(new Locale("es", "MX"));

    private static final List<DateTimeFormatter> FORMATTERS = List.of(
            DateTimeFormatter.ofPattern("M/d/yy"),
            DateTimeFormatter.ofPattern("M/d/yyyy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("dd-MMM", new Locale("es", "MX")),    // 01-ene
            DateTimeFormatter.ofPattern("d-MMM", new Locale("es", "MX")),
            FECHA_ES,
            DD_MMM_EN,
            DD_MM_YYY,
            DD_MMM_ES,
            D_MMM_EN      // 01-ene
    );

    private ExcelValueParser() {
        // utility class
    }

    public static BigDecimal decimalValidador(String value) {

        if (value == null || value.isBlank()) {
            return new BigDecimal("0.0");
        }

        String normalized = value
                .trim()
                .replace(",", " ")
                .replaceAll("[^0-9.-]", "");


        if (normalized.isBlank()) {
            return new BigDecimal("0.0");
        }

        if (normalized.contains("..")) {
            return new BigDecimal(normalized.replace("..", ".")).setScale(SCALE.ordinal(), RoundingMode.HALF_UP);
        }
        if (normalized.equals(".")) {
            return new BigDecimal("0.0");
        }
        try {
            return new BigDecimal(normalized).setScale(SCALE.ordinal(), RoundingMode.HALF_UP);
        } catch (Exception e) {
            log.error("ERROR EN DECIMAL " + value, e);

            return new BigDecimal("0.0");
            // throw new IllegalArgumentException("Decimal inválido: " + value);
        }
    }

    public static LocalDate dateValidadorDebug(Map<Integer, String> row, String value) {

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
        log.error("ERROR EN DATE " + value + " " + v + " " + row);
        return null;
    }

    public static LocalDate dateValidador(String value) {

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

        log.error("ERROR EN DATE " + value + " " + v);
        return null;
    }

    public static String stringValidador(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return value.trim();
    }

    public static Integer intValidador(String value) {

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
            log.error("ERROR EN INT " + value, e);
            return null;
        }
    }

    public static LocalDate dateValidadorProd(String value) {

        if (value == null || value.isBlank()) {
            return null;
        }

        String v = value.trim().toLowerCase();

        // Excel numérico
        if (v.matches("\\d+(\\.0)?")) {
            double excelDate = Double.parseDouble(v);
            return DateUtil.getLocalDateTime(excelDate).toLocalDate();
        }

        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                TemporalAccessor ta = formatter.parse(v);
                // log.info("Parse OK [{}] usando {}", value, formatter);

                // Si no trae año, asumimos año actual
                if (!ta.isSupported(ChronoField.YEAR)) {
                    int year = Year.now().getValue();
                    int month = ta.get(ChronoField.MONTH_OF_YEAR);
                    int day = ta.get(ChronoField.DAY_OF_MONTH);
                    return LocalDate.of(year, month, day);
                }

                return LocalDate.from(ta);

            } catch (Exception ignored) {
                // log.error("Parse FAIL [{}] con {}", value, formatter);
            }
        }

        throw new IllegalArgumentException("Fecha inválida: " + value);
    }
}
