package com.pasdm.integration.service;

import com.pasdm.integration.enums.SheetType;
import com.pasdm.integration.mapper.PlantConsumiblesMapper;
import com.pasdm.integration.model.PlantBudget;
import com.pasdm.integration.model.PlantConsumibles;
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
public class SheetHandlerPlantConsumible implements ExcelSheetHandler {

    private static final int BATCH_SIZE = 1000;

    private final PlantConsumiblesMapper mapperPlant;
    private final BatchService batchService;

    private final List<PlantConsumibles> bufferPlanConsumible = new ArrayList<>(BATCH_SIZE);
    private final Map<Integer, String> currentRow = new HashMap<>();
    private int totalProcessed = 0;

    public SheetHandlerPlantConsumible(PlantConsumiblesMapper mapperPlant,
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

        int colIndex = CellReference.convertColStringToIndex(cellReference.replaceAll("\\d", ""));

        currentRow.put(colIndex, formattedValue);
    }

    @Override
    public void endRow(int rowNum) {

        if (rowNum == 0) return; // encabezado 1

        try {
            PlantConsumibles entity = mapperPlant.mapEntity(currentRow);
            if (entity != null) {
                bufferPlanConsumible.add(entity);
                totalProcessed++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Fila {} inválida: {}", rowNum, currentRow);
        }

        if (bufferPlanConsumible.size() >= BATCH_SIZE) {
            batchService.upsertBatchPlantConsumible(bufferPlanConsumible);
            bufferPlanConsumible.clear();
        }
    }

    @Override
    public void headerFooter(String text, boolean isHeader, String tagName) {
        // no-op
    }

    @Override
    public void flushRemaining() {
        if (!bufferPlanConsumible.isEmpty()) {
            batchService.upsertBatchPlantConsumible(bufferPlanConsumible);
            bufferPlanConsumible.clear();
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
        return SheetType.PLANT_CONSUMIBLES;
    }

    @Override
    public String sheetName() {
        return "Consumibles";
    }

}