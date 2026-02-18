package com.pasdm.integration.mapper;

import com.pasdm.integration.model.EntradaAcero;
import com.pasdm.integration.util.ExcelValueParser;
import com.pasdm.integration.util.HashUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;

@Slf4j
@Component
public class EntradaAceroMapper {
    // =======================
    // ÍNDICES DE COLUMNA (Excel real)
    // =======================
    private static final int COL_FECHA = 1;
    private static final int COL_RECIBIDO_POR = 2;
    private static final int COL_CODIGO = 3;
    private static final int COL_ACERO = 4;
    private static final int COL_NOMBRE_ACERO = 5; // Columna1
    private static final int COL_CANTIDAD = 6;


    public EntradaAcero mapEntity(Map<Integer, String> row) {
        try {
            EntradaAcero e = new EntradaAcero();

            // FECHA
            LocalDate fecha = ExcelValueParser.dateValidadorDebug(row, row.get(COL_FECHA));

            e.setFecha(fecha);

            // DATOS
            e.setRecibidoPor(ExcelValueParser.stringValidador(row.get(COL_RECIBIDO_POR)));
            e.setCodigo(ExcelValueParser.stringValidador(row.get(COL_CODIGO)));
            e.setAcero(ExcelValueParser.stringValidador(row.get(COL_ACERO)));
            e.setNombreAcero(ExcelValueParser.stringValidador(row.get(COL_NOMBRE_ACERO)));
            e.setCantidad(ExcelValueParser.intValidador(row.get(COL_CANTIDAD)));

            // HASH
            e.setRowHash(HashUtil.calculateRowHash(
                    row.get(COL_FECHA),
                    row.get(COL_CODIGO),
                    row.get(COL_NOMBRE_ACERO),
                    row.get(COL_CANTIDAD),
                    "",
                    ""
            ));

            return e;

        } catch (Exception ex) {
            log.error("Error procesando Excel Entradas acero", ex);
            return null;
        }
    }
}