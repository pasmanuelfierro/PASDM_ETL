package com.pasdm.integration.service;

import com.pasdm.integration.enums.SheetType;
import com.pasdm.integration.mapper.GeologyDrillingMapper;
import com.pasdm.integration.model.GeologyDrilling;
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
public class SheetHandlerGeologyDrilling implements ExcelSheetHandler {

    private static final int BATCH_SIZE = 100;

    private final BatchService batchService;

    private final List<GeologyDrilling> bufferGeologyDrilling = new ArrayList<>(BATCH_SIZE);
    private final Map<Integer, String> currentRow = new HashMap<>();
    private final GeologyDrillingMapper geologyDrillingMapper;

    private int totalProcessed = 0;

    public SheetHandlerGeologyDrilling(BatchService batchService, GeologyDrillingMapper geologyDrillingMapper) {
        this.batchService = batchService;
        this.geologyDrillingMapper = geologyDrillingMapper;
    }

    @Override
    public void startRow(int i) {
        currentRow.clear();
    }

    @Override
    public SheetType getType() {
        return SheetType.GEOLOGY_DRILLING;
    }

    @Override
    public void endRow(int rowNum) {
        if (rowNum == 0) return;

        try {
            GeologyDrilling geologyDrilling = geologyDrillingMapper.mapEntity(currentRow);
            if (geologyDrilling != null) {
                bufferGeologyDrilling.add(geologyDrilling);
                totalProcessed++;
            }
        } catch (Exception e) {
            log.error("Fila {} inválida: {}", rowNum, currentRow);
        }
        if (bufferGeologyDrilling.size() >= BATCH_SIZE) {
            batchService.upsertBatchGeologyDrilling(bufferGeologyDrilling);
            bufferGeologyDrilling.clear();
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
    public int getCount() {
        return totalProcessed;
    }

    @Override
    public void resetCount() {
        totalProcessed = 0;
    }

    @Override
    public void flushRemaining() {
        if (!bufferGeologyDrilling.isEmpty()) {
            batchService.upsertBatchGeologyDrilling(bufferGeologyDrilling);
            bufferGeologyDrilling.clear();
        }
    }

    @Override
    public String sheetName() {
        return "DIAMANTE CORREGIDO";
    }

    @Override
    public void headerFooter(String text, boolean isHeader, String tagName) {
        // no-op
    }

}
