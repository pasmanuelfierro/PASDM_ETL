package com.pasdm.integration.service;

import com.pasdm.integration.enums.SheetType;
import com.pasdm.integration.mapper.GeologyMapper;
import com.pasdm.integration.model.Geology;
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
public class SheetHandlerGeology implements ExcelSheetHandler {

    private static final int BATCH_SIZE = 1000;

    private final GeologyMapper mapperGeology;
    private final BatchService batchService;

    private final List<Geology> bufferGeology = new ArrayList<>(BATCH_SIZE);
    private final Map<Integer, String> currentRow = new HashMap<>();
    private int totalProcessed = 0;

    public SheetHandlerGeology(GeologyMapper mapperGeology,
                               BatchService batchService) {
        this.mapperGeology = mapperGeology;
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
            Geology entity = mapperGeology.mapEntity(currentRow);

            if (entity != null) {
                bufferGeology.add(entity);
            }

        } catch (Exception e) {
            log.error("Fila {} inválida: {}", rowNum, currentRow);
        }

        if (bufferGeology.size() >= BATCH_SIZE) {
            batchService.saveBatchGeology(bufferGeology);
            bufferGeology.clear();
        }
    }

    @Override
    public void headerFooter(String text, boolean isHeader, String tagName) {
        // no-op
    }

    @Override
    public void flushRemaining() {
        if (!bufferGeology.isEmpty()) {
            batchService.saveBatchGeology(bufferGeology);
            bufferGeology.clear();
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
        return SheetType.GEOLOGY;
    }

    @Override
    public String sheetName() {
        return "BD_GEOLOGIA";
    }
}