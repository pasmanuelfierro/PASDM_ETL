package com.pasdm.integration.mapper;

import com.pasdm.integration.model.LaboratoryPlant;
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
public class LaboratoryPlantMapper {

    private static final int COL_NUM_DIA = 0;
    private static final int COL_TURNO = 1;
    // BANDA
    private static final int COL_BAN_AU = 2;
    private static final int COL_BAN_AG = 3;
    private static final int COL_BAN_PB = 4;
    private static final int COL_BAN_ZN = 5;
    private static final int COL_BAN_HUMEDAD = 6;
    // FINOS
    private static final int COL_FINOS_AG = 7;
    private static final int COL_FINOS_ZN = 8;

    public LaboratoryPlant mapEntity(Map<Integer, String> row) {
        try {

            if (row.isEmpty()) return null;
            LaboratoryPlant laboratoryPlant = new LaboratoryPlant();
            Integer month = ZonedDateTime.now(ZoneId.of("America/Mexico_City")).getMonthValue();
            if (row.get(COL_NUM_DIA) != null) {
                log.error("Procesando dia {}  ", row.get(COL_NUM_DIA));
                log.error("Procesando mes {}  ", month);

                laboratoryPlant.setNumDia(row.get(COL_NUM_DIA));
                laboratoryPlant.setMonth(month);
                laboratoryPlant.setTurno(intValidador(row.get(COL_TURNO)));

                // BANDA
                laboratoryPlant.setBanAu(intValidador(row.get(COL_BAN_AU)));
                laboratoryPlant.setBanAg(intValidador(row.get(COL_BAN_AG)));
                laboratoryPlant.setBanPb(intValidador(row.get(COL_BAN_PB)));
                laboratoryPlant.setBanZn(intValidador(row.get(COL_BAN_ZN)));
                laboratoryPlant.setBanHumedad(decimalValidador(row.get(COL_BAN_HUMEDAD)));

                // FINOS
                laboratoryPlant.setFinosAg(intValidador(row.get(COL_FINOS_AG)));
                laboratoryPlant.setFinosZn(intValidador(row.get(COL_FINOS_ZN)));

                laboratoryPlant.setRowHash(HashUtil.calculateRowHash(laboratoryPlant.getNumDia(), laboratoryPlant.getMonth().toString(), laboratoryPlant.getTurno().toString(), "", "", ""));

                return laboratoryPlant;
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("Error procesando Excel LaboratoryPlant", e);
            return null;
        }
    }
}
