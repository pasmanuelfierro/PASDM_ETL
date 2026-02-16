package com.pasdm.integration.service;

import com.pasdm.integration.enums.SheetType;
import com.pasdm.integration.mapper.PlantV1Mapper;
import com.pasdm.integration.model.PlantV1;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFComment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j

public class SheetHandlerPlantV1 implements ExcelSheetHandler {

    private static final int BATCH_SIZE = 1000;

    private final PlantV1Mapper mapperPlant;
    private final BatchService batchService;

    private final List<PlantV1> bufferPlant = new ArrayList<>(BATCH_SIZE);
    private final Map<Integer, String> currentRow = new HashMap<>();
    private int totalProcessed = 0;

    public SheetHandlerPlantV1(PlantV1Mapper mapperPlant,
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

        if (colIndex == 0) {
            return;
        }

        currentRow.put(colIndex, formattedValue);
    }

    @Override
    public void endRow(int rowNum) {

        if (rowNum == 0) return; // encabezado 1
        if (rowNum == 1) return; // encabezado 2

        try {
            PlantV1 entity = mapperPlant.mapEntity(currentRow);
            if (entity != null) {
                bufferPlant.add(entity);
            }
        } catch (Exception e) {
            log.error("Fila {} inválida: {}", rowNum, currentRow);
        }

        if (bufferPlant.size() >= BATCH_SIZE) {
          //  batchService.saveBatchPlant(bufferPlant);
            bufferPlant.clear();
        }
    }

    @Override
    public void headerFooter(String text, boolean isHeader, String tagName) {
        // no-op
    }

    @Override
    public void flushRemaining() {
        if (!bufferPlant.isEmpty()) {
          //  batchService.saveBatchPlantV(bufferPlant);
            bufferPlant.clear();
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


    @Override
    public String sheetName() {
        return "BD_RR.HH";
    }
}