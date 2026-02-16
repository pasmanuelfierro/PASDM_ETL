package com.pasdm.integration.scheduler;

import com.pasdm.integration.enums.SheetType;
import com.pasdm.integration.service.ExcelStreamingService;
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

    @Value("${excel.plant.path}")
    private String excelPlantPath;

    @Value("${excel.geology.drilling.path}")
    private String excelGeologyDrillingPath;

    @Value("${excel.laboratory.plant.path}")
    private String excelLaboratoryPlantPath;

    @Value("${excel.dev.path}")
    private String excelDevPath;

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

    /*
        @Scheduled(cron = "0 55 06 * * ?") A LAS 6:55
    */
    @Scheduled(cron = "0 55 * * * ?")
    public void processExcel() {
        log.info("Inicio proceso Excel");
        /* excelStreamingService.readExcel(excelRRHHPath);
         excelStreamingService.readExcel(excelGeologyPath);
         excelStreamingService.readExcel(excelPlantPath);*/
        excelStreamingService.readExcel(excelDevPath, SheetType.DEVELOPMENT);
        excelStreamingService.readExcel(excelProdPath, SheetType.PRODUCTION);
        excelStreamingService.readExcel(excelPlantPath, null);
        excelStreamingService.readExcel(excelSecurityPath, SheetType.SECURITY);
        excelStreamingService.readExcel(excelGeologyGradePath, SheetType.GEOLOGY_GRADE);
        excelStreamingService.readExcel(excelGeologyReportPath, SheetType.GEOLOGY_REPORT);
        excelStreamingService.readExcel(excelDieselReport, SheetType.DIESEL_REPORT);

        /*excelStreamingService.readExcel(excelLaboratoryPath);
        excelStreamingService.readExcel(excelGeologyDrillingPath);
        excelStreamingService.readExcel(excelLaboratoryPlantPath);*/
        log.info("Fin proceso Excel");
    }
}