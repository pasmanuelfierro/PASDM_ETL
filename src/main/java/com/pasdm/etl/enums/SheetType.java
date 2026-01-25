package com.pasdm.etl.enums;

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
    GEOLOGY_REPORT("BASE DE DATOS");

    private final String sheetName;


    SheetType(String sheetName) {
        this.sheetName = sheetName;
    }

    public static SheetType fromPath(String path) {

        String fileName = path.substring(path.lastIndexOf('/') + 1).toLowerCase();

        if (fileName.contains("geology")) return GEOLOGY;
        if (fileName.contains("balance demo  2026")) return PLANT;
        if (fileName.contains("rrhh")) return RRHH;
        if (fileName.contains("mtto")) return MTTO;
        if (fileName.contains("produccion")) return PRODUCTION;
        if (fileName.contains("desarrollo")) return DEVELOPMENT;
        if (fileName.contains("estadisticos")) return SECURITY;
        if (fileName.contains("laboratory")) return LABORATORY;
        if (fileName.contains("geology_drilling")) return GEOLOGY_DRILLING;
        if (fileName.contains("01 leyes")) return LABORATORY_PLANT;
        if (fileName.contains("avance barrenación")) return GEOLOGY_DRILLING;
        if (fileName.contains("ley")) return GEOLOGY_GRADE;
        if (fileName.contains("reporte_geologia")) return GEOLOGY_REPORT;

        throw new IllegalArgumentException(
                "No se pudo determinar SheetType desde el path: " + path
        );
    }

    public String getSheetName() {
        return sheetName;
    }
}