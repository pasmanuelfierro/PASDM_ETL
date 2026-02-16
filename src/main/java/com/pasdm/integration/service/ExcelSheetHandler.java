package com.pasdm.integration.service;

import com.pasdm.integration.enums.SheetType;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;

public interface ExcelSheetHandler extends XSSFSheetXMLHandler.SheetContentsHandler {
    SheetType getType();
    String sheetName();

    int getCount();

    void flushRemaining();

    void resetCount();

}