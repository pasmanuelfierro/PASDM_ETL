package com.pasdm.etl.service;

import com.pasdm.etl.enums.SheetType;
import com.pasdm.etl.factory.SheetHandlerFactory;
import com.pasdm.etl.infraestructure.nas.NasSmbClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.util.ZipSecureFile;
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
import java.util.Objects;

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

       /* try (InputStream is = nasSmbClient.openFile(excelPath);
             OPCPackage pkg = OPCPackage.open(is)) {*/
        try (OPCPackage pkg = OPCPackage.open(file)) {
            ZipSecureFile.setMinInflateRatio(0);

            XSSFReader reader = new XSSFReader(pkg);
            SharedStrings sharedStrings = reader.getSharedStringsTable();
            StylesTable styles = reader.getStylesTable();

            DataFormatter formatter = new DataFormatter(true);
            XSSFReader.SheetIterator sheets = (XSSFReader.SheetIterator) reader.getSheetsData();
            while (sheets.hasNext()) {

                try (InputStream sheetStream = sheets.next()) {

                    String sheetName = sheets.getSheetName().trim();

                    if (type == SheetType.LABORATORY_PLANT) {

                        XMLReader parser = XMLHelper.newXMLReader();
                        ExcelSheetHandler sheetHandler;
                        if ("Budget".equals(sheetName)) {
                            sheetHandler = sheetHandlerFactory.get(sheetName);
                        } else if ("Actual".equals(sheetName)) {
                            sheetHandler = sheetHandlerFactory.get(sheetName);
                        } else {
                            log.error("Hoja no soportada: {}", sheetName);
                            continue;
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


                    } else {
                        String SHEET_NAME = type.getSheetName();
                        if (Objects.equals(type.getSheetName(), "BASE DE DATOSS")) {
                            SHEET_NAME = "BASE DE DATOS";
                        }
                        if (!sheetName.equalsIgnoreCase(SHEET_NAME)) {
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

    public String resolve(String path) {

        String filename = path.toLowerCase();

        if (filename.contains("geology")) {
            return "BD_GEOLOGIA";
        }

        if (filename.contains("rrhh")) {
            return "BD_RR.HH";
        }

        if (filename.contains("produccion")) {
            return "database";
        }

        if (filename.contains("desarrollo")) {
            return "BD Desarrollo";
        }

        if (filename.contains("estadisticos - sso - mlc.xlsx")) {
            return "BD";
        }

        if (filename.contains("laboratory")) {
            return "DB";
        }

        if (filename.contains("ley")) {
            return "BASE DE DATOS";
        }

        if (filename.contains("geologydrilling")) {
            return "DB";
        }

        if (filename.contains("avance barrenación")) {
            return "DIAMANTE CORREGIDO";
        }

        if (filename.contains("laboratoryplant")) {
            return "DB";
        }

        if (filename.contains("reporte_geologia")) {
            return "BASE DE DATOSS";
        }

        throw new IllegalArgumentException(
                "No se pudo determinar el SheetType para el archivo: " + filename
        );
    }
}