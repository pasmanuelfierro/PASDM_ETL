package com.pasdm.etl.mapper;

import com.pasdm.etl.model.RRHH;
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
public class RRHHMapper {

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

    public RRHH mapEntity(Map<Integer, String> row) {
        try {
            RRHH e = new RRHH();

            e.setFecha(dateValidador(row.get(1)));

            /* =======================
               ASISTENCIA
               ======================= */
            e.setAsistenciaPersonal(intValidador(row.get(2)));
            e.setVacaciones(intValidador(row.get(3)));
            e.setDescanso(intValidador(row.get(4)));
            e.setFalta(intValidador(row.get(5)));
            e.setIncapacidad(intValidador(row.get(6)));
            e.setPermisoConGoce(intValidador(row.get(7)));
            e.setPermisoSinGoce(intValidador(row.get(8)));
            e.setAsistenciaContratistas(intValidador(row.get(9)));

            /* =======================
               CAPACITACION
               ======================= */
            e.setOperacionMina(intValidador(row.get(11)));
            e.setOtrasAreas(intValidador(row.get(12)));
            e.setInduccion(intValidador(row.get(13)));

            /* =======================
               VERIFICACIONES CRM
               ======================= */
            e.setFechaVerificacion(dateValidador(row.get(15)));
            e.setVerificacionSupervisor(intValidador(row.get(16)));
            e.setVerificacionOperativo(intValidador(row.get(17)));
            
            e.setFechaCrm(dateValidador(row.get(19)));
            e.setComentario(stringValidador(row.get(20)));
            e.setImagenes(stringValidador(row.get(21)));
            return e;
        } catch (Exception e) {
            log.error("Error procesando Excel RRHH", e);
        }
        return null;
    }

    public String stringValidador(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return value.trim();
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
}
