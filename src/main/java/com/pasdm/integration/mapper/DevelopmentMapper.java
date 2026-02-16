package com.pasdm.integration.mapper;

import com.pasdm.integration.model.Development;
import com.pasdm.integration.util.ExcelValueParser;
import com.pasdm.integration.util.HashUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;

@Slf4j
@Component
public class DevelopmentMapper {
    // =======================
    // ÍNDICES DE COLUMNA (Excel)
    // =======================
    private static final int COL_FECHA = 2;  // C
    private static final int COL_GRUPO = 3;  // D
    private static final int COL_PRIORIDAD = 4;  // E
    private static final int COL_LOTE = 5;  // F
    private static final int COL_STATUS = 6;  // G
    private static final int COL_MINA = 7;  // H
    private static final int COL_ZONA = 8;  // I
    private static final int COL_COSTOS = 9;  // J
    private static final int COL_ELABORADO = 10; // K
    private static final int COL_MIN_TEP = 11; // L
    private static final int COL_OBRA = 12; // M
    private static final int COL_ESTRUCTURA = 13; // N
    private static final int COL_LUGAR = 14; // O
    private static final int COL_METROS = 15; // P

    public Development mapEntity(Map<Integer, String> row) {
        try {

            LocalDate localDate = LocalDate.now().minusYears(1);
            Development e = new Development();
            LocalDate date = ExcelValueParser.dateValidador(row.get(COL_FECHA));
            if (date != null) {

                //  if (date.isAfter(localDate) || date.isEqual(localDate)) {

                e.setFecha(ExcelValueParser.dateValidador(row.get(COL_FECHA)));
                e.setGrupo(row.get(COL_GRUPO));
                e.setPrioridad(ExcelValueParser.intValidador(row.get(COL_PRIORIDAD)));
                e.setLote(row.get(COL_LOTE));
                e.setStatus(row.get(COL_STATUS));
                e.setMina(row.get(COL_MINA));
                e.setZona(row.get(COL_ZONA));
                e.setCostos(ExcelValueParser.stringValidador(row.get(COL_COSTOS)));
                e.setElaboradoPor(row.get(COL_ELABORADO));
                e.setMinTep(row.get(COL_MIN_TEP));
                e.setObra(row.get(COL_OBRA));
                e.setEstructura(row.get(COL_ESTRUCTURA));
                e.setLugar(row.get(COL_LUGAR));
                e.setMetros(ExcelValueParser.decimalValidador(row.get(COL_METROS)));
                e.setRowHash(HashUtil.calculateRowHash(e.getFecha().toString(), e.getStatus(), e.getEstructura(), e.getLugar(), e.getObra(),""));

                return e;
            }
            // }
        } catch (Exception e) {
            log.error("Error procesando Excel desarrollo", e);
        }
        return null;
    }

}