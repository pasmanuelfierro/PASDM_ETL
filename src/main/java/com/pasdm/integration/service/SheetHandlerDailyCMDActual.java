package com.pasdm.integration.service;

import com.pasdm.integration.enums.SheetType;
import com.pasdm.integration.mapper.DailyCMDActualMapper;
import com.pasdm.integration.mapper.PlantActualMapper;
import com.pasdm.integration.model.DailyCMDActual;
import com.pasdm.integration.model.PlantActual;
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
public class SheetHandlerDailyCMDActual implements ExcelSheetHandler {

    private static final int BATCH_SIZE = 1000;

    private final DailyCMDActualMapper mapperPlant;
    private final BatchService batchService;

    private final List<DailyCMDActual> bufferDailyCMDActual = new ArrayList<>(BATCH_SIZE);
    private final Map<Integer, String> currentRow = new HashMap<>();
    private int totalProcessed = 0;

    public SheetHandlerDailyCMDActual(DailyCMDActualMapper mapperPlant, BatchService batchService) {
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

        if (rowNum < 9) return; // Datos comienzan en fila 10 de Excel

        try {
            DailyCMDActual entity = mapperPlant.mapEntity(currentRow);
            if (entity != null) {
                bufferDailyCMDActual.add(entity);
                totalProcessed++;
            }
        } catch (Exception e) {
            log.error("Fila {} inválida: {}", rowNum, currentRow);
        }

        if (bufferDailyCMDActual.size() >= BATCH_SIZE) {
            batchService.upsertBatchDailyCMDActual(bufferDailyCMDActual);
            bufferDailyCMDActual.clear();
        }
    }

    @Override
    public void headerFooter(String text, boolean isHeader, String tagName) {
        // no-op
    }

    @Override
    public void flushRemaining() {
        if (!bufferDailyCMDActual.isEmpty()) {
            batchService.upsertBatchDailyCMDActual(bufferDailyCMDActual);
            bufferDailyCMDActual.clear();
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
        return SheetType.TCD_CMD_ACTUAL;
    }

    @Override
    public String sheetName() {
        return "AP D";
    }
}