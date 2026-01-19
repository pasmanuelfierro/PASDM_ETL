package com.pasdm.etl.scheduler;

import com.pasdm.etl.service.ExcelStreamingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ETLJobScheduler {
    private final ExcelStreamingService excelStreamingService;

    @Value("${excel.rrhh.path}")
    private String excelRRHHPath;

    @Value("${excel.geology.path}")
    private String excelGeologyPath;

    @Value("${excel.plant.path}")
    private String excelPlantPath;

    @Value("${excel.laboratory.path}")
    private String excelLaboratoryPath;

    @Value("${excel.dev.path}")
    private String excelDevPath;

    @Value("${excel.prod.path}")
    private String excelProdPath;

    @Value("${excel.test.path}")
    private String excelTestPath;

    @Value("${excel.security.path}")
    private String excelSecurityPath;

    @Scheduled(cron = "0 55 06 * * ?")
    public void processExcel() {
        log.info("Inicio proceso Excel");
        // excelStreamingService.readExcel(excelRRHHPath);
        // excelStreamingService.readExcel(excelGeologyPath);
        // excelStreamingService.readExcel(excelPlantPath);
        excelStreamingService.readExcel(excelDevPath);
        excelStreamingService.readExcel(excelProdPath);
        excelStreamingService.readExcel(excelPlantPath);
        excelStreamingService.readExcel(excelLaboratoryPath);
        //  excelStreamingService.readExcel(excelSecurityPath);
        log.info("Fin proceso Excel");
    }
}