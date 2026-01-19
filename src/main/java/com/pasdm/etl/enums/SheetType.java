package com.pasdm.etl.enums;

import com.pasdm.etl.model.Laboratory;

import java.nio.file.Paths;

public enum SheetType {

    GEOLOGY("BD_GEOLOGIA"),
    PLANT("Actual"),
    PLANT_BUDGET("Budget"),
    RRHH("BD_RR.HH"),
    MTTO("DB MTTO"),
    PRODUCTION("database"),
    TEST("Hoja1"),
    SECURITY("DB"),
    DEVELOPMENT("BD Desarrollo"),
    LABORATORY("BD_LABORATORY");

    private final String sheetName;

    SheetType(String sheetName) {
        this.sheetName = sheetName;
    }

    public static SheetType fromPath(String path) {
//        String p = path.toLowerCase();
        String fileName = Paths.get(path)
                .getFileName()
                .toString()
                .toLowerCase();

        if (fileName.contains("geology")) return GEOLOGY;
        if (fileName.contains("balance demo  2026")) return PLANT;
        if (fileName.contains("rrhh")) return RRHH;
        if (fileName.contains("mtto")) return MTTO;
        if (fileName.contains("produccion")) return PRODUCTION;
        if (fileName.contains("reporte")) return TEST;
        if (fileName.contains("desarrollo")) return DEVELOPMENT;
        if (fileName.contains("estadisticos")) return SECURITY;
        if (fileName.contains("laboratory")) return LABORATORY;

        throw new IllegalArgumentException(
                "No se pudo determinar SheetType desde el path: " + path
        );
    }

    public String getSheetName() {
        return sheetName;
    }
}