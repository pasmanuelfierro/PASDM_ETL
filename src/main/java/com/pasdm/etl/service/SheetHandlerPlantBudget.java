package com.pasdm.etl.service;

import com.pasdm.etl.enums.SheetType;
import com.pasdm.etl.mapper.PlantBudgetMapper;
import com.pasdm.etl.model.PlantBudget;
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
public class SheetHandlerPlantBudget implements ExcelSheetHandler {

    private static final int BATCH_SIZE = 1000;

    private final PlantBudgetMapper mapperPlant;
    private final BatchService batchService;

    private final List<PlantBudget> bufferPlantBudget = new ArrayList<>(BATCH_SIZE);
    private final Map<Integer, String> currentRow = new HashMap<>();
    private int totalProcessed = 0;

    public SheetHandlerPlantBudget(PlantBudgetMapper mapperPlant,
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
        if (rowNum == 1) return; // encabezado 2

        try {
            PlantBudget entity = mapperPlant.mapEntity(currentRow);
            if (entity != null) {
                bufferPlantBudget.add(entity);
                totalProcessed++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Fila {} inválida: {}", rowNum, currentRow);
        }

        if (bufferPlantBudget.size() >= BATCH_SIZE) {
            batchService.upsertBatchPlantBudget(bufferPlantBudget);
            bufferPlantBudget.clear();
        }
    }

    @Override
    public void headerFooter(String text, boolean isHeader, String tagName) {
        // no-op
    }

    @Override
    public void flushRemaining() {
        if (!bufferPlantBudget.isEmpty()) {
            batchService.upsertBatchPlantBudget(bufferPlantBudget);
            bufferPlantBudget.clear();
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
        return SheetType.PLANT_BUDGET;
    }

    @Override
    public String sheetName() {
        return "Budget";
    }

}