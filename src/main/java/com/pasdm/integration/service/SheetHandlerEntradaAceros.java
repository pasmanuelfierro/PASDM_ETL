package com.pasdm.integration.service;

import com.pasdm.integration.enums.SheetType;
import com.pasdm.integration.mapper.EntradaAceroMapper;
import com.pasdm.integration.model.EntradaAcero;
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
public class SheetHandlerEntradaAceros implements ExcelSheetHandler {
    private static final int BATCH_SIZE = 1000;

    private final EntradaAceroMapper mapperEntradaAcero;
    private final BatchService batchService;

    private final List<EntradaAcero> bufferEntradaAcero = new ArrayList<>(BATCH_SIZE);
    private final Map<Integer, String> currentRow = new HashMap<>();
    private int totalProcessed = 0;

    public SheetHandlerEntradaAceros(EntradaAceroMapper mapperEntradaAcero, BatchService batchService) {
        this.mapperEntradaAcero = mapperEntradaAcero;
        this.batchService = batchService;
    }

    @Override
    public void startRow(int i) {
        currentRow.clear();
    }

    @Override
    public SheetType getType() {
        return SheetType.ENTRADA_ACERO;
    }

    @Override
    public void endRow(int rowNum) {

        if (rowNum == 1) return; // encabezado 1

        try {
            EntradaAcero entity = mapperEntradaAcero.mapEntity(currentRow);
            if (entity != null) {
                bufferEntradaAcero.add(entity);
            }
        } catch (Exception e) {
            log.error("Fila {} inválida: {}", rowNum, currentRow);
        }

        if (bufferEntradaAcero.size() >= BATCH_SIZE) {
            batchService.upsertBatchEntradaAcero(bufferEntradaAcero);
            bufferEntradaAcero.clear();
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
        if (!bufferEntradaAcero.isEmpty()) {
            batchService.upsertBatchEntradaAcero(bufferEntradaAcero);
            bufferEntradaAcero.clear();
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
        return "ENTRADAS";
    }

}
