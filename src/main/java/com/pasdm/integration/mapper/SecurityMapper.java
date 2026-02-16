package com.pasdm.integration.mapper;

import com.pasdm.integration.model.Security;
import com.pasdm.integration.util.ExcelValueParser;
import com.pasdm.integration.util.HashUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Slf4j
@Component
public class SecurityMapper {

    // =======================
    // ÍNDICES DE COLUMNA (Excel)
    // =======================
    private static final int COL_FECHA = 0;
    private static final int COL_TIPO = 1;
    private static final int COL_NO = 2;
    private static final int COL_COMENTARIO = 3;

    public Security mapEntity(Map<Integer, String> row) {
        if (row == null || row.isEmpty()) {
            return null;
        }

        LocalDate fecha = ExcelValueParser.dateValidador(row.get(COL_FECHA));
        String tipo = ExcelValueParser.stringValidador(row.get(COL_TIPO));
        BigDecimal no = ExcelValueParser.decimalValidador(row.get(COL_NO));
        String comentario = ExcelValueParser.stringValidador(row.get(COL_COMENTARIO));

        Security s = Security.builder()
                .fecha(fecha)
                .tipo(tipo)
                .no(no)
                .comentario(comentario)
                .build();

        s.setRowHash(HashUtil.calculateRowHash(s.getFecha().toString(), s.getTipo(), "", "", "", ""));

        return s;
    }
}
