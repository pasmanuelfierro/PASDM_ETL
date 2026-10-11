package com.pasdm.integration.service;

import com.pasdm.integration.enums.SheetType;
import com.pasdm.integration.mapper.LaboratoryGradesMapper;
import com.pasdm.integration.model.LaboratoryGrades;
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
public class SheetHandlerLaboratoryGrades implements ExcelSheetHandler {
    private static final int BATCH_SIZE = 100;

    private final LaboratoryGradesMapper laboratoryGradesMapper;
    private final BatchService batchService;

    private final List<LaboratoryGrades> bufferLaboratoryGrades = new ArrayList<>(BATCH_SIZE);
    private final Map<Integer, String> currentRow = new HashMap<>();
    private int totalProcessed = 0;

    public SheetHandlerLaboratoryGrades(LaboratoryGradesMapper laboratoryGradesMapper, BatchService batchService) {
        this.laboratoryGradesMapper = laboratoryGradesMapper;
        this.batchService = batchService;
    }

    @Override
    public SheetType getType() {
        return SheetType.LABORATORY_GRADES;
    }

    @Override
    public String sheetName() {
        return "LEYES PLANTA";
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
    public void flushRemaining() {

    }

    @Override
    public void startRow(int i) {
        currentRow.clear();
    }

    @Override
    public void endRow(int rowNum) {

        if (rowNum < 8) return; // salta todos los encabezados, comienza en el renglon 8
        try {
            LaboratoryGrades entity = laboratoryGradesMapper.mapEntity(currentRow);
            if (entity != null) {
                bufferLaboratoryGrades.add(entity);
                totalProcessed++;
            } else {
                log.error("Fila {} inválida: {}", rowNum, entity);
            }
        } catch (Exception e) {
            log.error("Fila {} inválida: {}", rowNum, currentRow);
        }
        if (bufferLaboratoryGrades.size() >= BATCH_SIZE) {
            batchService.upsertBatchLaboratoryGrades(bufferLaboratoryGrades);
            bufferLaboratoryGrades.clear();
        }

    }

    @Override
    public void cell(String cellReference, String formattedValue, XSSFComment comment) {

        int colIndex = CellReference.convertColStringToIndex(
                cellReference.replaceAll("\\d", "")
        );

        currentRow.put(colIndex, formattedValue);
    }
}
