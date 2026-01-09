package com.pasdm.etl.util;

import org.apache.poi.ss.usermodel.DateUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.apache.commons.math3.exception.util.LocalizedFormats.SCALE;

public class ExcelValueParser {
    private static final List<DateTimeFormatter> FORMATTERS = List.of(
            DateTimeFormatter.ofPattern("M/d/yy")
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

}
