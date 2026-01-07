package com.pasdm.etl.mapper;

import com.pasdm.etl.model.MTTO;
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
public class MTTOMapper {

    private static final List<DateTimeFormatter> FORMATTERS = List.of(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("M/d/yy"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("dd-MMM-yy", new Locale("es", "MX")),
            DateTimeFormatter.ofPattern("dd-MMM-yyyy", new Locale("es", "MX"))
    );

    public MTTO mapEntity(Map<Integer, String> row) {
        try {

            MTTO mtto = new MTTO();

            /*
            Bloque 1.
             */

            mtto.setFecha1(dateValidador(row.get(0)));
            mtto.setJumbosTepetatePct(decimalValidador(row.get(1)));
            mtto.setJumboBarrenacionLargaPct(decimalValidador(row.get(2)));
            mtto.setJumbosLinealesSillesPct(decimalValidador(row.get(3)));
            mtto.setJumbosMixtosSillesPct(decimalValidador(row.get(4)));
            mtto.setJumbosAcladoresTepetatePct(decimalValidador(row.get(5)));
            mtto.setJumbosAncladoresSillesPct(decimalValidador(row.get(6)));
            mtto.setScoopsLH203ControlRemotoPct(decimalValidador(row.get(7)));
            mtto.setScoopsLH203SinControlRemotoPct(decimalValidador(row.get(8)));
            mtto.setLoadersLH307Pct(decimalValidador(row.get(9)));
            mtto.setScoopsSt1030Pct(decimalValidador(row.get(10)));
            mtto.setCamionesCBP15tonPct(decimalValidador(row.get(11)));
            mtto.setCamionesCBP20tonPct(decimalValidador(row.get(12)));
            mtto.setCamionesCBP32tonPct(decimalValidador(row.get(13)));
            mtto.setMixerPct(decimalValidador(row.get(14)));
            mtto.setLanzadorAlfaPct(decimalValidador(row.get(15)));
            mtto.setLanzadorAlfaPct(decimalValidador(row.get(16)));
            mtto.setLanzadorPutzmeisterPct(decimalValidador(row.get(17)));

            /*
            Bloque 2.
             */

            mtto.setJumbosTepetateHrs(decimalValidador(row.get(18)));
            mtto.setJumboBarrenacionLarga_Hrs(decimalValidador(row.get(19)));
            mtto.setJumbosLinealesSilles_Hrs(decimalValidador(row.get(20)));
            mtto.setJumbosMixtosSilles_Hrs(decimalValidador(row.get(21)));
            mtto.setJumbosAncladoresSilles_Hrs(decimalValidador(row.get(22)));
            mtto.setJumbosAncladoresTepetate_Hrs(decimalValidador(row.get(23)));
            mtto.setScoopsLH203ControlRemoto_Hrs(decimalValidador(row.get(24)));
            mtto.setScoopsLH203SinControlRemoto_Hrs(decimalValidador(row.get(25)));
            mtto.setScoopsLH307_Hrs(decimalValidador(row.get(27)));
            mtto.setScoopsST1030_Hrs(decimalValidador(row.get(28)));
            mtto.setCamionesCBP15ton_Hrs(decimalValidador(row.get(29)));
            mtto.setCamionesCBP20ton_Hrs(decimalValidador(row.get(30)));
            mtto.setCamionesCBP32ton_Hrs(decimalValidador(row.get(31)));
            mtto.setMixerHrs(decimalValidador(row.get(32)));
            mtto.setLanzadoAlta_Hrs(decimalValidador(row.get(33)));
            mtto.setMiniMixer_Hrs(decimalValidador(row.get(34)));
            mtto.setLanzadorPutzmeister_Hrs(decimalValidador(row.get(35)));

            /*
            Bloque 3.
             */
            mtto.setJumboTepetateHrs1(decimalValidador(row.get(36)));
            mtto.setJumboBarrenacionLarga_Hrs1(decimalValidador(row.get(37)));
            mtto.setJumbosLinealesSilles_Hrs1(decimalValidador(row.get(38)));
            mtto.setJumbosMixtosSilles_Hrs1(decimalValidador(row.get(39)));
            mtto.setJumbosAncladoresTepetate_Hrs1(decimalValidador(row.get(40)));
            mtto.setScoopsLH203ControlRemoto_Hrs1(decimalValidador(row.get(41)));
            mtto.setScoopsLH203SinControlRemoto_Hrs1(decimalValidador(row.get(42)));
            mtto.setCamionesCBP15ton_Hrs1(decimalValidador(row.get(43)));
            mtto.setCamionesCBP20ton_Hrs1(decimalValidador(row.get(44)));
            mtto.setCamionesCBP32ton_Hrs1(decimalValidador(row.get(45)));
            mtto.setMixerHrs1(decimalValidador(row.get(46)));
            mtto.setLanzadorAlfaHrs1(decimalValidador(row.get(47)));
            mtto.setMiniMixerHrs1(decimalValidador(row.get(48)));
            mtto.setLanzadorPutzmeisterHrs1(decimalValidador(row.get(49)));

            /*
            Bloque 4.
             */
            mtto.setJumboTepetate2(intValidador(row.get(50)));
            mtto.setJumboBarrenacionLarga2(intValidador(row.get(51)));
            mtto.setJumbosLinealesSilles2(intValidador(row.get(52)));
            mtto.setJumbosMixtosSilles2(intValidador(row.get(53)));
            mtto.setJumbosAncladoresSilles2(intValidador(row.get(54)));
            mtto.setJumbosAncladoresTepetateHr2(intValidador(row.get(55)));
            mtto.setScoopsLH203ControlRemoto2(intValidador(row.get(56)));
            mtto.setScoopsLH203SinControlRemoto2(intValidador(row.get(57)));
            mtto.setScoopsLH307_2(intValidador(row.get(58)));
            mtto.setScoopsST1030_2(intValidador(row.get(59)));
            mtto.setCamionesCBP15ton_2(intValidador(row.get(60)));
            mtto.setCamionesCBP20ton_2(intValidador(row.get(61)));
            mtto.setCamionesCBP32ton_2(intValidador(row.get(62)));
            mtto.setMixer2(intValidador(row.get(63)));
            mtto.setLanzadorAlfa2(intValidador(row.get(64)));
            mtto.setMiniMixer2(intValidador(row.get(65)));
            mtto.setLanzadorPutzmeister2(intValidador(row.get(66)));

            /*
            Bloque 5.
             */

            mtto.setConsumo_EnergiaMina_Kw_hr(decimalValidador(row.get(67)));

            /*
            Bloque 6.
             */

            mtto.setOp_bombas_nivel315Hrs(intValidador(row.get(68)));
            mtto.setOp_bombas_nivel618Hrs(intValidador(row.get(69)));
            mtto.setVol_agua_extraida_m3(intValidador(row.get(70)));
            mtto.setVol_agua_enviado_nivel_315m3(intValidador(row.get(71)));

            // DISPONIBILIDAD
            mtto.setFecha2(dateValidador(row.get(72)));
            mtto.setVerificacionSupervisor1(intValidador(row.get(73)));
            mtto.setVerificacionSupervisor2(intValidador(row.get(74)));

            /*
            Bloque 8.
             */

            mtto.setFecha4(dateValidador(row.get(75)));
            mtto.setVerificacionSupervisor2(intValidador(row.get(76)));
            mtto.setVerificacionOperativo2(intValidador(row.get(77)));

            return mtto;
        } catch (Exception e){
            log.error("Error procesando Excel", e);
        }
        return null;

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

    public BigDecimal decimalValidador(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }

        String normalized = value
                .trim()
                .replace(",", "")
                .replaceAll("[^0-9.-]", "");

        try {
            return new BigDecimal(normalized).setScale(SCALE.ordinal(), RoundingMode.HALF_UP);
        } catch (Exception e) {
            throw new IllegalArgumentException("Decimal inválido: " + value);
        }
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
