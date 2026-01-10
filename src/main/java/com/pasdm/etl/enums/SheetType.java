package com.pasdm.etl.enums;

import java.nio.file.Path;

public enum SheetType {

    GEOLOGY("BD_GEOLOGIA"),
    PLANT("BD_PLANTA"),
    RRHH("BD_RR.HH"),
    MTTO("DB MTTO"),
    PRODUCTION("database"),
    TEST("Hoja1"),
    DEVELOPMENT("BD Desarrollo");

    private final String sheetName;

    SheetType(String sheetName) {
        this.sheetName = sheetName;
    }

    public static SheetType fromPath(String path) {
        String p = path.toLowerCase();

        if (p.contains("geology")) return GEOLOGY;
        if (p.contains("plant")) return PLANT;
        if (p.contains("rrhh")) return RRHH;
        if (p.contains("mtto")) return MTTO;
        if (p.contains("produccion")) return PRODUCTION;
        if (p.contains("reporte")) return TEST;
        if (p.contains("desarrollo")) return DEVELOPMENT;


        throw new IllegalArgumentException(
                "No se pudo determinar SheetType desde el path: " + path
        );
    }

    public String getSheetName() {
        return sheetName;
    }
}