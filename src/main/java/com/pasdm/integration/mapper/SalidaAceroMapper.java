package com.pasdm.integration.mapper;

import com.pasdm.integration.model.SalidaAcero;
import com.pasdm.integration.util.ExcelValueParser;
import com.pasdm.integration.util.HashUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;

@Slf4j
@Component
public class SalidaAceroMapper {
    // =======================
    // ÍNDICES DE COLUMNA (Excel real)
    // =======================
    private static final int COL_FECHA = 1;
    private static final int COL_TURNO = 2;
    private static final int COL_GRUPO = 3;
    private static final int COL_CODIGO = 4;
    private static final int COL_ACERO = 5;
    private static final int COL_CANTIDAD = 6;
    private static final int COL_EQUIPO_COD = 7;
    private static final int COL_EQUIPO = 8;
    private static final int COL_NOMINA_OPERADOR = 9;
    private static final int COL_OPERADOR = 10;
    private static final int COL_NOMINA_SUPERVISOR = 11;
    private static final int COL_SUPERVISOR = 12;
    private static final int COL_ZONA = 13;
    private static final int COL_PROVEEDOR = 14;
    private static final int COL_COSTO = 15;


    public SalidaAcero mapEntity(Map<Integer, String> row) {
        try {
            SalidaAcero e = new SalidaAcero();

            // FECHA
            LocalDate fecha = ExcelValueParser.dateValidador(row.get(COL_FECHA));

            e.setFecha(fecha);

            // BÁSICOS
            e.setTurno(ExcelValueParser.stringValidador(row.get(COL_TURNO)));
            e.setGrupo(ExcelValueParser.stringValidador(row.get(COL_GRUPO)));
            e.setCodigo(ExcelValueParser.stringValidador(row.get(COL_CODIGO)));
            e.setAcero(ExcelValueParser.stringValidador(row.get(COL_ACERO)));
            e.setCantidad(ExcelValueParser.intValidador(row.get(COL_CANTIDAD)));

            // EQUIPO
            e.setEquipoCod(ExcelValueParser.stringValidador(row.get(COL_EQUIPO_COD)));
            e.setEquipo(ExcelValueParser.stringValidador(row.get(COL_EQUIPO)));

            // OPERACIÓN
            e.setNominaOperador(ExcelValueParser.stringValidador(row.get(COL_NOMINA_OPERADOR)));
            e.setOperador(ExcelValueParser.stringValidador(row.get(COL_OPERADOR)));
            e.setNominaSupervisor(ExcelValueParser.stringValidador(row.get(COL_NOMINA_SUPERVISOR)));
            e.setSupervisor(ExcelValueParser.stringValidador(row.get(COL_SUPERVISOR)));

            // CONTROL
            e.setZona(ExcelValueParser.stringValidador(row.get(COL_ZONA)));
            e.setProveedor(ExcelValueParser.stringValidador(row.get(COL_PROVEEDOR)));
            e.setCosto(ExcelValueParser.decimalValidador(row.get(COL_COSTO)));

            // HASH (para UPSERT)
            e.setRowHash(HashUtil.calculateRowHash(
                    row.get(COL_FECHA),
                    row.get(COL_CODIGO),
                    row.get(COL_EQUIPO_COD),
                    row.get(COL_NOMINA_OPERADOR),
                    row.get(COL_CANTIDAD),
                    row.get(COL_ZONA)
            ));

            return e;

        } catch (Exception ex) {
            log.error("Error procesando Excel Salida acero", ex);
            return null;
        }
    }

}