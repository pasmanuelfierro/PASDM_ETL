package com.pasdm.integration.service;

import com.pasdm.integration.enums.SheetType;
import com.pasdm.integration.mapper.GeologyReportMapper;
import com.pasdm.integration.model.GeologyReport;
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
public class SheetHandlerGeologyReport implements ExcelSheetHandler {

    private static final int BATCH_SIZE = 1000;

    private final GeologyReportMapper reportMapper;
    private final BatchService batchService;

    private final List<GeologyReport> bufferGeologyReport = new ArrayList<>(BATCH_SIZE);
    private final Map<Integer, String> currentRow = new HashMap<>();
    private int totalProcessed = 0;

    public SheetHandlerGeologyReport(GeologyReportMapper reportMapper,
                                     BatchService batchService) {
        this.reportMapper = reportMapper;
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
        if (rowNum == 2) return; // encabezado 3

        try {
            GeologyReport entity = reportMapper.mapEntity(currentRow);
            if (entity != null) {
                bufferGeologyReport.add(entity);
                totalProcessed++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Fila {} inválida: {}", rowNum, currentRow);
        }

        if (bufferGeologyReport.size() >= BATCH_SIZE) {
            batchService.upsertBatchGeologyReport(bufferGeologyReport);
            bufferGeologyReport.clear();
        }
    }

    @Override
    public void headerFooter(String text, boolean isHeader, String tagName) {
        // no-op
    }

    @Override
    public void flushRemaining() {
        if (!bufferGeologyReport.isEmpty()) {
            batchService.upsertBatchGeologyReport(bufferGeologyReport);
            bufferGeologyReport.clear();
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
        return SheetType.GEOLOGY_REPORT;
    }

    @Override
    public String sheetName() {
        return "BASE DE DATOSS";
    }

}