package com.pasdm.etl.service;

import com.pasdm.etl.enums.SheetType;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;

public interface ExcelSheetHandler extends XSSFSheetXMLHandler.SheetContentsHandler {
    SheetType getType();
    String sheetName();

    int getCount();

    void flushRemaining();

    void resetCount();

}