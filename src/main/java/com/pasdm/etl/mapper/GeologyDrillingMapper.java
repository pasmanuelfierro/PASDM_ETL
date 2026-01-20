package com.pasdm.etl.mapper;

import com.pasdm.etl.model.GeologyDrilling;
import com.pasdm.etl.util.ExcelValueParser;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.pasdm.etl.util.ExcelValueParser.intValidador;

@Component
public class GeologyDrillingMapper {

    private static final int COL_FECHA_1 = 0;
    private static final int COL_BNO = 1;
    private static final int COL_EMPRESA = 2;
    private static final int COL_MAQUINA = 3;
    private static final int COL_FONDO_DIA_ANTERIOR = 4;
    private static final int COL_FONDO_ACTUAL_M = 5;
    private static final int COL_AVANCE_DIA_M = 6;
    private static final int COL_STATUS = 7;
    private static final int COL_ESTACION = 8;
    private static final int COL_JV_HOLES = 9;
    private static final int COL_TARGET = 10;
    private static final int COL_TARGET_2 = 11;


    public GeologyDrilling mapEntity(Map<Integer, String> row){
        if (row.isEmpty()) return null;

        GeologyDrilling GD = new GeologyDrilling();
        GD.setFecha1(ExcelValueParser.dateValidador(row.get(COL_FECHA_1)));
        GD.setBno(intValidador(row.get(COL_BNO)));
        GD.setEmpresa(intValidador(row.get(COL_EMPRESA)));
        GD.setMaquina(row.get(COL_MAQUINA));
        GD.setFondoDiaAnterior(ExcelValueParser.decimalValidador(row.get(COL_FONDO_DIA_ANTERIOR)));
        GD.setFondoActualM(ExcelValueParser.decimalValidador(row.get(COL_FONDO_ACTUAL_M)));
        GD.setAvanceDiaM(ExcelValueParser.decimalValidador(row.get(COL_AVANCE_DIA_M)));
        GD.setStatus(intValidador(row.get(COL_STATUS)));
        GD.setEstacion(intValidador(row.get(COL_ESTACION)));
        GD.setJvHoles(row.get(COL_JV_HOLES));
        GD.setTarget(row.get(COL_TARGET));
        GD.setTarget2(row.get(COL_TARGET_2));

        return GD;
    }
}
