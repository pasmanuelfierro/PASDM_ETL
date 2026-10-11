package com.pasdm.integration.mapper;

import com.pasdm.integration.model.LaboratoryGrades;
import com.pasdm.integration.util.ExcelValueParser;
import com.pasdm.integration.util.HashUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

import static com.pasdm.integration.util.ExcelValueParser.decimalValidador;
import static com.pasdm.integration.util.ExcelValueParser.intValidador;

@Slf4j
@Component
public class LaboratoryGradesMapper {

    private static final int COL_FECHA = 0;
    private static final int COL_TURNO = 1;
    // BANDA
    private static final int COL_BAN_AU = 2;
    private static final int COL_BAN_AG = 3;
    private static final int COL_BAN_PB = 4;
    private static final int COL_BAN_ZN = 5;
    private static final int COL_BAN_HUMEDAD = 6;

    private static final int COL_FINOS_AG = 7;
    private static final int COL_FINOS_PB = 8;
    private static final int COL_FINOS_ZN = 9;

    public LaboratoryGrades mapEntity(Map<Integer, String> row) {
        try {

            if (row.isEmpty()) return null;
            LaboratoryGrades laboratoryGrades = new LaboratoryGrades();
            if (row.get(COL_FECHA) != null) {

                laboratoryGrades.setFecha(ExcelValueParser.dateValidador(row.get(COL_FECHA)));
                laboratoryGrades.setTurno(intValidador(row.get(COL_TURNO)));
                laboratoryGrades.setBanAu(ExcelValueParser.decimalValidador((row.get(COL_BAN_AU))));
                laboratoryGrades.setBanAg(ExcelValueParser.decimalValidador((row.get(COL_BAN_AG))));
                laboratoryGrades.setBanPb(ExcelValueParser.decimalValidador((row.get(COL_BAN_PB))));
                laboratoryGrades.setBanZn(ExcelValueParser.decimalValidador((row.get(COL_BAN_ZN))));
                laboratoryGrades.setBanHumedad(ExcelValueParser.decimalValidador((row.get(COL_BAN_HUMEDAD))));
                laboratoryGrades.setFinosAg(ExcelValueParser.decimalValidador((row.get(COL_FINOS_AG))));
                laboratoryGrades.setFinosPb(ExcelValueParser.decimalValidador((row.get(COL_FINOS_PB))));
                laboratoryGrades.setFinosZn(ExcelValueParser.decimalValidador((row.get(COL_FINOS_ZN))));

                laboratoryGrades.setRowHash(HashUtil.calculateRowHash(
                        laboratoryGrades.getFecha().toString(),
                        laboratoryGrades.getTurno().toString(),
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""));

                return laboratoryGrades;
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("Error procesando Excel LaboratoryPlant", e);
            return null;
        }
    }
}
