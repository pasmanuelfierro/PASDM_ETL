package com.pasdm.integration.mapper;

import com.pasdm.integration.model.DieselReport;
import com.pasdm.integration.util.ExcelValueParser;
import com.pasdm.integration.util.HashUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class DieselReportMapper {

    public static final int COL_ESTACION = 0;
    public static final int COL_FLOTA = 1;
    public static final int COL_AREA = 2;
    public static final int COL_MODELO = 3;
    public static final int COL_DISPOSITIVO = 4;
    public static final int COL_CANTIDAD = 5;
    public static final int COL_PRODUCTO = 6;
    public static final int COL_FECHA = 7;
    public static final int COL_INTERIOR_SUPERFICIE = 8;
    public static final int COL_FAMILIA = 9;
    public static final int COL_COLORADA_CONTRATISTA = 10;
    public static final int COL_MES = 11;

    public DieselReport mapEntity(Map<Integer, String> row) {

        DieselReport e = new DieselReport();
        try {

            e.setEstacion(row.get(COL_ESTACION));
            e.setFlota(row.get(COL_FLOTA));
            e.setArea(row.get(COL_AREA));
            e.setModelo(row.get(COL_MODELO));
            e.setDispositivo(row.get(COL_DISPOSITIVO));
            e.setCantidad(ExcelValueParser.intValidador(row.get(COL_CANTIDAD)));
            e.setProducto(row.get(COL_PRODUCTO));
            e.setFecha(ExcelValueParser.dateValidador(row.get(COL_FECHA)));
            e.setInteriorSuperficie(row.get(COL_INTERIOR_SUPERFICIE));
            e.setFamilia(row.get(COL_FAMILIA));
            e.setColoradaContratista(row.get(COL_COLORADA_CONTRATISTA));
            e.setMes(row.get(COL_MES));
            e.setRowHash(HashUtil.calculateRowHash(e.getFecha().toString(), e.getEstacion(), e.getDispositivo(), e.getProducto(), e.getModelo(), e.getArea()));

            return e;

        } catch (Exception ex) {
            ex.printStackTrace();
            return e;
        }
    }
}
