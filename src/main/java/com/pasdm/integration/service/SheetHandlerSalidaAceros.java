package com.pasdm.integration.service;

import com.pasdm.integration.enums.SheetType;
import com.pasdm.integration.mapper.SalidaAceroMapper;
import com.pasdm.integration.model.SalidaAcero;
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
public class SheetHandlerSalidaAceros implements ExcelSheetHandler {
    private static final int BATCH_SIZE = 1000;

    private final SalidaAceroMapper mapperSalidaAcero;
    private final BatchService batchService;

    private final List<SalidaAcero> bufferSalidaAcero = new ArrayList<>(BATCH_SIZE);
    private final Map<Integer, String> currentRow = new HashMap<>();
    private int totalProcessed = 0;

    public SheetHandlerSalidaAceros(SalidaAceroMapper mapperSalidaAcero, BatchService batchService) {
        this.mapperSalidaAcero = mapperSalidaAcero;
        this.batchService = batchService;
    }

    @Override
    public void startRow(int i) {
        currentRow.clear();
    }

    @Override
    public SheetType getType() {
        return SheetType.SALIDA_ACERO;
    }

    @Override
    public void endRow(int rowNum) {

        if (rowNum == 0) return; // encabezado 1

        try {
            SalidaAcero entity = mapperSalidaAcero.mapEntity(currentRow);
            if (entity != null) {
                bufferSalidaAcero.add(entity);
            }
        } catch (Exception e) {
            log.error("Fila {} inválida: {}", rowNum, currentRow);
        }

        if (bufferSalidaAcero.size() >= BATCH_SIZE) {
            batchService.upsertBatchSalidaAcero(bufferSalidaAcero);
            bufferSalidaAcero.clear();
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
        if (!bufferSalidaAcero.isEmpty()) {
            batchService.upsertBatchSalidaAcero(bufferSalidaAcero);
            bufferSalidaAcero.clear();
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
        return "SALIDAS";
    }

}
