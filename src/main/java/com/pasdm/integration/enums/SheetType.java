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
    LABORATORY("RECEPCION"),
    LABORATORY_GRADES("LEYES PLANTA"),
    LABORATORY_QC("LC SRM"),
    GEOLOGY_DRILLING("DIAMANTE CORREGIDO"),
    GEOLOGY_GRADE("BASE DE DATOS"),
    GEOLOGY_REPORT("BASE DE DATOS"),
    DIESEL_REPORT("BASE DE DATOS"),
    TOPOGRAPHY("INGRESO DATOS"),
    EXPLOSIVES("Datos"),
    ENTRADA_ACERO("ENTRADAS"),
    SALIDA_ACERO("SALIDAS"),
    TCD_CMD_ACTUAL("AP D"),
    PLAN_PROD("Hoja1"),
    PLAN_DEV("Hoja1"),
    TCD_CMD_BUDGET("BP D");

    private final String sheetName;

    SheetType(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getSheetName() {
        return sheetName;
    }
}