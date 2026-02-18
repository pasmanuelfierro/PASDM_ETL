package com.pasdm.integration.controller;

import com.pasdm.integration.enums.SheetType;
import com.pasdm.integration.service.ExcelStreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/etl")
@RequiredArgsConstructor
public class EtlController {
    private final ExcelStreamingService excelStreamingService;

    @Value("${excel.rrhh.path}")
    private String excelRRHHPath;

    @Value("${excel.plant.path}")
    private String excelPlantPath;

    @Value("${excel.dev.path}")
    private String excelDevPath;

    @Value("${excel.geology.drilling.path}")
    private String excelGeologyDrillingPath;

    @Value("${excel.laboratory.plant.path}")
    private String excelLaboratoryPlantPath;

    @Value("${excel.prod.path}")
    private String excelProdPath;

    @Value("${excel.security.path}")
    private String excelSecurityPath;

    @Value("${excel.geology.grade.path}")
    private String excelGeologyGradePath;

    @Value("${excel.geology.report.path}")
    private String excelGeologyReportPath;

    @Value("${excel.diesel.report.path}")
    private String excelDieselReport;

    @Value("${excel.topography.path}")
    private String excelTopographyPath;

    @Value("${excel.explosives.path}")
    private String excelExplosivesPath;

    @Value("${excel.entrada_aceros.path}")
    private String excelEntradaAceroPath;

    @Value("${excel.salida_aceros.path}")
    private String excelSalidaAceroPath;

/*
   @PostMapping("/run-geology")
    public ResponseEntity<String> runGeologyEtl() {
        excelStreamingService.readExcel(excelGeologyPath);
        return ResponseEntity.ok("ETL Geology ejecutado");

            @PostMapping("/run-laboratory")
    public ResponseEntity<String> runLaboratoryEtl() {
        excelStreamingService.readExcel(excelLaboratoryPath);
        return ResponseEntity.ok("ETL Laboratory ejecutado");
    }

    }*/

    @PostMapping("/run-rrhh")
    public ResponseEntity<String> runRRHHEtl() {
        excelStreamingService.readExcel(excelRRHHPath, SheetType.RRHH);
        return ResponseEntity.ok("ETL RRHH ejecutado");
    }

    //DESDE ARCHIVOS VERIFICADOS

    //Geologia
    @PostMapping("/run-geology-drilling")
    public ResponseEntity<String> runGeologyDrillingEtl() {
        excelStreamingService.readExcel(excelGeologyDrillingPath, SheetType.GEOLOGY_DRILLING);
        return ResponseEntity.ok("ETL run-geology-drilling ejecutado");
    }

    @PostMapping("/run-geology-grade")
    public ResponseEntity<String> runGeologyGradeEtl() {
        excelStreamingService.readExcel(excelGeologyGradePath, SheetType.GEOLOGY_GRADE);
        return ResponseEntity.ok("ETL run-geology-grade ejecutado");
    }

    @PostMapping("/run-geology-report")
    public ResponseEntity<String> runGeologyReportEtl() {
        excelStreamingService.readExcel(excelGeologyReportPath, SheetType.GEOLOGY_REPORT);
        return ResponseEntity.ok("ETL run-geology-report ejecutado");
    }
    //Geologia

    //Laboratorio
    @PostMapping("/run-laboratory-plant")
    public ResponseEntity<String> runLaboratoryPlantEtl() {
        excelStreamingService.readExcel(excelLaboratoryPlantPath, SheetType.LABORATORY_PLANT);
        return ResponseEntity.ok("ETL run-laboratory-plant ejecutado");
    }
    //Laboratorio

    @PostMapping("/run-security")
    public ResponseEntity<String> runSecurityEtl() {
        excelStreamingService.readExcel(excelSecurityPath, SheetType.SECURITY);
        return ResponseEntity.ok("ETL run-security ejecutado");
    }

    @PostMapping("/run-prod")
    public ResponseEntity<String> runProdEtl() {
        excelStreamingService.readExcel(excelProdPath, SheetType.PRODUCTION);
        return ResponseEntity.ok("ETL run-prod ejecutado");
    }

    @PostMapping("/run-dev")
    public ResponseEntity<String> runDevEtl() {
        excelStreamingService.readExcel(excelDevPath, SheetType.DEVELOPMENT);
        return ResponseEntity.ok("ETL run-dev ejecutado");
    }

    @PostMapping("/run-plant")
    public ResponseEntity<String> runPlantEtl() {
        excelStreamingService.readExcel(excelPlantPath, null);
        return ResponseEntity.ok("ETL run-plant ejecutado");
    }

    @PostMapping("/run-diesel-report")
    public ResponseEntity<String> runDieselReportEtl() {
        excelStreamingService.readExcel(excelDieselReport, SheetType.DIESEL_REPORT);
        return ResponseEntity.ok("ETL run-plant ejecutado");
    }

    @PostMapping("/run-topography")
    public ResponseEntity<String> runTopographyEtl() {
        excelStreamingService.readExcel(excelTopographyPath, SheetType.TOPOGRAPHY);
        return ResponseEntity.ok("ETL run-topography ejecutado");
    }

    @PostMapping("/run-explosives")
    public ResponseEntity<String> runExplosivesEtl() {
        excelStreamingService.readExcel(excelExplosivesPath, SheetType.EXPLOSIVES);
        return ResponseEntity.ok("ETL run-texplosives ejecutado");
    }

    @PostMapping("/run-entrada-acero")
    public ResponseEntity<String> runEntradaAceroEtl() {
        excelStreamingService.readExcel(excelEntradaAceroPath, SheetType.ENTRADA_ACERO);
        return ResponseEntity.ok("ETL run-entrada-acero ejecutado");
    }

    @PostMapping("/run-salida-acero")
    public ResponseEntity<String> runSalidaAceroEtl() {
        excelStreamingService.readExcel(excelSalidaAceroPath, SheetType.SALIDA_ACERO);
        return ResponseEntity.ok("ETL run-salida-acero ejecutado");
    }

}