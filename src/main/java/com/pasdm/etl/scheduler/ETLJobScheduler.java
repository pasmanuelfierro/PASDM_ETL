package com.pasdm.etl.scheduler;
import com.pasdm.etl.service.ExcelStreamingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ETLJobScheduler {

    private final ExcelStreamingService excelStreamingService;

    @Scheduled(cron = "0 0 7 * * ?")
    public void processExcel() {
        log.info("Inicio proceso Excel grande");
        excelStreamingService.readLargeExcel();
        log.info("Fin proceso Excel grande");
    }
}