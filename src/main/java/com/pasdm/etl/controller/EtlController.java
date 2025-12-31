package com.pasdm.etl.controller;

import com.pasdm.etl.service.ExcelStreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/etl")
@RequiredArgsConstructor
public class EtlController {

    private final ExcelStreamingService excelStreamingService;

    @PostMapping("/run")
    public ResponseEntity<String> runEtl() {
        excelStreamingService.readLargeExcel();
        return ResponseEntity.ok("ETL ejecutado");
    }
}