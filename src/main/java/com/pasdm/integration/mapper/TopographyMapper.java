package com.pasdm.integration.mapper;

import com.pasdm.integration.model.Topography;
import com.pasdm.integration.util.ExcelValueParser;
import com.pasdm.integration.util.HashUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;

@Slf4j
@Component
public class TopographyMapper {
    // =======================
    // ÍNDICES DE COLUMNA (Excel real)
    // =======================
    private static final int COL_LUGARD = 0;
    private static final int COL_OBRA = 1;

    private static final int COL_TIPOMINADO = 2;
    private static final int COL_ZONA = 3;
    private static final int COL_VETA = 4;
    private static final int COL_TIPOMATERIAL = 5;

    private static final int COL_PRIORIDAD = 6;

    private static final int COL_LOTE = 7;
    private static final int COL_EMPRESA = 8;
    private static final int COL_CUENTA = 9;
    private static final int COL_GRUPO = 10;
    private static final int COL_AREA = 11;
    private static final int COL_ANCHOPLAN = 12;
    private static final int COL_ALTOPLAN = 13;
    private static final int COL_LAD = 14;
    private static final int COL_FECHA = 15;

    private static final int COL_AVANCE = 16;
    private static final int COL_DISP = 17;
    private static final int COL_ANCHO = 18;
    private static final int COL_ALTO = 19;
    private static final int COL_TON = 20;

    public Topography mapEntity(Map<Integer, String> row) {
        try {

            LocalDate localDate = LocalDate.now();
            Topography t = new Topography();

            if (ExcelValueParser.dateValidadorProd(row.get(COL_FECHA)) != null) {

                // if (ExcelValueParser.dateValidadorProd(row.get(COL_FECHA)).isBefore(localDate) || ExcelValueParser.dateValidadorProd(row.get(COL_FECHA)).isEqual(localDate)) {

                t.setFecha(ExcelValueParser.dateValidadorProd(row.get(COL_FECHA)));
                t.setLugarD(row.get(COL_LUGARD));
                t.setObra(row.get(COL_OBRA));

                t.setTipoMinado(row.get(COL_TIPOMINADO));
                t.setZona(row.get(COL_ZONA));
                t.setVeta(row.get(COL_VETA));
                t.setTipoMaterial(row.get(COL_TIPOMATERIAL));

                t.setPrioridad(ExcelValueParser.decimalValidador(row.get(COL_PRIORIDAD)));

                t.setLote(row.get(COL_LOTE));
                t.setEmpresa(row.get(COL_EMPRESA));
                t.setCuenta(row.get(COL_CUENTA));
                t.setGrupo(row.get(COL_GRUPO));
                t.setArea(row.get(COL_AREA));
                t.setAnchoPlan(ExcelValueParser.decimalValidador(row.get(COL_ANCHOPLAN)));
                t.setAltoPlan(ExcelValueParser.decimalValidador(row.get(COL_ALTOPLAN)));
                t.setLad(ExcelValueParser.decimalValidador(row.get(COL_LAD)));

                t.setAvance(ExcelValueParser.decimalValidador(row.get(COL_AVANCE)));
                t.setDisp(ExcelValueParser.decimalValidador(row.get(COL_DISP)));
                t.setAncho(ExcelValueParser.decimalValidador(row.get(COL_ANCHO)));
                t.setAlto(ExcelValueParser.decimalValidador(row.get(COL_ALTO)));
                t.setTon(ExcelValueParser.decimalValidador(row.get(COL_TON)));

                t.setRowHash(HashUtil.calculateRowHash(t.getFecha().toString(), t.getLugarD(), t.getObra(), t.getTipoMinado(), t.getZona(), t.getVeta()));
                return t;
            }
            // }
        } catch (Exception e) {
            log.error("Error procesando Excel produccion", e);
        }
        return null;
    }

}