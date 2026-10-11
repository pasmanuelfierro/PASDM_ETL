package com.pasdm.integration.service;

import com.pasdm.integration.enums.SheetType;
import com.pasdm.integration.mapper.LaboratoryQCMapper;
import com.pasdm.integration.model.LaboratoryQC;
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
public class SheetHandlerLaboratoryQC implements ExcelSheetHandler {
    private static final int BATCH_SIZE = 100;

    private final LaboratoryQCMapper mapperLaboratoryQC;
    private final BatchService batchService;

    private final List<LaboratoryQC> bufferLaboratoryQC = new ArrayList<>(BATCH_SIZE);
    private final Map<Integer, String> currentRow = new HashMap<>();
    private int totalProcessed = 0;

    public SheetHandlerLaboratoryQC(LaboratoryQCMapper mapperLaboratoryQC, BatchService batchService) {
        this.mapperLaboratoryQC = mapperLaboratoryQC;
        this.batchService = batchService;
    }

    @Override
    public void startRow(int i) {
        currentRow.clear();
    }

    @Override
    public SheetType getType() {
        return SheetType.LABORATORY_QC;
    }

    @Override
    public void endRow(int rowNum) {

        if (rowNum < 6) return; // encabezado 1

        try {
            LaboratoryQC entity = mapperLaboratoryQC.mapEntity(currentRow);
            if (entity != null) {
                bufferLaboratoryQC.add(entity);
                totalProcessed++;
            }
        } catch (Exception e) {
            log.error("Fila {} inválida: {}", rowNum, currentRow);
        }

        if (bufferLaboratoryQC.size() >= BATCH_SIZE) {
            batchService.upsertBatchLaboratoryQC(bufferLaboratoryQC);
            bufferLaboratoryQC.clear();
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
        if (!bufferLaboratoryQC.isEmpty()) {
            batchService.upsertBatchLaboratoryQC(bufferLaboratoryQC);
            bufferLaboratoryQC.clear();
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
        return "LC SRM";
    }

}
