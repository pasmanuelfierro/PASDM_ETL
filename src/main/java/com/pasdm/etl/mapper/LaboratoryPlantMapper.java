package com.pasdm.etl.mapper;

import com.pasdm.etl.model.LaboratoryPlant;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.pasdm.etl.util.ExcelValueParser.decimalValidador;
import static com.pasdm.etl.util.ExcelValueParser.intValidador;

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

    public LaboratoryPlant mapEntity(Map<Integer, String> row){
        if (row.isEmpty()) return null;
        LaboratoryPlant laboratoryPlant = new LaboratoryPlant();

        laboratoryPlant.setNumDia(row.get(COL_NUM_DIA));
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

        return laboratoryPlant;
    }

}
