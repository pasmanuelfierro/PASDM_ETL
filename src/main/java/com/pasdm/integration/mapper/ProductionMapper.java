package com.pasdm.integration.mapper;

import com.pasdm.integration.model.Production;
import com.pasdm.integration.util.ExcelValueParser;
import com.pasdm.integration.util.HashUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Slf4j
@Component
public class ProductionMapper {
    // =======================
    // ÍNDICES DE COLUMNA (Excel real)
    // =======================
    private static final int COL_FECHA = 0;
    private static final int COL_LOTE = 1;
    private static final int COL_MINA = 2;
    private static final int COL_ZONA = 3;
    private static final int COL_EMPRESA = 4;
    private static final int COL_OBRA = 5;
    private static final int COL_ESTATUS = 6;
    private static final int COL_ESTRUCTURA = 7;
    private static final int COL_LABOR = 8;

    private static final int COL_LEY_AG = 9;
    private static final int COL_LEY_AU = 10;
    private static final int COL_LEY_PB = 11;
    private static final int COL_LEY_ZN = 12;

    private static final int COL_VPT = 13;
    private static final int COL_DILUCION_PORC = 14;
    private static final int COL_TIPO = 15;
    private static final int COL_TONS = 16;

    private static final int COL_SUM_AG = 17;
    private static final int COL_SUM_AU = 18;
    private static final int COL_SUM_PB = 19;
    private static final int COL_SUM_ZN = 20;
    private static final int COL_SUM_VPT = 21;
    private static final int COL_SEQ = 25;

    public Production mapEntity(Map<Integer, String> row) {
        try {

            LocalDate localDate = LocalDate.now();
            Production e = new Production();

            if (ExcelValueParser.dateValidadorProd(row.get(COL_FECHA)) != null) {

                // if (ExcelValueParser.dateValidadorProd(row.get(COL_FECHA)).isBefore(localDate) || ExcelValueParser.dateValidadorProd(row.get(COL_FECHA)).isEqual(localDate)) {

                e.setFecha(ExcelValueParser.dateValidadorProd(row.get(COL_FECHA)));
                e.setLote(row.get(COL_LOTE));
                e.setMina(row.get(COL_MINA));
                e.setZona(row.get(COL_ZONA));
                e.setEmpresa(row.get(COL_EMPRESA));
                e.setObra(row.get(COL_OBRA));
                e.setEstatus(row.get(COL_ESTATUS));
                e.setEstructura(row.get(COL_ESTRUCTURA));
                e.setLabor(row.get(COL_LABOR));

                e.setLeyAg(ExcelValueParser.decimalValidador(row.get(COL_LEY_AG)));
                e.setLeyAu(ExcelValueParser.decimalValidador(row.get(COL_LEY_AU)));
                e.setLeyPb(ExcelValueParser.decimalValidador(row.get(COL_LEY_PB)));
                e.setLeyZn(ExcelValueParser.decimalValidador(row.get(COL_LEY_ZN)));

                e.setVpt(ExcelValueParser.decimalValidador(row.get(COL_VPT)));
                e.setDilucionPorc(ExcelValueParser.decimalValidador(row.get(COL_DILUCION_PORC)));
                e.setTipo(row.get(COL_TIPO));
                e.setTons(ExcelValueParser.decimalValidador(row.get(COL_TONS)));

                e.setSumAg(ExcelValueParser.decimalValidador(row.get(COL_SUM_AG)));
                e.setSumAu(ExcelValueParser.decimalValidador(row.get(COL_SUM_AU)));
                e.setSumPb(ExcelValueParser.decimalValidador(row.get(COL_SUM_PB)));
                e.setSumZn(ExcelValueParser.decimalValidador(row.get(COL_SUM_ZN)));
                e.setSumVpt(ExcelValueParser.decimalValidador(row.get(COL_SUM_VPT)));
                BigDecimal seq = ExcelValueParser.decimalValidador(row.get(COL_SEQ));
                e.setRowHash(HashUtil.calculateRowHash("", "", "", "", seq.toString(), "", "", ""));

                return e;
            }
            // }
        } catch (Exception e) {
            log.error("Error procesando Excel produccion", e);
        }
        return null;
    }

}