package com.pasdm.integration.mapper;

import com.pasdm.integration.model.DailyCMDActual;
import com.pasdm.integration.util.ExcelValueParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class DailyCMDActualMapper {

    // =======================
    // IDENTIFICACIÓN
    // =======================
    private static final int COL_DATE = 1;
    private static final int COL_ROW_HASH = 2;

    // =======================
    // SAFETY
    // =======================
    private static final int COL_FRECUENCY_INDEX = 3;
    private static final int COL_SEVERITY_INDEX = 4;
    private static final int COL_LOST_DAYS = 5;
    private static final int COL_EXPOSURE_HOURS = 6;
    private static final int COL_LT_HPIF = 7;
    private static final int COL_HPI_CON_LESION = 8;
    private static final int COL_HPI_SIN_LESION = 9;
    private static final int COL_ACCIDENTE_TRAYECTO = 10;
    private static final int COL_PROPERTY_DAMAGE_INCIDENTS = 11;
    private static final int COL_LOST_TIME_ACCIDENTS = 12;
    private static final int COL_TARJETA_ROJA = 13;
    private static final int COL_MEDICAL_ATTENTION = 14;
    private static final int COL_FIRST_AID_ACCIDENTS = 15;
    private static final int COL_FATAL_ACCIDENT = 16;

    // =======================
    // HR
    // =======================
    private static final int COL_DAILY_WORKERS = 17;
    private static final int COL_TOTAL_WORKERS = 18;
    private static final int COL_TOTAL_CONTRACTORS_OPERATIVOS = 19;
    private static final int COL_TOTAL_WORKERS_PASMEX = 20;
    private static final int COL_HM_LABOURED = 21;
    private static final int COL_HM_CONTRACTORS = 22;
    private static final int COL_HM_TRAINING_SH = 23;

    // =======================
    // PLANTA / METALURGIA
    // =======================
    private static final int COL_FLUJO_PATIOS = 24;
    private static final int COL_FLUJO_ENTRADA_MERRIL = 25;
    private static final int COL_AU_GT = 26;
    private static final int COL_AG_GT = 27;
    private static final int COL_AU_OZ = 28;
    private static final int COL_AG_OZ = 29;

    public DailyCMDActual mapEntity(Map<Integer, String> row) {

        if (row.isEmpty()) return null;

        DailyCMDActual p = new DailyCMDActual();

        try {
            if (ExcelValueParser.dateValidador(row.get(COL_DATE)) != null) {

                p.setFecha(ExcelValueParser.dateValidador(row.get(COL_DATE)));
                p.setRowHash(row.get(COL_ROW_HASH));

                p.setFrecuencyIndex(ExcelValueParser.decimalValidador(row.get(COL_FRECUENCY_INDEX)));
                p.setSeverityIndex(ExcelValueParser.decimalValidador(row.get(COL_SEVERITY_INDEX)));
                p.setLostDays(ExcelValueParser.decimalValidador(row.get(COL_LOST_DAYS)));
                p.setExposureHours(ExcelValueParser.decimalValidador(row.get(COL_EXPOSURE_HOURS)));
                p.setLtHpif(ExcelValueParser.decimalValidador(row.get(COL_LT_HPIF)));
                p.setHpiConLesion(ExcelValueParser.decimalValidador(row.get(COL_HPI_CON_LESION)));
                p.setHpiSinLesion(ExcelValueParser.decimalValidador(row.get(COL_HPI_SIN_LESION)));
                p.setAccidenteTrayecto(ExcelValueParser.decimalValidador(row.get(COL_ACCIDENTE_TRAYECTO)));
                p.setPropertyDamageIncidents(ExcelValueParser.decimalValidador(row.get(COL_PROPERTY_DAMAGE_INCIDENTS)));
                p.setLostTimeAccidents(ExcelValueParser.decimalValidador(row.get(COL_LOST_TIME_ACCIDENTS)));
                p.setTarjetaRoja(ExcelValueParser.decimalValidador(row.get(COL_TARJETA_ROJA)));
                p.setMedicalAttention(ExcelValueParser.decimalValidador(row.get(COL_MEDICAL_ATTENTION)));
                p.setFirstAidAccidents(ExcelValueParser.decimalValidador(row.get(COL_FIRST_AID_ACCIDENTS)));
                p.setFatalAccident(ExcelValueParser.decimalValidador(row.get(COL_FATAL_ACCIDENT)));

                p.setDailyWorkers(ExcelValueParser.decimalValidador(row.get(COL_DAILY_WORKERS)));
                p.setTotalWorkers(ExcelValueParser.decimalValidador(row.get(COL_TOTAL_WORKERS)));
                p.setTotalContractorsOperativos(ExcelValueParser.decimalValidador(row.get(COL_TOTAL_CONTRACTORS_OPERATIVOS)));
                p.setTotalWorkersPasmex(ExcelValueParser.decimalValidador(row.get(COL_TOTAL_WORKERS_PASMEX)));
                p.setHmLaboured(ExcelValueParser.decimalValidador(row.get(COL_HM_LABOURED)));
                p.setHmContractors(ExcelValueParser.decimalValidador(row.get(COL_HM_CONTRACTORS)));
                p.setHmTrainingSh(ExcelValueParser.decimalValidador(row.get(COL_HM_TRAINING_SH)));

                p.setFlujoPatios(ExcelValueParser.decimalValidador(row.get(COL_FLUJO_PATIOS)));
                p.setFlujoEntradaMerril(ExcelValueParser.decimalValidador(row.get(COL_FLUJO_ENTRADA_MERRIL)));
                p.setAuGt(ExcelValueParser.decimalValidador(row.get(COL_AU_GT)));
                p.setAgGt(ExcelValueParser.decimalValidador(row.get(COL_AG_GT)));
                p.setAuOz(ExcelValueParser.decimalValidador(row.get(COL_AU_OZ)));
                p.setAgOz(ExcelValueParser.decimalValidador(row.get(COL_AG_OZ)));

                return p;
            }

            return null;

        } catch (Exception e) {
            log.error("Error procesando Excel CMD ACTUAL", e);
            return null;
        }
    }
}
