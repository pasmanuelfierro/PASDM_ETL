package com.pasdm.integration.service;

import com.pasdm.integration.model.*;
import com.pasdm.integration.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExportPlannedLaborsService {

    private final PlannedLaborsRepository plannedLaborsRepository;

    public List<PlannedLabors> getPlannedLabors() {
        List<PlannedLabors> batch = plannedLaborsRepository.findAll();
        return batch;
    }

    public byte[] loadXControlExcel() {
        try (InputStream plantillaStream = getClass().getResourceAsStream("/templates/obras.xlsx");
             Workbook workbook = new XSSFWorkbook(plantillaStream);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            Sheet planSheet = workbook.getSheet("subniveles");

            int fila = 1;
            List<PlannedLabors> plannedLabors = getPlannedLabors();
            for (PlannedLabors plannedLabor : plannedLabors) {
                if (determinarCuenta(plannedLabor.getLabor()) != null) {
                    Row row = planSheet.createRow(fila++);
                    row.createCell(0).setCellValue(plannedLabor.getLabor());
                    row.createCell(1).setCellValue(plannedLabor.getEstructura());
                    row.createCell(2).setCellValue(formatearZona(plannedLabor.getZona()));
                    row.createCell(3).setCellValue(determinarCuenta(plannedLabor.getLabor()));
                    row.createCell(4).setCellValue(determinarProcesoMinero(plannedLabor.getLabor()));
                    row.createCell(5).setCellValue("Si");
                }

            }

            workbook.write(outputStream);
            byte[] contenido = outputStream.toByteArray();

            return contenido;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String determinarProcesoMinero(String labor) {

        if (labor == null || labor.isBlank()) {
            return "";
        }

        String valor = labor.trim().toUpperCase();

        if (valor.contains("BL")) {
            return "BARRENACION LARGA";
        }

        if (valor.contains("BRT")) {
            return "BREASTING";
        }

        char primerCaracter = valor.charAt(0);

        if (Character.isLetter(primerCaracter)) {
            return "DESARROLLO";
        }

        if (Character.isDigit(primerCaracter)) {
            return "SILL";
        }

        return "";
    }

    private String determinarCuenta(String labor) {

        if (labor == null || labor.isBlank()) {
            return null;
        }

        String valor = labor.trim().toUpperCase();

        if (valor.contains("BL")) {
            return "Produccion, Barrenacion Larga (Otros)";
        }

        if (valor.contains("BRT")) {
            return "Produccion, Breasting (Otros)";
        }

        if (valor.contains("ACC")) {
            return "Desarrollo, Acceso (Otros)";
        }

        if (valor.contains("LAT")) {
            return "Desarrollo, Lateral (Otros)";
        }

        if (valor.contains("NICHO")) {
            return "Desarrollo, Nicho (Otros)";
        }

        if (valor.contains("RPA")) {
            return "Desarrollo, Rampa (Otros)";
        }

        char primerCaracter = valor.charAt(0);
        if (Character.isDigit(primerCaracter)) {
            return "Produccion, Sill (Otros)";
        }

        return null;
    }

    private String formatearZona(String labor) {
        if (labor == null || labor.isBlank()) {
            return null;
        }

        String zona = labor.trim();

        if (zona.contains("Can")) {
            zona = zona.replace("Can", "Candelaria");
        }

        return zona;
    }
}