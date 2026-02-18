package com.pasdm.integration.service;

import com.pasdm.integration.enums.SheetType;
import com.pasdm.integration.mapper.ExplosivesMapper;
import com.pasdm.integration.model.Explosives;
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
public class SheetHandlerExplosives implements ExcelSheetHandler {
    private static final int BATCH_SIZE = 1000;

    private final ExplosivesMapper mapperExplosives;
    private final BatchService batchService;

    private final List<Explosives> bufferExplosives = new ArrayList<>(BATCH_SIZE);
    private final Map<Integer, String> currentRow = new HashMap<>();
    private int totalProcessed = 0;

    public SheetHandlerExplosives(ExplosivesMapper mapperExplosives, BatchService batchService) {
        this.mapperExplosives = mapperExplosives;
        this.batchService = batchService;
    }

    @Override
    public void startRow(int i) {
        currentRow.clear();
    }

    @Override
    public SheetType getType() {
        return SheetType.EXPLOSIVES;
    }

    @Override
    public void endRow(int rowNum) {

        if (rowNum == 0) return; // encabezado 1

        try {
            Explosives entity = mapperExplosives.mapEntity(currentRow);
            if (entity != null) {
                bufferExplosives.add(entity);
            }
        } catch (Exception e) {
            log.error("Fila {} inválida: {}", rowNum, currentRow);
        }

        if (bufferExplosives.size() >= BATCH_SIZE) {
            batchService.upsertBatchExplosives(bufferExplosives);
            bufferExplosives.clear();
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
        if (!bufferExplosives.isEmpty()) {
            batchService.upsertBatchExplosives(bufferExplosives);
            bufferExplosives.clear();
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
        return "Datos";
    }

}
