package com.pasdm.integration.mapper;


import com.pasdm.integration.model.Laboratory;
import com.pasdm.integration.util.ExcelValueParser;
import com.pasdm.integration.util.HashUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.pasdm.integration.util.ExcelValueParser.intValidador;

@Slf4j
@Component
public class LaboratoryMapper {

    // =======================
    // IDENTIFICACIÓN
    // =======================
    private static final int COL_FECHA = 0;
    private static final int COL_TIPO = 1;

    // =======================
    // LABORATORIO
    // =======================
    private static final int COL_MINA = 2;
    private static final int COL_EXPLORACION = 3;
    private static final int COL_PLANTA = 4;
    private static final int COL_EMBARQUES_PB = 5;
    private static final int COL_EMBARQUES_ZN = 6;
    private static final int COL_MUESTRAS_ESPECIALES = 7;
    private static final int COL_METALURGIAS = 8;
    private static final int COL_COMERCIALIZACION = 9;
    private static final int COL_REENSAYES = 10;
    private static final int COL_QAQC = 11;

    public Laboratory mapEntity(Map<Integer, String> row) {

        if (row.isEmpty()) return null;

        Laboratory p = new Laboratory();

        try {
            if (ExcelValueParser.dateValidador(row.get(COL_FECHA)) != null) {

                p.setFecha(ExcelValueParser.dateValidador(row.get(COL_FECHA)));
                p.setTipo(row.get(COL_TIPO));

                p.setMina(ExcelValueParser.decimalValidador(row.get(COL_MINA)));
                p.setExploracion(ExcelValueParser.decimalValidador(row.get(COL_EXPLORACION)));
                p.setPlanta(ExcelValueParser.decimalValidador(row.get(COL_PLANTA)));
                p.setEmbarquesPb(ExcelValueParser.decimalValidador(row.get(COL_EMBARQUES_PB)));
                p.setEmbarquesZn(ExcelValueParser.decimalValidador(row.get(COL_EMBARQUES_ZN)));
                p.setMuestrasEspeciales(ExcelValueParser.decimalValidador(row.get(COL_MUESTRAS_ESPECIALES)));
                p.setMetalurgias(ExcelValueParser.decimalValidador(row.get(COL_METALURGIAS)));
                p.setComercializacion(ExcelValueParser.decimalValidador(row.get(COL_COMERCIALIZACION)));
                p.setReensayes(ExcelValueParser.decimalValidador(row.get(COL_REENSAYES)));
                p.setQaqc(ExcelValueParser.decimalValidador(row.get(COL_QAQC)));

                p.setRowHash(HashUtil.calculateRowHash(
                        p.getFecha().toString(),
                        p.getTipo(),
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
            log.error("Error procesando Excel", e);
            return null;
        }
    }
}
