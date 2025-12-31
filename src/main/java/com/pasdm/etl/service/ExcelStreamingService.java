package com.pasdm.etl.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.util.XMLHelper;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.SharedStrings;
import org.apache.poi.xssf.model.StylesTable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.File;
import java.io.InputStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelStreamingService {

    private final SheetHandler sheetHandler;
    @Value("${excel.path}")
    private String excelPath;

    public void readLargeExcel() {

        File file = new File(excelPath);

        try (OPCPackage pkg = OPCPackage.open(file)) {

            XSSFReader reader = new XSSFReader(pkg);
            SharedStrings sharedStrings = reader.getSharedStringsTable();
            StylesTable styles = reader.getStylesTable();

            DataFormatter formatter = new DataFormatter(true);

            XMLReader parser = XMLHelper.newXMLReader();

            //PROCESADOR DE CELDAS
            ContentHandler handler = new XSSFSheetXMLHandler(
                    styles,
                    sharedStrings,
                    sheetHandler,
                    formatter,
                    false
            );

            parser.setContentHandler(handler);

            try (InputStream sheet = reader.getSheetsData().next()) {
                parser.parse(new InputSource(sheet));
            }

            sheetHandler.flushRemaining();

        } catch (Exception e) {
            log.error("Error procesando Excel", e);
        }
    }
}