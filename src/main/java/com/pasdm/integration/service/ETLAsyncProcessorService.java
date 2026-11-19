package com.pasdm.integration.service;

import com.pasdm.integration.enums.SheetType;
import com.pasdm.integration.model.SourceConfiguration;
import com.pasdm.integration.model.TaskExecution;
import com.pasdm.integration.repository.SourceConfigurationRepository;
import com.pasdm.integration.repository.TaskExecutionRepository;
import jakarta.persistence.EntityNotFoundException;
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

            // String excelPath = configuration.getDirectoryPath();


            //log.info("EXCEL PATH (archivo): {}", excelPath)
            log.info("SheetType: {}", sheetType);

            /*
                Falta armar bien la url
                excelPath = "smb://172.30.1.22/planeacion/01 BASES DE DATOS/BD DESARROLLO.xlsx"
                sheetType = SheetType.DEVELOPMENT

             */

            String excelPath = buildExcelPath(configuration);

            long recordsRead = excelStreamingService.readExcel(excelPath, sheetType);

            execution.setRecordsRead(recordsRead);
            execution.setRecordsInserted(recordsRead);
            execution.setRecordsUpdated(recordsRead);
            execution.setStatus("SUCCESS");

        } catch (Exception e) {

            log.error("Error en ejecución ETL {}", execution.getId(), e);

            execution.setStatus("ERROR");
            execution.setErrorMessage(e.getMessage());

        } finally {
            execution.setFinishedAt(OffsetDateTime.now());
            taskExecutionRepository.save(execution);
        }
    }


    private String buildExcelPath(SourceConfiguration configuration) {

        String host = configuration.getHost().trim();
        String directoryPath = configuration.getDirectoryPath().trim();

        host = host.replaceFirst("(?i)^smb://", "");

        directoryPath = directoryPath.replace("\\", "/");
        directoryPath = directoryPath.replaceFirst("^/+", "");

        if (host.isBlank() || directoryPath.isBlank()) {
            throw new IllegalArgumentException("Host or directory path is blank");
        }

        return "smb://" + host + "/" + directoryPath;
    }
}
