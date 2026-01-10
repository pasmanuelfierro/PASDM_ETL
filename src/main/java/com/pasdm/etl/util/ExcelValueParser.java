package com.pasdm.etl.util;

import org.apache.poi.ss.usermodel.DateUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Locale;

import static org.apache.commons.math3.exception.util.LocalizedFormats.SCALE;

public class ExcelValueParser {
    private static final List<DateTimeFormatter> FORMATTERS = List.of(
            DateTimeFormatter.ofPattern("M/d/yy"),
            DateTimeFormatter.ofPattern("dd-MMM", new Locale("es", "MX")) ,    // 01-ene
            DateTimeFormatter.ofPattern("d-MMM", new Locale("es", "MX"))       // 01-ene
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

        try {
            return new BigDecimal(normalized).setScale(SCALE.ordinal(), RoundingMode.HALF_UP);
        } catch (Exception e) {
            throw new IllegalArgumentException("Decimal inválido: " + value);
        }
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

        throw new IllegalArgumentException("Fecha inválida: " + value);
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
            throw new IllegalArgumentException("Entero inválido: " + value);
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

                // Si no trae año, asumimos año actual
                if (!ta.isSupported(ChronoField.YEAR)) {
                    int year = Year.now().getValue();
                    int month = ta.get(ChronoField.MONTH_OF_YEAR);
                    int day = ta.get(ChronoField.DAY_OF_MONTH);
                    return LocalDate.of(year, month, day);
                }

                return LocalDate.from(ta);

            } catch (Exception ignored) {
            }
        }

        throw new IllegalArgumentException("Fecha inválida: " + value);
    }
}
