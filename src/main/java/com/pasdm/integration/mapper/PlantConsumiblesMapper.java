package com.pasdm.integration.mapper;

 import com.pasdm.integration.model.PlantConsumibles;
import com.pasdm.integration.util.ExcelValueParser;
import com.pasdm.integration.util.HashUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

 import java.util.Map;

@Slf4j
@Component
public class PlantConsumiblesMapper {

    private static final int COL_FECHA = 0;                 // B
    private static final int COL_TURNO = 1;                // C
    private static final int COL_CAL = 2;                  // D
    private static final int COL_DEPREZINC = 3;            // E
    private static final int COL_SULFATO_COBRE = 4;        // F
    private static final int COL_FLOCULANTE = 5;           // G
    private static final int COL_AEROFINA_3418 = 6;        // H
    private static final int COL_ESPUMANTE = 7;            // I
    private static final int COL_RO_2 = 8;                 // J
    private static final int COL_BISULFITO_AMONIO = 9;    // K
    private static final int COL_BOLA_2_5 = 10;            // L
    private static final int COL_XANTATO = 11;             // M
    private static final int COL_ANTI_INCRUSTANTE = 12;    // N
    private static final int COL_AGUA_FRESCA = 13;         // O
    private static final int COL_SOL_RET = 14;             // P

    // Q está vacía

    private static final int COL_HORAS_OPERACION = 16;     // R
    private static final int COL_HORAS_DISPONIBLES = 17;   // S
    private static final int COL_HORAS_TRITURADAS = 18;    // T
    private static final int COL_GRANULOMETRIA_PCT = 19;   // U

    /* =========================
       MAPEO
       ========================= */

    public PlantConsumibles mapEntity(Map<Integer, String> row) {
        try {
            if (ExcelValueParser.dateValidador(row.get(COL_FECHA)) != null) {

                PlantConsumibles entity = new PlantConsumibles();

                entity.setFecha(ExcelValueParser.dateValidador(row.get(COL_FECHA)));
                entity.setTurno(ExcelValueParser.stringValidador(row.get(COL_TURNO)));

                entity.setCal(ExcelValueParser.decimalValidador(row.get(COL_CAL)));
                entity.setDeprezinc(ExcelValueParser.decimalValidador(row.get(COL_DEPREZINC)));
                entity.setSulfatoCobre(ExcelValueParser.decimalValidador(row.get(COL_SULFATO_COBRE)));
                entity.setFloculante(ExcelValueParser.decimalValidador(row.get(COL_FLOCULANTE)));
                entity.setAerofina3418(ExcelValueParser.decimalValidador(row.get(COL_AEROFINA_3418)));
                entity.setEspumante(ExcelValueParser.decimalValidador(row.get(COL_ESPUMANTE)));
                entity.setRo2(ExcelValueParser.decimalValidador(row.get(COL_RO_2)));
                entity.setBisulfitoAmonio(ExcelValueParser.decimalValidador(row.get(COL_BISULFITO_AMONIO)));
                entity.setBola25(ExcelValueParser.decimalValidador(row.get(COL_BOLA_2_5)));
                entity.setXantato(ExcelValueParser.decimalValidador(row.get(COL_XANTATO)));
                entity.setAntiIncrustante(ExcelValueParser.decimalValidador(row.get(COL_ANTI_INCRUSTANTE)));
                entity.setAguaFresca(ExcelValueParser.decimalValidador(row.get(COL_AGUA_FRESCA)));
                entity.setSolRet(ExcelValueParser.decimalValidador(row.get(COL_SOL_RET)));

                entity.setHorasOperacion(ExcelValueParser.decimalValidador(row.get(COL_HORAS_OPERACION)));
                entity.setHorasDisponibles(ExcelValueParser.decimalValidador(row.get(COL_HORAS_DISPONIBLES)));
                entity.setHorasTrituradas(ExcelValueParser.decimalValidador(row.get(COL_HORAS_TRITURADAS)));
                entity.setGranulometriaPct(ExcelValueParser.decimalValidador(row.get(COL_GRANULOMETRIA_PCT)));

                // Clave única lógica por fecha y turno
                entity.setRowHash(HashUtil.calculateRowHash(entity.getFecha().toString(), entity.getTurno(), "", "", "", "", "", ""));

                return entity;
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("Error procesando Excel PlantBudget", e);
            return null;
        }

    }
}
