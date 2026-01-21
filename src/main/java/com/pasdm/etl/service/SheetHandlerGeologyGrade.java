package com.pasdm.etl.service;

import com.pasdm.etl.enums.SheetType;
import com.pasdm.etl.mapper.GeologyGradeMapper;
import com.pasdm.etl.model.GeologyGrade;
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
public class SheetHandlerGeologyGrade implements ExcelSheetHandler {

    private static final int BATCH_SIZE = 1000;

    private final GeologyGradeMapper gradeMapper;
    private final BatchService batchService;

    private final List<GeologyGrade> bufferGeologyGrade = new ArrayList<>(BATCH_SIZE);
    private final Map<Integer, String> currentRow = new HashMap<>();
    private int totalProcessed = 0;

    public SheetHandlerGeologyGrade(GeologyGradeMapper gradeMapper,
                                    BatchService batchService) {
        this.gradeMapper = gradeMapper;
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
            GeologyGrade entity = gradeMapper.mapEntity(currentRow);
            if (entity != null) {
                bufferGeologyGrade.add(entity);
                totalProcessed++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Fila {} inválida: {}", rowNum, currentRow);
        }

        if (bufferGeologyGrade.size() >= BATCH_SIZE) {
            batchService.upsertBatchGeologyGrade(bufferGeologyGrade);
            bufferGeologyGrade.clear();
        }
    }

    @Override
    public void headerFooter(String text, boolean isHeader, String tagName) {
        // no-op
    }

    @Override
    public void flushRemaining() {
        if (!bufferGeologyGrade.isEmpty()) {
            batchService.upsertBatchGeologyGrade(bufferGeologyGrade);
            bufferGeologyGrade.clear();
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
        return SheetType.GEOLOGY_GRADE;
    }

    @Override
    public String sheetName() {
        return "BASE DE DATOS";
    }

}