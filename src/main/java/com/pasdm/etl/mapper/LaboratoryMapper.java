package com.pasdm.etl.mapper;


import com.pasdm.etl.model.Laboratory;
import com.pasdm.etl.util.ExcelValueParser;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.pasdm.etl.util.ExcelValueParser.intValidador;

@Component
public class LaboratoryMapper {


    // DIARIO - MUESTRAS RECIBIDAS (SULFUROS)

    private static final int COL_FECHA = 0;

    private static final int COL_REC_SULFMUESTRAS_MINA = 1;
    private static final int COL_REC_SULFMUESTRAS_EXPLORACIONES = 2;

    private static final int COL_REC_SULFMUESTRAS_PLANTA = 3;
    private static final int COL_REC_SULFMUESTRAS_ESPECIALES = 4;
    private static final int COL_REC_SULFMUESTRAS_METALURGICA = 5;
    private static final int COL_REC_SULFMUESTRAS_COMERCIALIZACION = 6;
    private  static final int COL_REC_SULFMUESTRAS_QAQCGEOLOGIA = 7;
    private  static final int COL_REC_SULFMUESTRAS_QAQCLABORATORIO = 8;


    // DIARIO - MUESTRAS ANALIZADAS (SULFUROS)

    private static final int COL_ANA_SULFMUESTRAS_MINA = 9;
    private static final int COL_ANA_SULFMUESTRAS_EXPLORACIONES = 10;
    private static final int COL_ANA_SULFMUESTRAS_PLANTA = 11;
    private static final int COL_ANA_SULFMUESTRAS_ESPECIALES = 12;
    private static final int COL_ANA_SULFMUESTRAS_METALURGICA = 13;
    private static final int COL_ANA_SULFMUESTRAS_COMERCIALIZACION = 14;
    private static final int COL_ANA_SULFMUESTRAS_QAQCGEOLOGIA = 15;
    private static final int COL_ANA_SULFMUESTRAS_QAQCLABORATORIO = 16;


    // DIARIO - MUESTRAS RECIBIDAS (OXIDO)
    private static final int COL_REC_OXI_MUESTRAS_MINA = 17;
    private static final int COL_REC_OXI_MUESTRAS_EXPLORACIONES = 18;
    private static final int COL_REC_OXI_MUESTRAS_PLANTA = 19;
    private static final int COL_REC_OXI_MUESTRAS_ESPECIALES = 20;
    private static final int COL_REC_OXI_METALURGICA = 21;
    private static final int COL_REC_OXI_COMERCIALIZACION = 22;
    private static final int COL_REC_OXI_QAQCGEOLOGIA = 23;
    private static final int COL_REC_OXI_QAQCLABORATORIO = 24;

    // VERIFICACIONES CRM
    private static final int COL_VERIFICACION_SUPERVISOR = 25;
    private static final int COL_VERIFICACION_OPERATIVO = 26;


    public Laboratory mapEntity(Map<Integer, String> row) {

        if (row.isEmpty()) return null;
        Laboratory L = new  Laboratory();

        // DIARIO - MUESTRAS RECIBIDAS (SULFUROS)
        L.setFecha_1(ExcelValueParser.dateValidador(row.get(COL_FECHA)));
        L.setRecSulfMuestrasMina(intValidador(row.get(COL_REC_SULFMUESTRAS_MINA)));
        L.setRecSulfMuestrasExploraciones(intValidador(row.get(COL_REC_SULFMUESTRAS_EXPLORACIONES)));
        L.setRecSulfMuestrasPlanta(intValidador(row.get(COL_REC_SULFMUESTRAS_PLANTA)));
        L.setRecSulfMuestrasEspeciales(intValidador(row.get(COL_REC_SULFMUESTRAS_ESPECIALES)));
        L.setRecSulfMuestrasMetalurgia(intValidador(row.get(COL_REC_SULFMUESTRAS_METALURGICA)));
        L.setRecSulfMuestrasComercializacion(intValidador(row.get(COL_REC_SULFMUESTRAS_COMERCIALIZACION)));
        L.setRecSulfMuestrasReensayeQaQcGeologia(intValidador(row.get(COL_REC_SULFMUESTRAS_QAQCGEOLOGIA)));
        L.setRecSulfMuestrasQaQcLaboratorio(intValidador(row.get(COL_REC_SULFMUESTRAS_QAQCLABORATORIO)));

        // DIARIO - MUESTRAS ANALIZADAS (SULFUROS)
        L.setAnaSulfMuestrasMina(intValidador(row.get(COL_ANA_SULFMUESTRAS_MINA)));
        L.setAnaSulfMuestrasExploraciones(intValidador(row.get(COL_ANA_SULFMUESTRAS_EXPLORACIONES)));
        L.setAnaSulfMuestrasPlanta(intValidador(row.get(COL_ANA_SULFMUESTRAS_PLANTA)));
        L.setAnaSulfMuestrasEspeciales(intValidador(row.get(COL_ANA_SULFMUESTRAS_ESPECIALES)));
        L.setAnaSulfMuestrasMetalurgia(intValidador(row.get(COL_ANA_SULFMUESTRAS_METALURGICA)));
        L.setAnaSulfMuestrasComercializacion(intValidador(row.get(COL_ANA_SULFMUESTRAS_COMERCIALIZACION)));
        L.setAnaSulfMuestrasReensayeQaQcGeologia(intValidador(row.get(COL_ANA_SULFMUESTRAS_QAQCGEOLOGIA)));
        L.setAnaSulfMuestrasQaQcLaboratorio(intValidador(row.get(COL_ANA_SULFMUESTRAS_QAQCLABORATORIO)));

        // DIARIO - MUESTRAS RECIBIDAS (OXIDO)
        L.setRecOxiMuestrasMina(intValidador(row.get(COL_REC_OXI_MUESTRAS_MINA)));
        L.setRecOxiMuestrasExploraciones(intValidador(row.get(COL_REC_OXI_MUESTRAS_EXPLORACIONES)));
        L.setRecOxiMuestrasPlanta(intValidador(row.get(COL_REC_OXI_MUESTRAS_PLANTA)));
        L.setRecOxiMuestrasEspeciales(intValidador(row.get(COL_REC_OXI_MUESTRAS_ESPECIALES)));
        L.setRecOxiMuestrasMetalurgia(intValidador(row.get(COL_REC_OXI_METALURGICA)));
        L.setRecOxiMuestrasComercializacion(intValidador(row.get(COL_REC_OXI_COMERCIALIZACION)));
        L.setRecOxiMuestrasReensayeQaQcGeologia(intValidador(row.get(COL_REC_OXI_QAQCGEOLOGIA)));
        L.setRecOxiMuestrasQaQcLaboratorio(intValidador(row.get(COL_REC_OXI_QAQCLABORATORIO)));

        // VERIFICACIONES CRM
        L.setVerificacionSupervisor(row.get(COL_VERIFICACION_SUPERVISOR));
        L.setVerificacionOperativo(row.get(COL_VERIFICACION_OPERATIVO));

        return L;
    }
}
