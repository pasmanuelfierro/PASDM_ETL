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
    public void processTask(Long executionId, Long configurationId, String taskType) {

        TaskExecution execution = taskExecutionRepository.findById(executionId).orElseThrow();

        try {
            SourceConfiguration configuration = sourceConfigurationRepository.findById(configurationId).orElseThrow();

            SheetType sheetType = SheetType.valueOf(
                    taskType.trim().toUpperCase()
            );

            String excelPath = configuration.getDirectoryPath();
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
    private SheetType getSheetType(String taskType) {
        try {
            return SheetType.valueOf(taskType.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("No se un SheetType para la tarea: " + taskType);
        }
    }
}
