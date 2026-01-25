package com.pasdm.etl.service;

import com.pasdm.etl.enums.SheetType;
import com.pasdm.etl.mapper.LaboratoryPlantMapper;
import com.pasdm.etl.model.LaboratoryPlant;
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
public class SheetHandlerLaboratoryPlant implements ExcelSheetHandler {
    private static final int BATCH_SIZE = 100;

    private final LaboratoryPlantMapper laboratoryPlantMapper;
    private final BatchService batchService;

    private final List<LaboratoryPlant> bufferLaboratoryPlant = new ArrayList<>(BATCH_SIZE);
    private final Map<Integer, String> currentRow = new HashMap<>();
    private int totalProcessed = 0;

    public SheetHandlerLaboratoryPlant(LaboratoryPlantMapper laboratoryPlantMapper, BatchService batchService) {
        this.laboratoryPlantMapper = laboratoryPlantMapper;
        this.batchService = batchService;
    }


    @Override
    public SheetType getType() {
        return SheetType.LABORATORY_PLANT;
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

        if (rowNum < 11) return; // salta todos los encabezados, comienza en el renglon 11
        try {
            LaboratoryPlant entity = laboratoryPlantMapper.mapEntity(currentRow);
            if (entity != null) {
                bufferLaboratoryPlant.add(entity);
                totalProcessed++;
            }
        } catch (Exception e) {
            log.error("Fila {} inválida: {}", rowNum, currentRow);
        }
        if (bufferLaboratoryPlant.size() >= BATCH_SIZE) {
            batchService.upsertBatchLaboratoryPlant(bufferLaboratoryPlant);
            bufferLaboratoryPlant.clear();
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
