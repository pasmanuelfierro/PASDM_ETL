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

    @Value("${excel.prod.path}")
    private String excelProdPath;

    @Value("${excel.test.path}")
    private String excelTestPath;

    @PostMapping("/run-plant")
    public ResponseEntity<String> runPlantEtl() {
        excelStreamingService.readLargeExcel(excelPlantPath);
        return ResponseEntity.ok("ETL Plant ejecutado");
    }

    @PostMapping("/run-geology")
    public ResponseEntity<String> runGeologyEtl() {
        excelStreamingService.readLargeExcel(excelGeologyPath);
        return ResponseEntity.ok("ETL Geology ejecutado");
    }

    @PostMapping("/run-rrhh")
    public ResponseEntity<String> runRRHHEtl() {
        excelStreamingService.readLargeExcel(excelRRHHPath);
        return ResponseEntity.ok("ETL RRHH ejecutado");
    }

    @PostMapping("/run-prod")
    public ResponseEntity<String> runProdEtl() {
        excelStreamingService.readLargeExcel(excelProdPath);
        return ResponseEntity.ok("ETL PROD ejecutado");
    }

    @PostMapping("/run-dev")
    public ResponseEntity<String> runDevEtl() {
        excelStreamingService.readLargeExcel(excelDevPath);
        return ResponseEntity.ok("ETL DEV ejecutado");
    }

    @PostMapping("/run-test")
    public ResponseEntity<String> runTestEtl() {
        excelStreamingService.readLargeExcel(excelTestPath);
        return ResponseEntity.ok("ETL excelTestPath ejecutado");
    }

}