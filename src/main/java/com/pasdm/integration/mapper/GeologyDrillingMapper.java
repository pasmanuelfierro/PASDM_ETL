package com.pasdm.integration.mapper;

import com.pasdm.integration.model.GeologyDrilling;
import com.pasdm.integration.util.ExcelValueParser;
import com.pasdm.integration.util.HashUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class GeologyDrillingMapper {

    private static final int COL_FECHA = 1;
    private static final int COL_BNO = 2;
    private static final int COL_EMPRESA = 3;
    private static final int COL_MAQUINA = 4;
    private static final int COL_FONDO_DIA_ANTERIOR_M = 5;
    private static final int COL_FONDO_ACTUAL_M = 6;
    private static final int COL_AVANCE_DIA_M = 7;
    private static final int COL_STATUS = 8;
    private static final int COL_ESTACION = 9;
    private static final int COL_JV_HOLES = 10;
    private static final int COL_TARGET = 11;
    private static final int COL_TARGET_2 = 12;

    public GeologyDrilling mapEntity(Map<Integer, String> row) {
        if (row.isEmpty()) return null;

        GeologyDrilling gd = new GeologyDrilling();
        try {
            if (ExcelValueParser.dateValidador(row.get(COL_FECHA)) != null) {

                gd.setFecha(ExcelValueParser.dateValidador(row.get(COL_FECHA)));
                gd.setBno(row.get(COL_BNO));
                gd.setEmpresa(row.get(COL_EMPRESA));
                gd.setMaquina(row.get(COL_MAQUINA));
                gd.setFondoDiaAnteriorM(ExcelValueParser.decimalValidador(row.get(COL_FONDO_DIA_ANTERIOR_M)));
                gd.setFondoActualM(ExcelValueParser.decimalValidador(row.get(COL_FONDO_ACTUAL_M)));
                gd.setAvanceDiaM(ExcelValueParser.decimalValidador(row.get(COL_AVANCE_DIA_M)));
                gd.setStatus(row.get(COL_STATUS));
                gd.setEstacion(row.get(COL_ESTACION));
                gd.setJvHoles(row.get(COL_JV_HOLES));
                gd.setTarget(row.get(COL_TARGET));
                gd.setTarget2(row.get(COL_TARGET_2));
                gd.setRowHash(HashUtil.calculateRowHash(gd.getFecha().toString(),gd.getBno(),gd.getStatus(),gd.getTarget(),gd.getTarget2(),gd.getMaquina()));
                return gd;
            } else {
                return null;
            }
        } catch (Exception ex) {
            log.error("Error procesando Excel GeologyDrilling", ex);
            return null;
        }

    }
}
