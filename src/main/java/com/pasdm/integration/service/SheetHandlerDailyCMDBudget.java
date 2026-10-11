package com.pasdm.integration.service;

import com.pasdm.integration.enums.SheetType;
import com.pasdm.integration.mapper.DailyCMDBudgetMapper;
import com.pasdm.integration.model.DailyCMDBudget;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class SheetHandlerDailyCMDBudget implements ExcelSheetHandler {

    private static final int BATCH_SIZE = 1000;

    private final DailyCMDBudgetMapper mapperPlant;
    private final BatchService batchService;

    private final List<DailyCMDBudget> bufferDailyCMDBudget = new ArrayList<>(BATCH_SIZE);
    private final Map<Integer, String> currentRow = new HashMap<>();
    private int totalProcessed = 0;

    public SheetHandlerDailyCMDBudget(DailyCMDBudgetMapper mapperPlant,
                                      BatchService batchService) {
        this.mapperPlant = mapperPlant;
        this.batchService = batchService;
    }

    @Override
    public void startRow(int rowNum) {
        currentRow.clear();
    }

    @Override
    public void cell(String cellReference, String formattedValue, XSSFComment comment) {

        int colIndex = CellReference.convertColStringToIndex(
                cellReference.replaceAll("\\d", "")
        );

        currentRow.put(colIndex, formattedValue);
    }

    @Override
    public void endRow(int rowNum) {

        if (rowNum == 0) return; // encabezado 1

        try {
            DailyCMDBudget entity = mapperPlant.mapEntity(currentRow);
            if (entity != null) {
                bufferDailyCMDBudget.add(entity);
                totalProcessed++;
            }
        } catch (Exception e) {
            log.error("Fila {} inválida: {}", rowNum, currentRow);
        }

        if (bufferDailyCMDBudget.size() >= BATCH_SIZE) {
            batchService.upsertBatchDailyCMDBudget(bufferDailyCMDBudget);
            bufferDailyCMDBudget.clear();
        }
    }

    @Override
    public void headerFooter(String text, boolean isHeader, String tagName) {
        // no-op
    }

    @Override
    public void flushRemaining() {
        if (!bufferDailyCMDBudget.isEmpty()) {
            batchService.upsertBatchDailyCMDBudget(bufferDailyCMDBudget);
            bufferDailyCMDBudget.clear();
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
    public SheetType getType() {
        return SheetType.TCD_CMD_BUDGET;
    }

    @Override
    public String sheetName() {
        return "BP D";
    }
}