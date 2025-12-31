package com.pasdm.etl.service;

import com.pasdm.etl.mapper.GeologyMapper;
import com.pasdm.etl.model.Geology;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class SheetHandler implements XSSFSheetXMLHandler.SheetContentsHandler {

    private static final int BATCH_SIZE = 1000;

    private final GeologyMapper mapper;
    private final BatchService batchService;

    private final List<Geology> bufferGeology = new ArrayList<>(BATCH_SIZE);
    private final Map<Integer, String> currentRow = new HashMap<>();

    public SheetHandler(GeologyMapper mapper,
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
        if (rowNum == 1) return; // encabezado 2

        try {
            Geology entity = mapper.mapEntity(currentRow);
            bufferGeology.add(entity);
        } catch (Exception e) {
            log.error("Fila {} inválida: {}", rowNum, currentRow);
        }

        if (bufferGeology.size() >= BATCH_SIZE) {
            batchService.saveBatch(bufferGeology);
            bufferGeology.clear();
        }
    }

    @Override
    public void headerFooter(String text, boolean isHeader, String tagName) {
        // no-op
    }

    public void flushRemaining() {
        if (!bufferGeology.isEmpty()) {
            batchService.saveBatch(bufferGeology);
            bufferGeology.clear();
        }
    }
}