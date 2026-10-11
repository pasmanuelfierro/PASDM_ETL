package com.pasdm.integration.service;

import com.pasdm.integration.enums.SheetType;
import com.pasdm.integration.mapper.PlannedLaborsMapper;
import com.pasdm.integration.model.PlannedLabors;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Component
public class SheetHandlerPlannedLabors implements ExcelSheetHandler {
    private static final int BATCH_SIZE = 100;

    private final PlannedLaborsMapper mapperPlannedLabors;
    private final BatchService batchService;

    private final List<PlannedLabors> bufferPlannedLabors = new ArrayList<>(BATCH_SIZE);
    private final Map<Integer, String> currentRow = new HashMap<>();
    private int totalProcessed = 0;
    private int MONTH = LocalDate.now().getMonthValue();
    private final Set<String> processedHashes = new HashSet<>();

    public SheetHandlerPlannedLabors(PlannedLaborsMapper mapperPlannedLabors, BatchService batchService) {
        this.mapperPlannedLabors = mapperPlannedLabors;
        this.batchService = batchService;
    }

    public void setDate() {
        MONTH = LocalDate.now().getMonthValue();
        log.info("Setting date");
    }

    @Override
    public void startRow(int i) {
        currentRow.clear();
    }

    @Override
    public SheetType getType() {
        return SheetType.PLAN_PROD;
    }

    @Override
    public void endRow(int rowNum) {

        if (rowNum < 1) return; // encabezado 1

        try {
            PlannedLabors entity = mapperPlannedLabors.mapEntity(currentRow, MONTH);
            if (entity != null) {

                String rowHash = entity.getRowHash();

                if (rowHash == null || rowHash.isBlank()) {
                    log.warn("Fila {} sin rowHash", rowNum);
                    return;
                }

                // Evitar duplicados dentro de esta importación
                if (!processedHashes.add(rowHash)) {
                    log.debug("Fila {} duplicada, rowHash={}", rowNum, rowHash);
                    return;
                }

                bufferPlannedLabors.add(entity);
                totalProcessed++;
            }
        } catch (Exception e) {
            log.error("Fila {} inválida: {}", rowNum, currentRow);
        }

        if (bufferPlannedLabors.size() >= BATCH_SIZE) {
            batchService.upsertBatchPlannedLabors(bufferPlannedLabors);
            bufferPlannedLabors.clear();
        }

    }

    @Override
    public void cell(String cellReference, String formattedValue, XSSFComment comment) {

        int colIndex = CellReference.convertColStringToIndex(
                cellReference.replaceAll("\\d", "")
        );

        currentRow.put(colIndex, formattedValue);
    }

    @Override
    public void headerFooter(String text, boolean isHeader, String tagName) {
        // no-op
    }

    @Override
    public void flushRemaining() {
        if (!bufferPlannedLabors.isEmpty()) {
            batchService.upsertBatchPlannedLabors(bufferPlannedLabors);
            bufferPlannedLabors.clear();
        }
    }

    @Override
    public int getCount() {
        return totalProcessed;
    }


    @Override
    public void resetCount() {
        totalProcessed = 0;
    }

    @Override
    public String sheetName() {
        return "Hoja1";
    }

}
