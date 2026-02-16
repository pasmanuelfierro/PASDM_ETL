package com.pasdm.integration.service;

import com.pasdm.integration.enums.SheetType;
import com.pasdm.integration.mapper.DieselReportMapper;
import com.pasdm.integration.model.DieselReport;
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
public class SheetHandlerDieselReport implements ExcelSheetHandler {

    private static final int BATCH_SIZE = 1000;

    private final DieselReportMapper mapper;
    private final BatchService batchService;

    private final List<DieselReport> buffer = new ArrayList<>(BATCH_SIZE);
    private final Map<Integer, String> currentRow = new HashMap<>();
    private int totalProcessed = 0;

    public SheetHandlerDieselReport(DieselReportMapper mapper,
                                    BatchService batchService) {
        this.mapper = mapper;
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

        try {
            DieselReport entity = mapper.mapEntity(currentRow);
            if (entity != null) {
                buffer.add(entity);
                totalProcessed++;
            }
        } catch (Exception e) {
            log.error("Fila {} inválida: {}", rowNum, currentRow);
        }

        if (buffer.size() >= BATCH_SIZE) {
            batchService.upsertBatchDieselReport(buffer);
            buffer.clear();
        }
    }

    @Override
    public void headerFooter(String text, boolean isHeader, String tagName) {
        // no-op
    }

    @Override
    public void flushRemaining() {
        if (!buffer.isEmpty()) {
            batchService.upsertBatchDieselReport(buffer);
            buffer.clear();
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
        return SheetType.DIESEL_REPORT;
    }

    @Override
    public String sheetName() {
        return "BASE DE DATOS";
    }
}