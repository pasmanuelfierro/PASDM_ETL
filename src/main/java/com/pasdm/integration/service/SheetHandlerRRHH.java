package com.pasdm.integration.service;

import com.pasdm.integration.enums.SheetType;
import com.pasdm.integration.mapper.RRHHMapper;
import com.pasdm.integration.model.RRHH;
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
public class SheetHandlerRRHH implements ExcelSheetHandler {

    private static final int BATCH_SIZE = 1000;

    private final RRHHMapper mapperRRHH;
    private final BatchService batchService;

    private final List<RRHH> bufferRRHH = new ArrayList<>(BATCH_SIZE);
    private final Map<Integer, String> currentRow = new HashMap<>();
    private int totalProcessed = 0;

    public SheetHandlerRRHH(RRHHMapper mapperRRHH,
                            BatchService batchService) {
        this.mapperRRHH = mapperRRHH;
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
            RRHH entity = mapperRRHH.mapEntity(currentRow);
            if (entity != null) {
                bufferRRHH.add(entity);
            }
        } catch (Exception e) {
            log.error("Fila {} inválida: {}", rowNum, currentRow);
        }

        if (bufferRRHH.size() >= BATCH_SIZE) {
            batchService.saveBatchRRHH(bufferRRHH);
            bufferRRHH.clear();
        }
    }

    @Override
    public void headerFooter(String text, boolean isHeader, String tagName) {
        // no-op
    }

    @Override
    public void flushRemaining() {
        if (!bufferRRHH.isEmpty()) {
            batchService.saveBatchRRHH(bufferRRHH);
            bufferRRHH.clear();
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
        return SheetType.RRHH;
    }

    @Override
    public String sheetName() {
        return "BD_RR.HH";
    }
}