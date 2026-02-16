package com.pasdm.integration.factory;

import com.pasdm.integration.enums.SheetType;
import com.pasdm.integration.service.ExcelSheetHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class SheetHandlerFactory {

    private final Map<SheetType, ExcelSheetHandler> handlers;

    public SheetHandlerFactory(List<ExcelSheetHandler> list) {
        this.handlers = list.stream()
                .collect(Collectors.toMap(
                        ExcelSheetHandler::getType,
                        Function.identity()
                ));
    }

    public ExcelSheetHandler get(SheetType sheetName) {
        return handlers.get(sheetName);
    }
}