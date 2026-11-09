package com.pasdm.integration.service;

import com.pasdm.integration.enums.SheetType;
import com.pasdm.integration.model.SourceConfiguration;
import com.pasdm.integration.model.TaskExecution;
import com.pasdm.integration.repository.SourceConfigurationRepository;
import com.pasdm.integration.repository.TaskExecutionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ETLAsyncProcessorService {
    private final SourceConfigurationRepository sourceConfigurationRepository;
    private final TaskExecutionRepository taskExecutionRepository;
    private final ExcelStreamingService excelStreamingService;

    @Async
    public void processTask(TaskExecution execution, SourceConfiguration configuration, SheetType sheetType) {

        try {

            String excelPath = configuration.getDirectoryPath();

            /*
                Falta armar bien la url
                excelPath = "smb://172.30.1.22/planeacion/01 BASES DE DATOS/BD DESARROLLO.xlsx"
                sheetType = SheetType.DEVELOPMENT
             */
            long recordsRead = excelStreamingService.readExcel(excelPath, sheetType);

            execution.setRecordsRead(String.valueOf(recordsRead));
            execution.setStatus("SUCCESS");

        } catch (Exception e) {
            log.error("Error ejecutando ETL", e);
            execution.setStatus("ERROR");
            execution.setErrorMessage(e.getMessage());

        } finally {
            execution.setFinishedAt(OffsetDateTime.now());
            taskExecutionRepository.save(execution);
        }
    }
}
