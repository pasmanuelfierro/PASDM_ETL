package com.pasdm.etl.factory;

import com.pasdm.etl.enums.SheetType;
import com.pasdm.etl.service.ExcelSheetHandler;
import com.pasdm.etl.service.SheetHandlerGeology;
import com.pasdm.etl.service.SheetHandlerPlantBudget;
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
        this.handlers.put(SheetType.PLANT_BUDGET, new SheetHandlerPlantBudget(null, null));
    }

    public ExcelSheetHandler get(SheetType type) {
        return handlers.get(type);
    }
}