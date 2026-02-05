package com.pasdm.etl.controller;

import com.pasdm.etl.service.ExcelStreamingService;
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

    @Value("${excel.geology.path}")
    private String excelGeologyPath;

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
        excelStreamingService.readExcel(excelRRHHPath);
        return ResponseEntity.ok("ETL RRHH ejecutado");
    }

    //DESDE ARCHIVOS VERIFICADOS

    //Geologia
    @PostMapping("/run-geology-drilling")
    public ResponseEntity<String> runGeologyDrillingEtl() {
        excelStreamingService.readExcel(excelGeologyDrillingPath);
        return ResponseEntity.ok("ETL run-geology-drilling ejecutado");
    }

    @PostMapping("/run-geology-grade")
    public ResponseEntity<String> runGeologyGradeEtl() {
        excelStreamingService.readExcel(excelGeologyGradePath);
        return ResponseEntity.ok("ETL run-geology-grade ejecutado");
    }

    @PostMapping("/run-geology-report")
    public ResponseEntity<String> runGeologyReportEtl() {
        excelStreamingService.readExcel(excelGeologyReportPath);
        return ResponseEntity.ok("ETL run-geology-report ejecutado");
    }
    //Geologia

    //Laboratorio
    @PostMapping("/run-laboratory-plant")
    public ResponseEntity<String> runLaboratoryPlantEtl() {
        excelStreamingService.readExcel(excelLaboratoryPlantPath);
        return ResponseEntity.ok("ETL run-laboratory-plant ejecutado");
    }
    //Laboratorio

    @PostMapping("/run-security")
    public ResponseEntity<String> runSecurityEtl() {
        excelStreamingService.readExcel(excelSecurityPath);
        return ResponseEntity.ok("ETL run-security ejecutado");
    }

    @PostMapping("/run-prod")
    public ResponseEntity<String> runProdEtl() {
        excelStreamingService.readExcel(excelProdPath);
        return ResponseEntity.ok("ETL run-prod ejecutado");
    }

    @PostMapping("/run-dev")
    public ResponseEntity<String> runDevEtl() {
        excelStreamingService.readExcel(excelDevPath);
        return ResponseEntity.ok("ETL run-dev ejecutado");
    }

    @PostMapping("/run-plant")
    public ResponseEntity<String> runPlantEtl() {
        excelStreamingService.readExcel(excelPlantPath);
        return ResponseEntity.ok("ETL run-plant ejecutado");
    }

}