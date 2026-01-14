package com.pasdm.etl.service;

import com.pasdm.etl.enums.SheetType;
import com.pasdm.etl.factory.SheetHandlerFactory;
import com.pasdm.etl.infraestructure.nas.NasSmbClient;
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
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelStreamingService {

    private final SheetHandlerFactory sheetHandlerFactory;
    private final NasSmbClient nasSmbClient;

    public void readExcel(String excelPath) {
        log.info("Comenzando procesando Excel {}", excelPath);

        File file = new File(excelPath);
        SheetType type = SheetType.fromPath(excelPath);

/*        try (InputStream is = nasSmbClient.openFile(excelPath);
             OPCPackage pkg = OPCPackage.open(is)) {*/
        try (OPCPackage pkg = OPCPackage.open(file)) {

            XSSFReader reader = new XSSFReader(pkg);
            SharedStrings sharedStrings = reader.getSharedStringsTable();
            StylesTable styles = reader.getStylesTable();

            DataFormatter formatter = new DataFormatter(true);
            XSSFReader.SheetIterator sheets = (XSSFReader.SheetIterator) reader.getSheetsData();
            while (sheets.hasNext()) {

                try (InputStream sheetStream = sheets.next()) {

                    String sheetName = sheets.getSheetName().trim();

                    if (type == SheetType.PLANT) {
                        List<String> plantSheets = new ArrayList();
                        plantSheets.add("Budget");
                        plantSheets.add("Actual");

                        for (String sheet : plantSheets) {
                            XMLReader parser = XMLHelper.newXMLReader();
                            ExcelSheetHandler sheetHandler;
                            if ("Budget".equals(sheet)) {
                                sheetHandler = sheetHandlerFactory.get(SheetType.PLANT_BUDGET);
                            } else if ("Actual".equals(sheet)) {
                                sheetHandler = sheetHandlerFactory.get(resolve(excelPath));
                            } else {
                                throw new IllegalArgumentException("Hoja no soportada: " + sheet);
                            }

                            //PROCESADOR DE CELDAS
                            ContentHandler handler = new XSSFSheetXMLHandler(
                                    styles,
                                    sharedStrings,
                                    sheetHandler,
                                    formatter,
                                    false
                            );

                            parser.setContentHandler(handler);
                            parser.parse(new InputSource(sheetStream));
                            sheetHandler.flushRemaining();
                            log.info("Se procesaron {} filas", sheetHandler.getCount());
                            sheetHandler.resetCount();
                            log.info("Termino procesando Excel {}", excelPath);
                        }

                    } else {
                        if (!sheetName.equalsIgnoreCase(type.getSheetName())) {
                            continue;
                        }

                        XMLReader parser = XMLHelper.newXMLReader();
                        ExcelSheetHandler sheetHandler = sheetHandlerFactory.get(resolve(excelPath));
                        //PROCESADOR DE CELDAS
                        ContentHandler handler = new XSSFSheetXMLHandler(
                                styles,
                                sharedStrings,
                                sheetHandler,
                                formatter,
                                false
                        );

                        parser.setContentHandler(handler);
                        parser.parse(new InputSource(sheetStream));
                        sheetHandler.flushRemaining();
                        log.info("Se procesaron {} filas", sheetHandler.getCount());
                        sheetHandler.resetCount();
                        log.info("Termino procesando Excel {}", excelPath);

                    }

                }
            }

        } catch (Exception e) {
            log.error("Error procesando Excel", e);
        }
    }

    public SheetType resolve(String path) {

        String filename = path.toLowerCase();

        if (filename.contains("geology")) {
            return SheetType.GEOLOGY;
        }
        if (filename.contains("balance demo  2026")) {
            return SheetType.PLANT;
        }
        if (filename.contains("rrhh")) {
            return SheetType.RRHH;
        }
        if (filename.contains("mtto")) {
            return SheetType.MTTO;
        }
        if (filename.contains("produccion")) {
            return SheetType.PRODUCTION;
        }
        if (filename.contains("desarrollo")) {
            return SheetType.DEVELOPMENT;
        }

        throw new IllegalArgumentException(
                "No se pudo determinar el SheetType para el archivo: " + filename
        );
    }
}