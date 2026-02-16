package com.pasdm.integration.service;

import com.pasdm.integration.enums.SheetType;
import com.pasdm.integration.mapper.TopographyMapper;
import com.pasdm.integration.model.Topography;

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
public class SheetHandlerTopography implements ExcelSheetHandler{
    private static final int BATCH_SIZE = 100;

    private final TopographyMapper mapperTopography;
    private final BatchService batchService;

    private final List<Topography> bufferTopography = new ArrayList<>(BATCH_SIZE);
    private final Map<Integer, String> currentRow = new HashMap<>();
    private int totalProcessed = 0;

    public SheetHandlerTopography(TopographyMapper mapperTopography, BatchService batchService) {
        this.mapperTopography = mapperTopography;
        this.batchService = batchService;
    }

    @Override
    public void startRow(int i) {
        currentRow.clear();
    }

    @Override
    public SheetType getType() {
        return SheetType.TOPOGRAPHY;
    }

    @Override
    public void endRow(int rowNum) {

        if (rowNum == 0) return; // encabezado 1

        try {
            Topography entity = mapperTopography.mapEntity(currentRow);
            if (entity != null) {
                bufferTopography.add(entity);
            }
        } catch (Exception e) {
            log.error("Fila {} inválida: {}", rowNum, currentRow);
        }

        if (bufferTopography.size() >= BATCH_SIZE) {
            batchService.upsertBatchTopography(bufferTopography);
            bufferTopography.clear();
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
        if (!bufferTopography.isEmpty()) {
            batchService.upsertBatchTopography(bufferTopography);
            bufferTopography.clear();
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
        return "Ingreso Datos";
    }

}
