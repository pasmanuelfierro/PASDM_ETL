package com.pasdm.integration.mapper;


import com.pasdm.integration.model.LaboratoryQC;
import com.pasdm.integration.util.ExcelValueParser;
import com.pasdm.integration.util.HashUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Component
public class LaboratoryQCMapper {

    // =======================
    // IDENTIFICACIÓN
    // =======================
    private static final int COL_FECHA = 0;
    private static final int COL_LOTE = 1;

    // =======================
    // LABORATORIO
    // =======================
    private static final int COL_PLATA = 2;
    private static final int COL_MINIMO = 3;
    private static final int COL_OPTIMO = 4;
    private static final int COL_MAXIMO = 5;
    private static final int COL_ORO = 6;
    private static final int COL_MINIMO_2 = 7;
    private static final int COL_OPTIMO_3 = 8;
    private static final int COL_MAXIMO_4 = 9;
    private static final int COL_PLOMO = 10;
    private static final int COL_MINIMOS = 11;
    private static final int COL_OPTIMO_6 = 12;
    private static final int COL_MAXIMO_7 = 13;
    private static final int COL_ZINC = 14;
    private static final int COL_MINIMO_8 = 15;
    private static final int COL_OPTIMO_9 = 16;
    private static final int COL_MAXIMO_10 = 17;

    public LaboratoryQC mapEntity(Map<Integer, String> row) {

        if (row.isEmpty()) return null;

        LaboratoryQC p = new LaboratoryQC();

        try {
            if (ExcelValueParser.dateValidador(row.get(COL_FECHA)) != null) {

                p.setFecha(ExcelValueParser.dateValidador(row.get(COL_FECHA)));
                p.setLote(row.get(COL_LOTE));

                p.setPlata(ExcelValueParser.decimalValidador(row.get(COL_PLATA)));
                p.setMinimo(ExcelValueParser.decimalValidador(row.get(COL_MINIMO)));
                p.setOptimo(ExcelValueParser.decimalValidador(row.get(COL_OPTIMO)));
                p.setMaximo(ExcelValueParser.decimalValidador(row.get(COL_MAXIMO)));
                p.setOro(ExcelValueParser.decimalValidador(row.get(COL_ORO)));
                p.setMinimo2(ExcelValueParser.decimalValidador(row.get(COL_MINIMO_2)));
                p.setOptimo3(ExcelValueParser.decimalValidador(row.get(COL_OPTIMO_3)));
                p.setMaximo4(ExcelValueParser.decimalValidador(row.get(COL_MAXIMO_4)));
                p.setPlomo(ExcelValueParser.decimalValidador(row.get(COL_PLOMO)));
                p.setMinimos(ExcelValueParser.decimalValidador(row.get(COL_MINIMOS)));
                p.setOptimo6(ExcelValueParser.decimalValidador(row.get(COL_OPTIMO_6)));
                p.setMaximo7(ExcelValueParser.decimalValidador(row.get(COL_MAXIMO_7)));
                p.setZinc(ExcelValueParser.decimalValidador(row.get(COL_ZINC)));
                p.setMinimo8(ExcelValueParser.decimalValidador(row.get(COL_MINIMO_8)));
                p.setOptimo9(ExcelValueParser.decimalValidador(row.get(COL_OPTIMO_9)));
                p.setMaximo10(ExcelValueParser.decimalValidador(row.get(COL_MAXIMO_10)));

                p.setRowHash(HashUtil.calculateRowHash(
                        p.getFecha().toString(),
                        p.getLote(),
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""));
                return p;
            }

            return null;

        } catch (Exception e) {
            log.error("Error procesando laboratory qc Excel", e);
            return null;
        }
    }
}
