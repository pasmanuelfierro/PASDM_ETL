package com.pasdm.etl.service;

import com.pasdm.etl.enums.SheetType;
import com.pasdm.etl.mapper.SecurityMapper;
import com.pasdm.etl.model.RRHH;
import com.pasdm.etl.model.Security;
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
public class SheetHandlerSecurity implements ExcelSheetHandler {

    private static final int BATCH_SIZE = 1000;

    private final SecurityMapper mapper;
    private final BatchService batchService;

    private final List<Security> buffer = new ArrayList<>(BATCH_SIZE);
    private final Map<Integer, String> currentRow = new HashMap<>();
    private int totalProcessed = 0;

    public SheetHandlerSecurity(SecurityMapper mapper,
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
            Security entity = mapper.mapEntity(currentRow);
            if (entity != null) {
                buffer.add(entity);
            }
        } catch (Exception e) {
            log.error("Fila {} inválida: {}", rowNum, currentRow);
        }

        if (buffer.size() >= BATCH_SIZE) {
            batchService.upsertBatchSecurity(buffer);
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
            batchService.upsertBatchSecurity(buffer);
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
        return SheetType.SECURITY;
    }
}