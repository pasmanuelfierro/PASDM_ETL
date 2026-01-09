package com.pasdm.etl.service;

import com.pasdm.etl.enums.SheetType;
import com.pasdm.etl.factory.SheetHandlerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.util.XMLHelper;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.SharedStrings;
import org.apache.poi.xssf.model.StylesTable;
import org.springframework.stereotype.Service;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelStreamingService {

    private final SheetHandlerGeology sheetHandlerGeology;
    private final SheetHandlerPlant sheetHandlerPlant;
    private final SheetHandlerRRHH sheetHandlerRRHH;
    private final SheetHandlerMTTO sheetHandlerMTTO;
    private final SheetHandlerFactory sheetHandlerFactory;

    public void readLargeExcel(String excelPath) {
        log.info("Comenzando procesando Excel {}", excelPath);

        File file = new File(excelPath);
        ExcelSheetHandler sheetHandler = sheetHandlerFactory.get(resolve(file.getAbsoluteFile().toPath()));

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
            log.info("Termino procesando Excel {}", excelPath);

        } catch (Exception e) {
            log.error("Error procesando Excel", e);
        }
    }

    public SheetType resolve(Path path) {

        String filename = path.getFileName().toString().toLowerCase();

        if (filename.contains("geology")) {
            return SheetType.GEOLOGY;
        }
        if (filename.contains("plant")) {
            return SheetType.PLANT;
        }
        if (filename.contains("rrhh")) {
            return SheetType.RRHH;
        }
        if (filename.contains("mtto") || filename.contains("mantenimiento")) {
            return SheetType.MTTO;
        }

        throw new IllegalArgumentException(
                "No se pudo determinar el SheetType para el archivo: " + filename
        );
    }
}