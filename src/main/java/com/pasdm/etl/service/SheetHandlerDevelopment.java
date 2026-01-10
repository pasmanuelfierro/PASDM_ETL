package com.pasdm.etl.service;

import com.pasdm.etl.enums.SheetType;
import com.pasdm.etl.mapper.DevelopmentMapper;
import com.pasdm.etl.model.Development;
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
public class SheetHandlerDevelopment implements ExcelSheetHandler {

    private static final int BATCH_SIZE = 1000;

    private final DevelopmentMapper mapperDevelopment;
    private final BatchService batchService;

    private final List<Development> bufferDevelopment = new ArrayList<>(BATCH_SIZE);
    private final Map<Integer, String> currentRow = new HashMap<>();
    private int totalProcessed = 0;

    public SheetHandlerDevelopment(DevelopmentMapper mapperDevelopment,
                                   BatchService batchService) {
        this.mapperDevelopment = mapperDevelopment;
        this.batchService = batchService;
    }

    @Override
    public void startRow(int rowNum) {
        currentRow.clear();
    }

    @Override
    public void cell(String cellReference, String formattedValue, XSSFComment comment) {

        if (cellReference == null) {
            return;
        }

        // Ignorar columnas A y B
        if (cellReference.startsWith("A") || cellReference.startsWith("B")) {
            return;
        }

        int colIndex = CellReference.convertColStringToIndex(
                cellReference.replaceAll("\\d", "")
        );

        currentRow.put(colIndex, formattedValue);
    }

    @Override
    public void endRow(int rowNum) {

        if (rowNum == 0) return; // encabezado 1

        try {
            Development entity = mapperDevelopment.mapEntity(currentRow);
            if (entity != null) {
                bufferDevelopment.add(entity);
                totalProcessed++;
            }
        } catch (Exception e) {
            log.error("Fila {} inválida: {}", rowNum, currentRow, e);
        }

        if (bufferDevelopment.size() >= BATCH_SIZE) {
            batchService.upsertBatchDevelopment(bufferDevelopment);
            bufferDevelopment.clear();
        }
    }

    @Override
    public void headerFooter(String text, boolean isHeader, String tagName) {
        // no-op
    }

    @Override
    public void flushRemaining() {
        if (!bufferDevelopment.isEmpty()) {
            batchService.upsertBatchDevelopment(bufferDevelopment);
            bufferDevelopment.clear();
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
        return SheetType.DEVELOPMENT;
    }
}