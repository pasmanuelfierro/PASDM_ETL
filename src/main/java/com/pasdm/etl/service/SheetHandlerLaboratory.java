package com.pasdm.etl.service;

import com.pasdm.etl.enums.SheetType;
import com.pasdm.etl.mapper.LaboratoryMapper;
import com.pasdm.etl.model.Laboratory;
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
public class SheetHandlerLaboratory implements ExcelSheetHandler{
    private static final int BATCH_SIZE = 100;

    private final LaboratoryMapper mapperLaboratory;
    private final BatchService batchService;

    private final List<Laboratory> bufferLaboratory = new ArrayList<>(BATCH_SIZE);
    private final Map<Integer, String> currentRow = new HashMap<>();
    private int totalProcessed = 0;

    public SheetHandlerLaboratory(LaboratoryMapper mapperLaboratory, BatchService batchService) {
        this.mapperLaboratory = mapperLaboratory;
        this.batchService = batchService;
    }

    @Override
    public void startRow(int i) {
        currentRow.clear();
    }

    @Override
    public SheetType getType() {
        return SheetType.LABORATORY;
    }

    @Override
    public void endRow(int rowNum) {

        if (rowNum == 0) return; // encabezado 1

        try {
            Laboratory entity = mapperLaboratory.mapEntity(currentRow);
            if (entity != null) {
                bufferLaboratory.add(entity);
            }
        } catch (Exception e) {
            log.error("Fila {} inválida: {}", rowNum, currentRow);
        }

        if (bufferLaboratory.size() >= BATCH_SIZE) {
            batchService.upsertBatchLaboratory(bufferLaboratory);
            bufferLaboratory.clear();
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
        return "Lab";
    }

}
