package com.pasdm.integration.mapper;


import com.pasdm.integration.model.PlannedLabors;
import com.pasdm.integration.util.ExcelValueParser;
import com.pasdm.integration.util.HashUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;

@Slf4j
@Component
public class PlannedDevLaborsMapper {

    private static final int COL_FECHA = 1;
    private static final int COL_ZONA = 7;
    private static final int COL_OBRA = 11;
    private static final int COL_ESTRUCTURA = 12;
    private static final int COL_LABOR = 13;


    public PlannedLabors mapEntity(Map<Integer, String> row, int MONTH) {

        if (row.isEmpty()) return null;

        PlannedLabors p = new PlannedLabors();

        try {
            LocalDate localDate = ExcelValueParser.dateValidador(row.get(COL_FECHA));

            if (localDate != null) {
                if (localDate.getMonthValue() == MONTH) {
                    p.setFecha(localDate);
                    p.setZona(row.get(COL_ZONA));
                    String OBRA = ExcelValueParser.stringValidador(row.get(COL_OBRA));
                    p.setObra(OBRA.equals("SILLES") ? "SILL" : OBRA);

                    p.setEstructura(ExcelValueParser.stringValidador(row.get(COL_ESTRUCTURA)));
                    p.setLabor(ExcelValueParser.stringValidador(row.get(COL_LABOR)));

                    p.setRowHash(HashUtil.calculateRowHash(
                            p.getZona(),
                            p.getObra(),
                            p.getEstructura(),
                            p.getLabor(),
                            "",
                            "",
                            "",
                            ""));
                    return p;
                } else {
                    return null;
                }

            }
            return null;

        } catch (Exception e) {
            log.error("Error procesando PlannedLabors", e);
            return null;
        }
    }
}
