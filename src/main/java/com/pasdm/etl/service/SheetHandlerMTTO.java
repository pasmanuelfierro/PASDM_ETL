package com.pasdm.etl.service;

import com.pasdm.etl.mapper.MTTOMapper;
import com.pasdm.etl.model.MTTO;
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
public class SheetHandlerMTTO implements XSSFSheetXMLHandler.SheetContentsHandler {

    private static final int BATCH_SIZE = 1000;

    private final MTTOMapper mapperMTTO;
    private final BatchService batchService;

    private final List<MTTO> bufferMTTO = new ArrayList<>(BATCH_SIZE);
    private final Map<Integer, String> currentRow = new HashMap<>();

    public SheetHandlerMTTO(MTTOMapper mapperMTTO, BatchService batchService) {
        this.mapperMTTO = mapperMTTO;
        this.batchService = batchService;
    }

    @Override
    public void startRow(int rowNum) {
        currentRow.clear();
    }

    @Override
    public void endRow(int rowNum) {

        if (rowNum == 0) return; // encabezado 1
        if (rowNum == 1) return; // encabezado 2

        try {
            MTTO entity = mapperMTTO.mapEntity(currentRow);
            bufferMTTO.add(entity);
        } catch (Exception e) {
            log.error("Fila {} inválida: {}", rowNum, currentRow);
        }

        if (bufferMTTO.size() >= BATCH_SIZE) {
            batchService.saveBatchMTTO(bufferMTTO);
            bufferMTTO.clear();
        }
    }

    @Override
    public void cell(String cellReference, String formattedValue, XSSFComment comment) {
        int colIndex = CellReference.convertColStringToIndex(
                cellReference.replaceAll("\\d", "")
        );
        currentRow.put(colIndex, formattedValue);
    }


    public void flushRemaining() {
        if (!bufferMTTO.isEmpty()) {
            batchService.saveBatchMTTO(bufferMTTO);
            bufferMTTO.clear();
        }
    }


}
