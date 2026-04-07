package com.pasdm.integration.enums;

public enum SheetType {

    GEOLOGY("BD_GEOLOGIA"),
    PLANT("Actual"),
    PLANT_BUDGET("Budget"),
    RRHH("BD_RR.HH"),
    MTTO("DB MTTO"),
    PRODUCTION("database"),
    TEST("Hoja1"),
    SECURITY("BD"),
    DEVELOPMENT("BD Desarrollo"),
    LABORATORY("BD_LABORATORY"),
    LABORATORY_PLANT("LEYES PLANTA"),
    GEOLOGY_DRILLING("DIAMANTE CORREGIDO"),
    GEOLOGY_GRADE("BASE DE DATOS"),
    GEOLOGY_REPORT("BASE DE DATOS"),
    DIESEL_REPORT("BASE DE DATOS"),
    TOPOGRAPHY("INGRESO DATOS"),
    EXPLOSIVES("Datos"),
    ENTRADA_ACERO("ENTRADAS"),
    SALIDA_ACERO("SALIDAS");

    private final String sheetName;


    SheetType(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getSheetName() {
        return sheetName;
    }
}