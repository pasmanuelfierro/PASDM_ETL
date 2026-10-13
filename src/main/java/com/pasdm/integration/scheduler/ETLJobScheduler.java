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

    @Value("${excel.geology.path}")
    private String excelGeologyPath;

    @Value("${excel.laboratory.path}")
    private String excelLaboratoryPath;

    @Value("${excel.laboratory.grades.path}")
    private String excelLaboratoryGradesPath;

    @Value("${excel.laboratory.qc.path}")
    private String excelLaboratoryQcPath;

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

    @Value("${excel.topography.path}")
    private String excelTopographyPath;

    @Value("${excel.daily.cmd.actual.path}")
    private String excelDailytCMDActualPath;

    @Value("${excel.daily.cmd.budget.path}")
    private String excelDailytCMDBudgetPath;

    @Value("${excel.plan.prod.path}")
    private String excelPlanProdPath;

    /*
        @Scheduled(cron = "0 55 06 * * ?") A LAS 6:55
    */
    //  @Scheduled(cron = "0 */5 * * * ?") Cada 5 minutos

    /*
        @Scheduled(cron = "0 55 * * * ?")*/

    @Scheduled(cron = "0 */15 * * * ?")
    //@Scheduled(cron = "0 */2 * * * ?")
    public void processExcel() {
        log.info("Inicio proceso Excel");
        // LOS BUENOS
       /* excelStreamingService.readExcel(excelDevPath, SheetType.DEVELOPMENT);
        excelStreamingService.readExcel(excelProdPath, SheetType.PRODUCTION);
        excelStreamingService.readExcel(excelPlantPath, SheetType.PLANT);
        excelStreamingService.readExcel(excelSecurityPath, SheetType.SECURITY);
        excelStreamingService.readExcel(excelLaboratoryPath, SheetType.LABORATORY);
        excelStreamingService.readExcel(excelLaboratoryGradesPath, SheetType.LABORATORY_GRADES);
        excelStreamingService.readExcel(excelGeologyPath, SheetType.GEOLOGY);
        excelStreamingService.readExcel(excelLaboratoryQcPath, SheetType.LABORATORY_QC);*/
        // LOS BUENOS

        /* Falta acceso desde CMD
         *
         * excelStreamingService.readExcel(excelDailytCMDActualPath, SheetType.TCD_CMD_ACTUAL);
         * excelStreamingService.readExcel(excelDailytCMDBudgetPath, SheetType.TCD_CMD_BUDGET);
         *
         */

        /*

        excelStreamingService.readExcel(excelRRHHPath);
        excelStreamingService.readExcel(excelGeologyPath);
        excelStreamingService.readExcel(excelPlantPath);
        excelStreamingService.readExcel(excelDieselReport, SheetType.DIESEL_REPORT);
        excelStreamingService.readExcel(excelGeologyGradePath, SheetType.GEOLOGY_GRADE);
        excelStreamingService.readExcel(excelGeologyReportPath, SheetType.GEOLOGY_REPORT);
        excelStreamingService.readExcel(excelTopographyPath, SheetType.TOPOGRAPHY);
        */

        log.info("Fin proceso Excel");
    }
}