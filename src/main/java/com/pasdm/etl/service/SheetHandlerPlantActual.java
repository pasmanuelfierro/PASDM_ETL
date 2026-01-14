package com.pasdm.etl.service;

import com.pasdm.etl.enums.SheetType;
import com.pasdm.etl.mapper.PlantActualMapper;
import com.pasdm.etl.model.PlantActual;
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
public class SheetHandlerPlantActual implements ExcelSheetHandler {

    private static final int BATCH_SIZE = 1000;

    private final PlantActualMapper mapperPlant;
    private final BatchService batchService;

    private final List<PlantActual> bufferPlantActual = new ArrayList<>(BATCH_SIZE);
    private final Map<Integer, String> currentRow = new HashMap<>();
    private int totalProcessed = 0;

    public SheetHandlerPlantActual(PlantActualMapper mapperPlant,
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
            PlantActual entity = mapperPlant.mapEntity(currentRow);
            if (entity != null) {
                bufferPlantActual.add(entity);
            }
        } catch (Exception e) {
            log.error("Fila {} inválida: {}", rowNum, currentRow);
        }

        if (bufferPlantActual.size() >= BATCH_SIZE) {
            batchService.upsertBatchPlantActual(bufferPlantActual);
            bufferPlantActual.clear();
        }
    }

    @Override
    public void headerFooter(String text, boolean isHeader, String tagName) {
        // no-op
    }

    @Override
    public void flushRemaining() {
        if (!bufferPlantActual.isEmpty()) {
            batchService.upsertBatchPlantActual(bufferPlantActual);
            bufferPlantActual.clear();
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
        return SheetType.PLANT;
    }

}